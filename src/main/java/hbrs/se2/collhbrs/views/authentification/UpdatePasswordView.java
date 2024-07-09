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

@Route(value = Globals.Pages.UPDATE_PASSWORD, layout = AppView.class)
@PageTitle("Update Password")
@CssImport("./styles/index.css")
@PermitAll
public class UpdatePasswordView extends VerticalLayout {

    private final transient LoginService loginService;
    private final SessionService sessionService;

    public UpdatePasswordView(LoginService loginService, SessionService sessionService) {
        this.loginService = loginService;
        this.sessionService = sessionService;

        setupLayout();
    }

    private void setupLayout() {
        PasswordField oldPassword = new PasswordField("altes Passwort");
        PasswordField newPassword = new PasswordField("neues Passwort");
        PasswordField confirmPassword = new PasswordField("Passwort bestätigen");
        Button updateButton = new Button("Aktualisieren");

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setWidthFull();
        setHeightFull();
        add(oldPassword, newPassword, confirmPassword, updateButton);

        updateButton.addClickListener(event -> updatePassword(oldPassword, newPassword, confirmPassword));
    }

    private void updatePassword(PasswordField oldPassword, PasswordField newPassword, PasswordField confirmPassword) {
        String oldPasswordValue = oldPassword.getValue();
        String newPasswordValue = newPassword.getValue();
        String confirmPasswordValue = confirmPassword.getValue();

        if (!newPasswordValue.equals(confirmPasswordValue)) {
            Notification.show("Passwörter stimmen nicht überein").addThemeVariants(NotificationVariant.LUMO_ERROR);
            return;
        }

        if (sessionService.getCurrentUser() != null && loginService.checkPassword(sessionService.getCurrentUser().getUser(), oldPasswordValue)) {
            loginService.updatePassword(sessionService.getCurrentUser().getUser(), newPasswordValue);
            Notification.show("Passwort erfolgreich geändert").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            UI.getCurrent().navigate(AppView.class);
        } else {
            Notification.show("Das aktuelle Passwort ist falsch").addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}