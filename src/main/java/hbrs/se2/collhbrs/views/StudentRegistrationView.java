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
import hbrs.se2.collhbrs.util.Globals;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Route(value = Globals.Pages.STUDENTREGISTRATION)
@CssImport("./styles/index.css")
// TODO: Bitte mehr refactoren ... Das ist irgendwie Spaghetti Code ; Logik vielleicht auslagern ? UIs zusammenpacken
public class StudentRegistrationView extends FormLayout {

    private final Button submitButton = createButton("Registrieren", ButtonVariant.LUMO_PRIMARY);
    private final Button cancelButton = createButton("Abbrechen", ButtonVariant.LUMO_ERROR);
    private TextField firstName;
    private TextField lastName;
    private TextField username;
    private EmailField email;
    private PasswordField password;
    private PasswordField passwordConfirm;

    public StudentRegistrationView(RegisterService registerService) {
        setupLayout();
        setupFields();
        addButtons(registerService);
    }

    private static boolean isValidFirstName(String firstName) {
        String firstNameRegex = "^[A-Za-z]{3,30}$";
        Pattern pattern = Pattern.compile(firstNameRegex);
        return pattern.matcher(firstName).matches();
    }

    private static boolean isValidLastName(String lastName) {
        String lastNameRegex = "^[A-Za-z]{3,30}$";
        Pattern pattern = Pattern.compile(lastNameRegex);
        return pattern.matcher(lastName).matches();
    }

    private static boolean isValidUsername(String username) {
        if (username.length() < 3 || username.length() > 20) {
            return false;
        }
        return Pattern.matches("^[a-zA-Z0-9]+$", username);
    }

    public boolean validateUsername(RegisterService registerService, User user) {
        List<User> users = registerService.getUsers();

        for (User existingUser : users) {
            if (Objects.equals(existingUser.getUsername(), user.getUsername())) {
                return false;
            }
        }
        return true;
    }

    public boolean validateEmail(RegisterService registerService, User user) {
        List<User> users = registerService.getUsers();

        for (User existingUser : users) {
            if (Objects.equals(existingUser.getEmail(), user.getEmail())) {
                return false;
            }
        }
        return true;
    }


    private void registerUser(RegisterService registerService) {
        Profile profile = createProfile();
        User user = createUser(profile, username.getValue(), password.getValue(), email.getValue());

        if (validateUsername(registerService, user)) {

            if (validateEmail(registerService, user)) {
                registerService.saveProfil(profile);
                registerService.saveUser(user);

                Student student = createStudent(user);
                registerService.saveStudent(student);

                saveFirstNames(registerService, firstName.getValue().split(" "), student);

                Notification.show("Benutzer erfolgreich registriert");
                UI.getCurrent().navigate("login");
            } else {
                Notification.show("Registrierung ist fehlgeschlagen: Email schon vergeben");
            }

            Notification.show("Benutzer erfolgreich registriert");
            UI.getCurrent().navigate(Globals.Pages.LOGIN_ALIAS);
        } else {
            Notification.show("Registrierung ist fehlgeschlagen: Nutzername schon vergeben");
        }
    }

    private Profile createProfile() {
        return new Profile();
    }

    private User createUser(Profile profile, String username, String password, String email) {
        User user = new User();
        user.setProfile(profile);
        user.setUsername(username);
        user.setPassword(password);
        user.setBlacklisted(0);
        user.setEmail(email);
        return user;
    }

    private Student createStudent(User user) {
        Student student = new Student();
        student.setUser(user);
        student.setLastName(lastName.getValue());
        return student;
    }

    private void saveFirstNames(RegisterService registerService, String[] vornamen, Student student) {
        IntStream.range(0, vornamen.length).forEach(counter -> {
            FirstName firstNameEntity = new FirstName();
            firstNameEntity.setFirstNameName(vornamen[counter]);
            firstNameEntity.setStudent(student);
            firstNameEntity.setSerialNumber(counter);
            registerService.saveVorname(firstNameEntity);
        });
    }

    private Button createButton(String text, ButtonVariant... variants) {
        Button button = new Button(text);
        button.addThemeVariants(variants);
        button.addClassName("button-layout");
        add(button);
        return button;
    }

    private void setupLayout() {
        addClassName("register");
        setMaxWidth("500px");
        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP)
        );
    }

    private TextField createTextField(String label) {
        TextField textField = new TextField(label);
        textField.setRequiredIndicatorVisible(true);
        return textField;
    }

    private void setupFields() {
        H3 title = new H3("Studentenregistrierung");
        firstName = createTextField("Vorname");
        lastName = createTextField("Nachname");
        username = createTextField("Nutzername");
        email = new EmailField("Email");
        password = new PasswordField("Passwort");
        passwordConfirm = new PasswordField("Passwort bestätigen");

        setRequiredIndicatorVisible(firstName, lastName, username, email, password, passwordConfirm);

        Span errorMessageField = new Span();

        add(title, firstName, lastName, username, email, password, passwordConfirm, errorMessageField);
        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(username, 2);
        setColspan(errorMessageField, 2);
    }

    private void addButtons(RegisterService registerService) {

        cancelButton.addClickListener(e -> {
            Notification.show("Registration abgebrochen");
            UI.getCurrent().navigate(Globals.Pages.LOGIN_ALIAS);
        });

        submitButton.addClickListener(e -> {
            if (validateInput()) {
                registerUser(registerService);
            }
        });

        add(cancelButton, submitButton);
        setColspan(cancelButton, 1);
        setColspan(submitButton, 1);
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

        if (firstName.getValue().isEmpty() || !isValidFirstName(firstName.getValue())) {
            Notification.show("Bitte geben Sie einen gültigen Vornamen ein.");
            isValid = false;
        }

        if (lastName.getValue().isEmpty() || !isValidLastName(lastName.getValue())) {
            Notification.show("Bitte geben Sie einen gültigen Nachnamen ein.");
            isValid = false;
        }

        if (username.getValue().isEmpty() || !isValidUsername(username.getValue())) {
            Notification.show("Bitte geben Sie einen gültigen Benutzernamen ein.");
            isValid = false;
        }

        if (email.getValue().isEmpty() || !isValidEmail(email.getValue())) {
            Notification.show("Bitte geben Sie eine gültige E-Mail-Adresse ein.");
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
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
