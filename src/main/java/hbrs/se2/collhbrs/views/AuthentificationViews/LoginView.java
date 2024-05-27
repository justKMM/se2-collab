package hbrs.se2.collhbrs.views.AuthentificationViews;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import hbrs.se2.collhbrs.entity.Email;
import hbrs.se2.collhbrs.entity.User;
import hbrs.se2.collhbrs.service.EmailService;
import hbrs.se2.collhbrs.service.LoginService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.util.Components;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;

@Route(value = "")
@RouteAlias(value = Globals.Pages.LOGIN_ALIAS)
@CssImport("./styles/index.css")
public class LoginView extends VerticalLayout {

    private LoginI18n i18n;
    // not needed:  private static final String LOGIN_ROUTE = "main";
    public LoginView(LoginService loginService) {
        addClassName("main");
        setSizeFull();
        // Language Switcher
        Components.LANGUAGE_SELECT.setItems(Globals.LANGUAGE.DEUTSCH, Globals.LANGUAGE.ENGLISH);
        Components.LANGUAGE_SELECT.setLabel("Select Language");
        Components.LANGUAGE_SELECT.setValue(Globals.CURRENT_LANGUAGE);
        // Add the language selection dropdown to the login form
        add(Components.LANGUAGE_SELECT);
        // Login form
        LoginForm component = createLoginForm();
        add(component);
        this.setAlignItems(Alignment.CENTER);
        // Registration buttons
        RouterLink studentRegisterLink = new RouterLink("Als Student registrieren", StudentRegistrationView.class);
        RouterLink unternehmenRegisterLink = new RouterLink("Als Unternehmen registrieren", BusinessRegistrationView.class);
        add(studentRegisterLink);
        add(unternehmenRegisterLink);
        // Login Listener
        component.addLoginListener(input -> handleLogin(input, loginService));
    }

    // TODO: Fehler Message fürs Username & Passwort Feld wenn man keins eingibt übersetzen

    private LoginForm createLoginForm() {
        LoginForm component = new LoginForm();
        i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();
        // Default error messages
        LoginI18n.ErrorMessage errorMessage = i18n.getErrorMessage();
        errorMessage.setUsername("Benutzername kann nicht leer sein");
        errorMessage.setPassword("Passwort kann nicht leer sein");
        // Language Change Listener
        Components.LANGUAGE_SELECT.addValueChangeListener(e -> {
            Globals.LANGUAGE selectedLanguage = e.getValue();
            if (Globals.LANGUAGE.DEUTSCH.equals(selectedLanguage)) {
                setGermanLanguage(i18nForm);
            } else if (Globals.LANGUAGE.ENGLISH.equals(selectedLanguage)) {
                setEnglishLanguage(i18nForm);
            }
            i18n.setForm(i18nForm);
            component.setI18n(i18n);
        });
        component.addForgotPasswordListener(e -> {
           UI.getCurrent().navigate(Globals.Pages.FORGOT_PASSWORD);
        });
        return component;
    }

    private void handleLogin(LoginForm.LoginEvent input, LoginService loginService) {
        String successLogIn = "Erfolgreich angemeldet als ";
        String blacklistedLogIn = "Anmelde fehlgeschlagen: Benutzer wurde schwarzgelistet ";
        String failedLogIn = "Anmelde fehlgeschlagen ";
        if (Globals.CURRENT_LANGUAGE.equals(Globals.LANGUAGE.ENGLISH)) {
            successLogIn = "Successfully logged in as ";
            blacklistedLogIn = "Login failed: User is blacklisted ";
            failedLogIn = "Login failed";
        }
        try {
            User user = loginService.getUser(input.getUsername(), input.getPassword());
            Notification notificationSuccess = Notification.show(successLogIn + user.getUsername());
            notificationSuccess.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            if (user.getBlacklisted() == 1) {
                Notification notificationBlacklisted = Notification.show(blacklistedLogIn);
                notificationBlacklisted.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }
            notificationSuccess.open();
            UI.getCurrent().navigate(Globals.Pages.MAIN);
        } catch (Exception e) {
            Notification notificationFailed = Notification.show(failedLogIn);
            notificationFailed.addThemeVariants(NotificationVariant.LUMO_ERROR);
            e.printStackTrace();
        }
    }

    private void setGermanLanguage(LoginI18n.Form i18nForm) {
        Globals.CURRENT_LANGUAGE = Globals.LANGUAGE.DEUTSCH;
        i18nForm.setTitle("Anmelden");
        i18nForm.setUsername("Benutzername");
        i18nForm.setPassword("Passwort");
        i18nForm.setSubmit("Anmelden");
        i18nForm.setForgotPassword("Passwort vergessen?");
        // Error Messages
        LoginI18n.ErrorMessage errorMessage = i18n.getErrorMessage();
        errorMessage.setUsername("Benutzername kann nicht leer sein");
        errorMessage.setPassword("Passwort kann nicht leer sein");
        i18n.setErrorMessage(errorMessage);
    }

    private void setEnglishLanguage(LoginI18n.Form i18nForm) {
        Globals.CURRENT_LANGUAGE = Globals.LANGUAGE.ENGLISH;
        i18nForm.setTitle("Login");
        i18nForm.setUsername("Username");
        i18nForm.setPassword("Password");
        i18nForm.setSubmit("Log in");
        i18nForm.setForgotPassword("Forgot password?");
        // Error Messages
        LoginI18n.ErrorMessage errorMessage = i18n.getErrorMessage();
        errorMessage.setUsername("Username can't be blank");
        errorMessage.setPassword("Password can't be blank");
        i18n.setErrorMessage(errorMessage);
    }
}