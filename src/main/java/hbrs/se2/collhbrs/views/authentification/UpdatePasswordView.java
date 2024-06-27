package hbrs.se2.collhbrs.views.authentification;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.service.LoginService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = Globals.Pages.UPDATE_PASSWORD,  layout = AppView.class)
@PageTitle("Update Password")
@CssImport("./styles/index.css")
@PermitAll
public class UpdatePasswordView extends VerticalLayout {
    private final PasswordField oldPassword = new PasswordField("Old Password");
    private final PasswordField newPassword = new PasswordField("New Password");
    private final PasswordField confirmPassword = new PasswordField("Confirm Password");

    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionService sessionService;

    public UpdatePasswordView() {
        Button updateButton = new Button("Update");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setWidthFull();
        setHeightFull();
        add(oldPassword, newPassword, confirmPassword, updateButton);
        updateButton.addClickListener(event -> updatePassword());
    }

    private void updatePassword() {
        String oldPasswordValue = oldPassword.getValue();
        String newPasswordValue = newPassword.getValue();
        String confirmPasswordValue = confirmPassword.getValue();

        if (!newPasswordValue.equals(confirmPasswordValue)) {
            Notification.show("Passwords do not match").addThemeVariants(NotificationVariant.LUMO_ERROR);
            return;
        }

        if (sessionService.getCurrentUser() != null && loginService.checkPassword(sessionService.getCurrentUser().getUser(), oldPasswordValue)) {
            loginService.updatePassword(sessionService.getCurrentUser().getUser(), newPasswordValue);
            Notification.show("Password updated successfully").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            UI.getCurrent().navigate(AppView.class);
        } else {
            Notification.show("Current password is incorrect").addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}
