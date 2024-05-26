package hbrs.se2.collhbrs.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import hbrs.se2.collhbrs.entity.User;
import hbrs.se2.collhbrs.service.LoginService;
import hbrs.se2.collhbrs.util.Globals;

@Route(value = "")
@RouteAlias(value = Globals.Pages.LOGIN_ALIAS)
@CssImport("./styles/index.css")
public class LoginView extends VerticalLayout {

    // not needed:  private static final String LOGIN_ROUTE = "main";

    public LoginView(LoginService loginService) {
        addClassName("main");
        setSizeFull();

        LoginForm component = createLoginForm();
        add(component);
        this.setAlignItems(Alignment.CENTER);
        RouterLink studentRegisterLink = new RouterLink("Als Student registrieren", StudentRegistrationView.class);
        RouterLink unternehmenRegisterLink = new RouterLink("Als Unternehmen registrieren", BusinessRegistrationView.class);
        add(studentRegisterLink);
        add(unternehmenRegisterLink);
        component.addLoginListener(input -> handleLogin(input, loginService));
    }

    // TODO: Fehler Message fürs Username & Passwort Feld wenn man keins eingibt übersetzen

    private LoginForm createLoginForm() {
        LoginForm component = new LoginForm();
        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();


        // Set the default language to German
        setGermanLanguage(i18nForm);

        i18n.setForm(i18nForm);
        component.setI18n(i18n);

        // Add a language selection dropdown with German and English options
        Select<Globals.LANGUAGE> languageSelect = new Select<>();
        languageSelect.setItems(Globals.LANGUAGE.DEUTSCH, Globals.LANGUAGE.ENGLISH);
        languageSelect.setLabel("Select Language");
        languageSelect.setValue(Globals.LANGUAGE.DEUTSCH); // Set default language to German

        languageSelect.addValueChangeListener(event -> {
            Globals.LANGUAGE selectedLanguage = event.getValue();
            if (Globals.LANGUAGE.DEUTSCH.equals(selectedLanguage)) {
                setGermanLanguage(i18nForm);
            } else if (Globals.LANGUAGE.ENGLISH.equals(selectedLanguage)) {
                setEnglishLanguage(i18nForm);
            }
            i18n.setForm(i18nForm);
            component.setI18n(i18n);
        });
        // Add the language selection dropdown to the login form
        add(languageSelect);
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
    }

    private void setEnglishLanguage(LoginI18n.Form i18nForm) {
        Globals.CURRENT_LANGUAGE = Globals.LANGUAGE.ENGLISH;
        i18nForm.setTitle("Login");
        i18nForm.setUsername("Username");
        i18nForm.setPassword("Password");
        i18nForm.setSubmit("Log in");
        i18nForm.setForgotPassword("Forgot password?");
    }
}