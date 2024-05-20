package hbrs.se2.collhbrs.views;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.entity.*;
import hbrs.se2.collhbrs.service.RegisterService;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@Route(value = "unternehmen_registration")
@CssImport("./styles/index.css")
public class UnternehmenRegistrationView extends FormLayout {

    private final H3 title;
    private final TextField unternehmenName;
    private final TextField username;
    private final EmailField email;
    private final PasswordField password;
    private final PasswordField passwordConfirm;
    private final Span errorMessageField;
    private final Button submitButton;


    public UnternehmenRegistrationView(RegisterService registerService) {

        addClassName("unternehmen_register");

        title = new H3("Unternehmensregistrierung");
        unternehmenName = new TextField("Name des Unternehmens");
        username = new TextField("Nutzername");
        email = new EmailField("Email");
        password = new PasswordField("Passwort");
        passwordConfirm = new PasswordField("Passwort bestätigen");

        setRequiredIndicatorVisible(
                unternehmenName,
                username,
                email,
                password,
                passwordConfirm
        );

        errorMessageField = new Span();

        submitButton = new Button("Registrieren");
        submitButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        submitButton.addClassName("button-layout");

        username.setWidth("100");

        add(
                title, unternehmenName, username, email, password,
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
        setColspan(unternehmenName, 2);
        setColspan(errorMessageField, 2);
        setColspan(submitButton, 2);

        submitButton.addClickListener(e -> {
            registerUser(registerService);
        });
    }

    private void registerUser(RegisterService registerService) {
        Profil profil = new Profil();
        registerService.saveProfil(profil);

        Benutzer benutzer = new Benutzer();
        benutzer.setProfil(profil);
        benutzer.setUsername(username.getValue());
        benutzer.setPasswort(password.getValue());
        benutzer.setBlacklisted(0);

        benutzer.setEmail(email.getValue());

        if (registerService.completeRegistration(benutzer)) {

            // Unternehmen erstellen
            Unternehmen unternehmen = new Unternehmen();
            unternehmen.setName(unternehmenName.getValue());
            unternehmen.setBenutzer(benutzer);

            registerService.saveUnternehmen(unternehmen);

            Notification.show("Benutzer erfolgreich registriert");
            UI.getCurrent().navigate("login");

        } else {
            Notification.show("Registration failed");
        }
    }

    public PasswordField getPasswordField() {
        return password;
    }

    public PasswordField getPasswordConfirmField() {
        return passwordConfirm;
    }

    public Span getErrorMessageField() {
        return errorMessageField;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
