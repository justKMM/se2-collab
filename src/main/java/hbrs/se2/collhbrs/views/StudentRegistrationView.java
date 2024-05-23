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

import java.util.stream.IntStream;
import java.util.stream.Stream;

@Route(value = "student/registration")
@CssImport("./styles/index.css")
public class StudentRegistrationView extends FormLayout {

    private TextField firstName;
    private TextField lastName;
    private TextField username;
    private EmailField email;
    private PasswordField password;

    public StudentRegistrationView(RegisterService registerService) {
        setupLayout();
        setupFields();

        Button submitButton = createButton("Registrieren", ButtonVariant.LUMO_ICON);
        submitButton.addClickListener(e -> registerUser(registerService));

        Button cancelButton = createButton("Abbrechen", ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR);
        cancelButton.addClickListener(e -> {
            Notification.show("Registration abgebrochen");
            UI.getCurrent().navigate("login");
        });
    }

    private void registerUser(RegisterService registerService) {
        Profile profile = createProfile(registerService);

        User user = createUser(profile, username.getValue(), password.getValue(), email.getValue());

        if (isAlphaNumeric(user.getUsername()) && isAlphaNumeric(user.getPassword())) {
            registerService.saveUser(user);

            Student student = createStudent(user);
            registerService.saveStudent(student);

            saveFirstNames(registerService, firstName.getValue().split(" "), student);

            Notification.show("Benutzer erfolgreich registriert");
            UI.getCurrent().navigate("login");
        } else {
            Notification.show("Registration failed");
        }
    }

    private Profile createProfile(RegisterService registerService) {
        Profile profile = new Profile();
        registerService.saveProfil(profile);
        return profile;
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
        username.setWidth("100");
        email = new EmailField("Email");
        password = new PasswordField("Passwort");
        PasswordField passwordConfirm = new PasswordField("Passwort bestätigen");
        setRequiredIndicatorVisible(
                firstName,
                lastName,
                username,
                email,
                password,
                passwordConfirm
        );
        Span errorMessageField = new Span();
        add(
                title, firstName, lastName, username, email, password,
                passwordConfirm, errorMessageField
        );
        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(username, 2);
        setColspan(errorMessageField, 2);
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }

    private boolean isAlphaNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
