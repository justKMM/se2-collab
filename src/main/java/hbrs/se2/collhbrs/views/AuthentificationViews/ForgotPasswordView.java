package hbrs.se2.collhbrs.views.AuthentificationViews;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.entity.Email;
import hbrs.se2.collhbrs.service.EmailService;
import hbrs.se2.collhbrs.util.Globals;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;

@Route(value = Globals.Pages.FORGOT_PASSWORD)
@CssImport("./styles/index.css")
public class ForgotPasswordView extends FormLayout {
    public ForgotPasswordView(@Qualifier("javaMailSender") JavaMailSender javaMailSender) {
        setupLayout();
        setupFields(javaMailSender);
    }

    private void setupLayout() {
        addClassName("forgot_password_view");
        setMaxWidth("500px");
        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP)
        );
    }

    private void setupFields(@Qualifier("javaMailSender") JavaMailSender javaMailSender) {
        H3 title = new H3("Reset Password");
        // Email Input Field
        EmailField emailField = new EmailField();
        emailField.setId("email");
        emailField.setLabel("Email");
        emailField.setRequiredIndicatorVisible(true);
        emailField.setErrorMessage("E-mail is required");
        emailField.setPlaceholder("muster@musterdomain.com");
        // Send reset password email button
        Button sendMailButton = new Button("Reset Password");
        sendMailButton.addClickListener(e -> {
            String recipient = emailField.getValue();
            Email email = new Email(recipient, "Click here to reset password:\n[URL]\n\nAldaringhausen Klangkreationen GmbH\n[Impressum]", "collhbrs - Reset Your Password", "");
            EmailService emailService = new EmailService(javaMailSender);
            String status = emailService.sendSimpleMail(email);
            Notification.show(status);
            UI.getCurrent().navigate(Globals.Pages.LOGIN);
        });
        // Cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancelButton.addClickListener(e -> {
            UI.getCurrent().navigate(Globals.Pages.LOGIN);
        });

        add(title, emailField, sendMailButton, cancelButton);
        setColspan(title, 2);
        setColspan(emailField, 2);
    }
}
