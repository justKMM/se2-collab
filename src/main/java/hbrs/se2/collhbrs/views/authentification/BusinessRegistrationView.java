package hbrs.se2.collhbrs.views.authentification;

import hbrs.se2.collhbrs.service.registration.RegisterProxy;
import hbrs.se2.collhbrs.service.registration.RegisterService;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import hbrs.se2.collhbrs.service.registration.RegisterServiceImpl;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.util.RegisterUtils;

import java.util.stream.Stream;

@CssImport("./styles/index.css")
public class BusinessRegistrationView extends FormLayout {

    private final transient RegisterService registerService;
    private final TextField businessName = new TextField("Business name");
    private final TextField username = new TextField("Username");
    private final EmailField email = new EmailField("Email");
    private final PasswordField password = new PasswordField("Password");
    private final PasswordField passwordConfirmation = new PasswordField("Confirm password");
    private final Button submitButton = createButton("Register", ButtonVariant.LUMO_PRIMARY);
    private final Button cancelButton = createButton("Cancel", ButtonVariant.LUMO_ERROR);

    public BusinessRegistrationView(RegisterService registerService) {
        this.registerService = new RegisterProxy((RegisterServiceImpl) registerService);
        setupLayout();
        setupFields();
        addButtons();
    }

    private Button createButton(String text, ButtonVariant variant) {
        Button button = new Button(text);
        button.addThemeVariants(variant);
        return button;
    }

    private void setupLayout() {
        setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("500px", 2)
        );
        addClassName("business-form");
    }

    private void setupFields() {
        H3 title = new H3("Register your business");
        setRequiredIndicatorVisible(username, email, password, passwordConfirmation, businessName);

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
            Notification notification = Notification.show("Registration cancelled");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            UI.getCurrent().navigate(Globals.Pages.LOGIN);
        });

        submitButton.addClickListener(e -> {
            if (RegisterUtils.validateInput(
                    username.getValue(),
                    businessName.getValue(),
                    email.getValue(),
                    password.getValue(),
                    passwordConfirmation.getValue()
            )) {
                try {
                    registerService.registerBusiness(
                            username.getValue(),
                            password.getValue(),
                            email.getValue(),
                            businessName.getValue()
                    );
                    Notification notification = Notification.show("Registration successful");
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    UI.getCurrent().navigate(Globals.Pages.LOGIN);
                } catch (RuntimeException runtimeException) {
                    Notification.show(runtimeException.getMessage());
                }
            }
        });

        add(cancelButton, submitButton);
        setColspan(cancelButton, 1);
        setColspan(submitButton, 1);
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}