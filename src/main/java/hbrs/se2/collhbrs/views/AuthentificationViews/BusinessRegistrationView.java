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
import hbrs.se2.collhbrs.service.RegisterService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.util.RegisterUtils;

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
    private PasswordField passwordConfirmation;

    public BusinessRegistrationView(RegisterService registerService) {
        this.registerService = registerService;
        setupLayout();
        setupFields();
        addButtons();
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
        passwordConfirmation = new PasswordField("Confirm password");

        setRequiredIndicatorVisible(businessName, username, email, password, passwordConfirmation);

        Span errorMessageField = new Span();

        add(title, businessName, username, email, password, passwordConfirmation, errorMessageField);
        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(username, 2);
        setColspan(errorMessageField, 2);
        setColspan(businessName, 2);
    }

    private void addButtons() {

        cancelButton.addClickListener(e -> {
            Notification.show("Registration cancelled");
            UI.getCurrent().navigate(Globals.Pages.LOGIN);
        });

        submitButton.addClickListener(e -> {
            if (RegisterUtils.validateInput(
                    username.getValue(),
                    businessName.getValue(),
                    email.getValue(),
                    password.getValue(),
                    passwordConfirmation.getValue()
            ))
            {
                registerService.registerUser(
                        username.getValue(),
                        password.getValue(),
                        email.getValue(),
                        businessName.getValue()
                );
                Notification.show("Registration successful");
                UI.getCurrent().navigate(Globals.Pages.LOGIN);
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

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
