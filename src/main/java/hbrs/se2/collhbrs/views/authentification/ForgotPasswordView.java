package hbrs.se2.collhbrs.views.authentification;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import hbrs.se2.collhbrs.service.ResetPasswordService;
import hbrs.se2.collhbrs.util.Globals;

@Route(value = Globals.Pages.FORGOT_PASSWORD)
@CssImport("./styles/index.css")
@AnonymousAllowed
public class ForgotPasswordView extends Composite<VerticalLayout> {

    final transient
    ResetPasswordService resetPasswordService;

    FormLayout formLayout;

    public ForgotPasswordView(ResetPasswordService resetPasswordService) {
        setupLayout();
        setupFields();
        this.resetPasswordService = resetPasswordService;
    }

    private void setupLayout() {
        addClassName("reset-password");
        getContent().setWidth("100%");
        formLayout = new FormLayout();
        formLayout.setWidth("500px");
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );
    }

    private void setupFields() {
        H3 title = new H3("Passwort zurücksetzen");
        // Email Input Field
        EmailField emailField = new EmailField();
        emailField.setId("email");
        emailField.setLabel("Email");
        emailField.setRequiredIndicatorVisible(true);
        emailField.setErrorMessage("E-Mail ist erforderlich");
        emailField.setPlaceholder("beispiel@domain.de");
        // Send reset password email button
        Button sendMailButton = new Button("Passwort zurücksetzen");
        sendMailButton.addClickListener(e -> {
            String recipient = emailField.getValue();
            String status = resetPasswordService.sendResetPasswordMail(recipient);
            Notification.show(status);
            UI.getCurrent().navigate(Globals.Pages.LOGIN);
        });
        // Cancel button
        Button cancelButton = new Button("Abbrechen");
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancelButton.addClickListener(e -> UI.getCurrent().navigate(Globals.Pages.LOGIN));
        formLayout.add(title, emailField, cancelButton, sendMailButton);
        formLayout.setColspan(title, 2);
        formLayout.setColspan(emailField, 2);
        getContent().add(formLayout);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, formLayout);
    }
}
