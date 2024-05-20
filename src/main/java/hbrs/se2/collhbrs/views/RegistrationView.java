package hbrs.se2.collhbrs.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.entity.*;
import hbrs.se2.collhbrs.service.RegisterService;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@Route(value = "registration")
@CssImport("./styles/index.css")
public class RegistrationView extends FormLayout {

    private H3 title;
    private TextField firstName;
    private TextField lastName;
    private TextField username;
    private EmailField email;
    private PasswordField password;
    private PasswordField passwordConfirm;
    private Span errorMessageField;
    private Button submitButton;

    public RegistrationView(RegisterService registerService) {

        addClassName("register");
        title = new H3("Sign Up");
        firstName = new TextField("First name");
        lastName = new TextField("Last name");
        username =  new TextField("Username");
        email = new EmailField("Email");
        password = new PasswordField("Password");
        passwordConfirm = new PasswordField("Confirm password");

        setRequiredIndicatorVisible(
                firstName,
                lastName,
                username,
                email,
                password,
                passwordConfirm
        );

        errorMessageField = new Span();

        submitButton = new Button("Sign Up");
        submitButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        submitButton.addClassName("button-layout");

        username.setWidth("100");

        add(
                title, firstName, lastName, username, email, password,
                passwordConfirm, errorMessageField,
                submitButton
        );

        setMaxWidth("500px");

        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP)
        );

        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(username, 2);
        setColspan(errorMessageField, 2);
        setColspan(submitButton, 2);

        submitButton.addClickListener(e -> {


            Profil profil = new Profil();
            registerService.saveProfil(profil);

            Benutzer benutzer = new Benutzer();
            benutzer.setProfil(profil);
            benutzer.setUsername(username.getValue());
            benutzer.setPasswort(password.getValue());
            benutzer.setBlacklisted(0);

            benutzer.setEmail(email.getValue());

            boolean completeRegistration = registerService.completeRegistration(benutzer);

            // TODO: Bitte nachschauen ob schon ein Benutzer existiert bevor er erstellt wird
            if (completeRegistration) {

                // Student erstellen
                Student student = new Student();
                student.setBenutzer(benutzer);
                student.setNachname(lastName.getValue());
                registerService.saveStudent(student);



                // TODO: Vornamen richtig abspeichern. Funktioniert noch nicht richtig
                // Vornamen erstellen
                String[] vornamen = firstName.getValue().split(" ");
                IntStream.range(0, vornamen.length).forEach(counter -> {

                    Vorname vornameEntity = new Vorname();
                    vornameEntity.setVorname(vornamen[counter]);
                    vornameEntity.setLaufendeNummer(counter);
                    vornameEntity.setStudent(student);

                    registerService.saveVorname(vornameEntity);
                });


                Notification.show("Benutzer erfolgreich registriert");
                UI.getCurrent().navigate("login");
            }
            else {
                Notification.show("Registration failed");
            }
        });
    }

    public PasswordField getPasswordField() { return password; }

    public PasswordField getPasswordConfirmField() { return passwordConfirm; }

    public Span getErrorMessageField() { return errorMessageField; }

    public Button getSubmitButton() { return submitButton; }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
