package hbrs.se2.collhbrs.views.AuthentificationViews;

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
import hbrs.se2.collhbrs.entity.Business;
import hbrs.se2.collhbrs.entity.Profile;
import hbrs.se2.collhbrs.entity.User;
import hbrs.se2.collhbrs.service.RegisterService;
import hbrs.se2.collhbrs.util.Globals;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@CssImport("./styles/index.css")
public class BusinessRegistrationView extends FormLayout {

    private final RegisterService registerService;
    private final Button submitButton = createButton("Register", ButtonVariant.LUMO_PRIMARY);
    private final Button cancelButton = createButton("Cancel", ButtonVariant.LUMO_ERROR);
    private TextField businessName;
    private TextField username;
    private EmailField email;
    private PasswordField password;
    private PasswordField passwordConfirm;

    public BusinessRegistrationView(RegisterService registerService) {
        this.registerService = registerService;
        setupLayout();
        setupFields();
        addButtons();
    }

    private boolean validateInput() {
        if (businessName.getValue().isEmpty() || !isValidCompanyName(businessName.getValue())) {
            Notification.show("Please enter a valid first name.");
            return false;
        }
        if (username.getValue().isEmpty() || !isValidUsername(username.getValue())) {
            Notification.show("Please enter a valid username.");
            return false;
        }
        if (email.getValue().isEmpty() || !isValidEmail(email.getValue())) {
            Notification.show("Please enter a valid e-mail address.");
            return false;
        }
        if (!password.getValue().equals(passwordConfirm.getValue())) {
            Notification.show("Passwords do not match.");
            return false;
        } else if (!isPasswordComplex(password.getValue())) {
            Notification.show("The password must be 8-16 characters long and contain upper case letters, lower case letters and numbers.");
            return false;
        }
        return true;
    }

    public boolean isUsernameAvailable(String username) {
        return registerService.getUsers().stream().noneMatch(user -> Objects.equals(user.getUsername(), username));
    }

    public boolean isEmailAvailable(String email) {
        return registerService.getUsers().stream().noneMatch(user -> Objects.equals(user.getEmail(), email));
    }

    private void registerUser() {

        if (!isUsernameAvailable(username.getValue())) {
            Notification.show("Registration failed: Username already taken");
            return;
        }

        if (!isEmailAvailable(email.getValue())) {
            Notification.show("Registration failed: Email already taken");
            return;
        }

        Profile profile = createProfile();
        User user = createUser(profile, username.getValue(), password.getValue(), email.getValue());

        registerService.saveProfile(profile);
        registerService.saveUser(user);

        Business business = createBusiness(businessName.getValue(), user);
        registerService.saveBusiness(business);

        Notification.show("User successfully registered");
        UI.getCurrent().navigate("login");
    }

    public Profile createProfile() {
        return new Profile();
    }

    public User createUser(Profile profile, String username, String password, String email) {
        User user = new User();
        user.setProfile(profile);
        user.setUsername(username);
        user.setPassword(password);
        user.setBlacklisted(0);
        user.setEmail(email);
        return user;
    }

    public Business createBusiness(String name, User user) {
        Business business = new Business();
        business.setName(name);
        business.setUser(user);
        return business;
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

    private void setupFields() {
        H3 title = new H3("Business registration");

        businessName = createTextField("Name of the organisation");
        username = createTextField("Username");
        email = new EmailField("Email");
        password = new PasswordField("Password");
        passwordConfirm = new PasswordField("Confirm password");

        setRequiredIndicatorVisible(businessName, username, email, password, passwordConfirm);

        Span errorMessageField = new Span();

        add(title, businessName, username, email, password, passwordConfirm, errorMessageField);
        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(username, 2);
        setColspan(errorMessageField, 2);
        setColspan(businessName, 2);
    }

    private void addButtons() {

        cancelButton.addClickListener(e -> {
            Notification.show("Registration cancelled");
            UI.getCurrent().navigate(Globals.Pages.LOGIN_ALIAS);
        });

        submitButton.addClickListener(e -> {
            if (validateInput()) {
                registerUser();
            }
        });

        add(cancelButton, submitButton);
        setColspan(cancelButton, 1);
        setColspan(submitButton, 1);
    }

    private TextField createTextField(String label) {
        TextField textField = new TextField(label);
        textField.setRequiredIndicatorVisible(true);
        return textField;
    }

    private boolean isValidEmail(String email) {
        return Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$").matcher(email).matches();
    }

    private boolean isPasswordComplex(String password) {
        return Pattern.compile("^[A-Za-z\\d]{8,16}$").matcher(password).matches();
    }

    private static boolean isValidCompanyName(String companyName) {
        return Pattern.compile("^[A-Za-z\\s]{3,}[A-Za-z\\d\\s]*$").matcher(companyName).matches();
    }

    private boolean isValidUsername(String username) {
        return Pattern.matches("^[a-zA-Z0-9]+$", username) && username.length() < 20 && username.length() > 3;
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
