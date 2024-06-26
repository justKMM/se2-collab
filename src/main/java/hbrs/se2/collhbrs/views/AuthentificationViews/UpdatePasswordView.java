package hbrs.se2.collhbrs.views.AuthentificationViews;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.NotificationVariant;
import hbrs.se2.collhbrs.service.LoginService;
import hbrs.se2.collhbrs.model.entity.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
@PermitAll
@Route("update-password")
@PageTitle("Update Password")
@CssImport("./styles/index.css")
public class UpdatePasswordView extends VerticalLayout {
    private final PasswordField oldPassword = new PasswordField("Old Password");
    private final PasswordField newPassword = new PasswordField("New Password");
    private final PasswordField confirmPassword = new PasswordField("Confirm Password");

    @Autowired
    private LoginService loginService;

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

        User currentUser = (User) VaadinSession.getCurrent().getAttribute("currentUser");

        if (currentUser != null && loginService.checkPassword(currentUser, oldPasswordValue)) {
            loginService.updatePassword(currentUser, newPasswordValue);
            Notification.show("Password updated successfully").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        } else {
            Notification.show("Current password is incorrect").addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}
