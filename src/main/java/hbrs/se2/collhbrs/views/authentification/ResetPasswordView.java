package hbrs.se2.collhbrs.views.authentification;

import com.vaadin.flow.component.Composite;
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
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import hbrs.se2.collhbrs.service.ResetPasswordService;
import hbrs.se2.collhbrs.util.Globals;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Route(Globals.Pages.RESET_PASSWORD)
@CssImport("./styles/index.css")
@AnonymousAllowed
public class ResetPasswordView extends Composite<VerticalLayout> implements HasUrlParameter<String> {

    final
    ResetPasswordService resetPasswordService;
    private final Button submitButton = createButton("Confirm", ButtonVariant.LUMO_PRIMARY);
    private final Button cancelButton = createButton("Cancel", ButtonVariant.LUMO_ERROR);
    FormLayout formLayout;
    private Map<String, List<String>> parameters;
    private PasswordField password;
    private PasswordField passwordConfirmation;

    public ResetPasswordView(ResetPasswordService resetPasswordService) {
        setupLayout();
        setupFields();
        addButtons();

        getContent().add(formLayout);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, formLayout);
        this.resetPasswordService = resetPasswordService;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        parameters = queryParameters.getParameters();
    }

    private String finishResetPassword(String newPassword) {
        String token = parameters.get("token").get(0);
        return resetPasswordService.resetPassword(token, newPassword);
    }

    private Button createButton(String text, ButtonVariant... variants) {
        Button button = new Button(text);
        button.addThemeVariants(variants);
        button.addClassName("button-layout");
        return button;
    }

    private void setupLayout() {
        addClassName("reset-password");
        getContent().setWidth("100%");
        formLayout = new FormLayout();
        formLayout.setMaxWidth("500px");
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );
    }

    private void setupFields() {
        H3 title = new H3("Reset your password");

        password = new PasswordField("Password");
        passwordConfirmation = new PasswordField("Confirm password");

        setRequiredIndicatorVisible(password, passwordConfirmation);

        Span errorMessageField = new Span();

        formLayout.add(title, password, passwordConfirmation, errorMessageField);
        formLayout.setColspan(title, 2);
        formLayout.setColspan(password, 2);
        formLayout.setColspan(passwordConfirmation, 2);
        formLayout.setColspan(errorMessageField, 2);
    }

    private void addButtons() {
        cancelButton.addClickListener(e -> {
            Notification notification = Notification.show("Password reset cancelled");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            UI.getCurrent().navigate(Globals.Pages.LOGIN);
        });

        submitButton.addClickListener(e -> {
            String newPassword = password.getValue();
            String confirmPassword = passwordConfirmation.getValue();
            if (!newPassword.equals(confirmPassword)) {
                Notification notification = Notification.show("""
                        Error: Password doesn't match
                        with confirmation password.
                        Please try again.
                        """);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                String status = finishResetPassword(newPassword);
                Notification notification = Notification.show(status);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate(Globals.Pages.LOGIN);
            }
        });

        formLayout.add(cancelButton, submitButton);
        formLayout.setColspan(cancelButton, 1);
        formLayout.setColspan(submitButton, 1);
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }

}
