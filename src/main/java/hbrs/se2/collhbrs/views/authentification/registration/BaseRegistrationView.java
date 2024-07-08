package hbrs.se2.collhbrs.views.authentification.registration;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.RouterLayout;
import hbrs.se2.collhbrs.service.registration.RegisterServiceImpl;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.util.RegisterUtils;

import java.util.stream.Stream;

public abstract class BaseRegistrationView extends FormLayout implements RouterLayout {

    protected TextField usernameField;
    protected PasswordField passwordField;
    protected EmailField emailField;
    protected PasswordField passwordConfirmationField;
    protected Button submitButton;
    protected Button cancelButton;
    protected Span errorMessageField;

    protected final transient RegisterServiceImpl registerService;

    protected BaseRegistrationView(RegisterServiceImpl registerService) {
        this.registerService = registerService;
        setupLayout();
        setupForm();
        addButtons();
    }

    private void setupLayout() {
        setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("500px", 2)
        );
        addClassName("registration-form");
    }

    private void setupForm() {
        usernameField = new TextField("Username");
        passwordField = new PasswordField("Password");
        emailField = new EmailField("Email");
        passwordConfirmationField = new PasswordField("Confirm Password");

        H3 title = new H3("Register");
        errorMessageField = new Span();

        setRequiredIndicatorVisible(usernameField, passwordField, emailField, passwordConfirmationField);

        add(title, usernameField, emailField, passwordField, passwordConfirmationField, errorMessageField);
        setColspan(title, 2);
        setColspan(usernameField, 2);
        setColspan(emailField, 2);
        setColspan(passwordField, 2);
        setColspan(passwordConfirmationField, 2);
        setColspan(errorMessageField, 2);
    }

    private void addButtons() {
        submitButton = createButton("Register", ButtonVariant.LUMO_PRIMARY);
        cancelButton = createButton("Cancel", ButtonVariant.LUMO_ERROR);

        cancelButton.addClickListener(e -> UI.getCurrent().navigate(Globals.Pages.LOGIN));

        submitButton.addClickListener(e -> {
            if (RegisterUtils.validateInput(
                    usernameField.getValue(),
                    emailField.getValue(),
                    passwordField.getValue(),
                    passwordConfirmationField.getValue()
            )) {
                register();
                Notification.show("Erfolgreich registriert");
                UI.getCurrent().navigate(Globals.Pages.LOGIN);
            } else {
                Notification.show("Validation failed");
            }
        });

        add(cancelButton, submitButton);
        setColspan(cancelButton, 1);
        setColspan(submitButton, 1);
    }

    private Button createButton(String text, ButtonVariant variant) {
        Button button = new Button(text);
        button.addThemeVariants(variant);
        return button;
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }

    protected abstract void register();
}