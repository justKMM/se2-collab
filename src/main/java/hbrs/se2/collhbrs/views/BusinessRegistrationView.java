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
import hbrs.se2.collhbrs.entity.Business;
import hbrs.se2.collhbrs.entity.Profile;
import hbrs.se2.collhbrs.entity.User;
import hbrs.se2.collhbrs.service.RegisterService;

import java.util.regex.Pattern;
import java.util.stream.Stream;

@Route(value = "unternehmen/registration")
@CssImport("./styles/index.css")
public class BusinessRegistrationView extends FormLayout {

    private TextField businessName;
    private TextField username;
    private EmailField email;
    private PasswordField password;


    public BusinessRegistrationView(RegisterService registerService) {
        setupLayout();
        setupFields();

        Button submitButton = createButton("Registrieren", ButtonVariant.LUMO_ICON);
        submitButton.addClickListener(e -> registerUser(registerService));

        Button cancelButton = createButton("Abbrechen", ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR);
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
        if (unternehmenName.getValue().isEmpty()) {
            Notification.show("Bitte Name des Unternehmens eingeben.");
            isValid = false;
        } else if (!isValidCompanyName(unternehmenName.getValue())){
            Notification.show("Der Name des Unternehmens muss mindestens 3 Zeichen lang sein und aus Buchstaben ggf. Zahlen bestehen.");
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
    private static boolean isValidCompanyName(String companyName) {
        // Überprüfen, ob der Unternehmensname mindestens 3 Zeichen lang ist und nur aus Buchstaben (ggf. auch Zahlen) und Leerzeichen besteht
        String companyNameRegex = "^[A-Za-z\\s]{3,}[A-Za-z\\d\\s]*$";
        Pattern pattern = Pattern.compile(companyNameRegex);
        return pattern.matcher(companyName).matches();
    }
    private boolean isPasswordComplex(String password) {
        String passwordRegex = "^[A-Za-z\\d]{8,16}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
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
        Profile profile = createProfile();
        User user = createUser(registerService, profile, username.getValue(), password.getValue(), email.getValue());

        if (isAlphaNumeric(user.getUsername()) && isAlphaNumeric(user.getPassword())) {
            registerService.saveProfil(profile);
            registerService.saveUser(user);

            Business business = createBusiness(registerService, businessName.getValue(), user);
            registerService.saveBusiness(business);

            Notification.show("Benutzer erfolgreich registriert");
            UI.getCurrent().navigate("login");

        } else {
            Notification.show("Registration failed");
        }
    }

    public Profile createProfile() {
        return new Profile();
    }

    public User createUser(RegisterService registerService, Profile profile, String username, String password, String email) {
        User user = new User();
        user.setProfile(profile);
        user.setUsername(username);
        user.setPassword(password);
        user.setBlacklisted(0);
        user.setEmail(email);
        return user;
    }

    public Business createBusiness(RegisterService registerService, String name, User user) {
        Business business = new Business();
        business.setName(name);
        business.setUser(user);
        return business;
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

    private void setupFields() {
        H3 title = new H3("Studentenregistrierung");

        businessName = createTextField("Name des Unternehmens");
        username = createTextField("Nutzername");
        username.setWidth("100");
        email = new EmailField("Email");
        password = new PasswordField("Passwort");
        PasswordField passwordConfirm = new PasswordField("Passwort bestätigen");
        setRequiredIndicatorVisible(
                businessName,
                username,
                email,
                password,
                passwordConfirm
        );
        Span errorMessageField = new Span();
        add(
                title, businessName, username, email, password,
                passwordConfirm, errorMessageField
        );
        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(username, 2);
        setColspan(errorMessageField, 2);
        setColspan(businessName, 2);
    }

    private TextField createTextField(String label) {
        TextField textField = new TextField(label);
        textField.setRequiredIndicatorVisible(true);
        return textField;
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
