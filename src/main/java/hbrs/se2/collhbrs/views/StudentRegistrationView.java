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
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.entity.FirstName;
import hbrs.se2.collhbrs.entity.Profile;
import hbrs.se2.collhbrs.entity.Student;
import hbrs.se2.collhbrs.entity.User;
import hbrs.se2.collhbrs.service.RegisterService;

import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Route(value = "student/registration")
@CssImport("./styles/index.css")
public class StudentRegistrationView extends FormLayout {

    private final H3 title;
    private final TextField firstName;
    private final TextField lastName;
    private final TextField username;
    private final EmailField email;
    private final PasswordField password;
    private final PasswordField passwordConfirm;
    private final Span errorMessageField;
    private final Button submitButton;
    private final Button cancelButton;

    public StudentRegistrationView(RegisterService registerService) {

        addClassName("register");
        title = new H3("Studentenregistrierung");
        firstName = new TextField("Vorname");
        lastName = new TextField("Nachname");
        username = new TextField("Nutzername");
        email = new EmailField("Email");
        password = new PasswordField("Passwort");
        passwordConfirm = new PasswordField("Passwort bestätigen");

        setRequiredIndicatorVisible(
                firstName,
                lastName,
                username,
                email,
                password,
                passwordConfirm
        );

        errorMessageField = new Span();

        // Submit registration button
        submitButton = new Button("Registrieren");
        submitButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        submitButton.addClassName("button-layout");

        // Cancel button
        cancelButton = new Button("Abbrechen");
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR);
        cancelButton.addClassName("button-layout");

        username.setWidth("100");

        add(
                title, firstName, lastName, username, email, password,
                passwordConfirm, errorMessageField,
                cancelButton, submitButton
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
        // setColspan(submitButton, 2);

        cancelButton.addClickListener(e -> {
            Notification.show("Registration abgebrochen");
            UI.getCurrent().navigate("login");
        });

        submitButton.addClickListener(e -> {
            if (validateInput()) {
                registerUser(registerService);
            }
        });
    }

    private boolean validateInput() {
        boolean isValid = true;
        if (!password.getValue().equals(passwordConfirm.getValue())) {
            Notification.show("Die Passwörter stimmen nicht überein.");
            isValid = false;
        } else if (!isPasswordComplex(password.getValue())) {
            Notification.show("Das Passwort muss 8-16 Zeichen lang sein und Großbuchstaben, Kleinbuchstaben, Zahlen enthalten.");
            isValid = false;
        }
        if (firstName.getValue().isEmpty()) {
            Notification.show("Bitte Vornamen eingeben.");
            isValid = false;
        } else if (!isValidFirstName(firstName.getValue())){
            Notification.show("Der Vorname muss mindestens 3 Zeichen lang sein und aus Buchstaben ggf. Zahlen bestehen.");
            isValid = false;
        }
        if (lastName.getValue().isEmpty()) {
            Notification.show("Der Nachname darf nur aus Buchstaben und höchstens 30 Zeichen bestehen.");
            isValid = false;
        } else if (!isValidLastName(lastName.getValue())){
            Notification.show("Der Nachname muss mindestens 3 Zeichen lang sein und aus Buchstaben ggf. Zahlen bestehen.");
            isValid = false;
        }
        if (username.getValue().isEmpty()) {
            Notification.show("Bitte Benutzernamen eingeben.");
            isValid = false;
        } else if (!isValidUsername(username.getValue())){
            Notification.show("Der Username muss 3-20 Zeichen lang sein und aus Buchstaben ggf. Zahlen bestehen.");
            isValid = false;
        }
        if (email.getValue().isEmpty()) {
            Notification.show("Bitte E-Mail-Adresse eingeben.");
            isValid = false;
        } else if (!isValidEmail(email.getValue())) {
            Notification.show("Bitte eine gültige E-Mail-Adresse eingeben.");
            isValid = false;
        }
        return isValid;
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    private boolean isPasswordComplex(String password) {
        String passwordRegex = "^[A-Za-z\\d]{8,16}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }
    private static boolean isValidFirstName(String firstName) {
        String lastNameRegex = "^[A-Za-z\\s-]{3,30}$";
        Pattern pattern = Pattern.compile(lastNameRegex);
        return pattern.matcher(firstName).matches();
    }
    private static boolean isValidLastName(String lastName) {
        String lastNameRegex = "^[A-Za-z\\s-]{3,30}$";
        Pattern pattern = Pattern.compile(lastNameRegex);
        return pattern.matcher(lastName).matches();
    }
    private static boolean isValidUsername(String username) {
        if (username.length() < 3 || username.length() > 20) {
            return false;
        }
        if (!Pattern.matches("^[a-zA-Z0-9]+$", username)) {
            return false;
        }
        return true;
    }
    private void registerUser(RegisterService registerService) {
        Profile profile = new Profile();
        registerService.saveProfil(profile);

        User user = new User();
        user.setProfile(profile);
        user.setUsername(username.getValue());
        user.setPassword(password.getValue());
        user.setBlacklisted(0);

        user.setEmail(email.getValue());

        if (registerService.completeRegistration(user)) {

            // Student erstellen
            Student student = new Student();
            student.setUser(user);
            student.setLastName(lastName.getValue());
            registerService.saveStudent(student);


            // Vornamen erstellen
            String[] vornamen = firstName.getValue().split(" ");

            IntStream.range(0, vornamen.length).forEach(counter -> {
                FirstName firstNameEntity = new FirstName();
                firstNameEntity.setFirstNameName(vornamen[counter]);
                firstNameEntity.setStudent(student);
                firstNameEntity.setSerialNumber(counter);
                registerService.saveVorname(firstNameEntity);
            });


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
