package hbrs.se2.collhbrs.views.authentification.registration;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.RouterLayout;
import hbrs.se2.collhbrs.service.registration.RegisterService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.util.RegisterUtils;

import java.util.stream.Stream;

public abstract class BaseRegistrationView extends FormLayout implements RouterLayout {

    protected final transient RegisterService registerService;
    protected TextField usernameField;
    protected PasswordField passwordField;
    protected EmailField emailField;
    protected PasswordField passwordConfirmationField;
    protected Button submitButton;
    protected Button cancelButton;
    protected Span errorMessageField;
    protected VerticalLayout verticalLayout;

    protected BaseRegistrationView(RegisterService registerService) {
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
        submitButton = new Button("Register");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelButton = new Button("Cancel");
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        errorMessageField = new Span();

        add(usernameField, 2);
        add(emailField, 2);
        add(passwordField, 1);
        add(passwordConfirmationField, 1);
    }

    private void addButtons() {
        submitButton.addClickListener(e -> register());
        cancelButton.addClickListener(e -> UI.getCurrent().navigate(Globals.Pages.LOGIN));
        add(errorMessageField);
        add(new HorizontalLayout(cancelButton, submitButton));
    }

    protected abstract void register();
}