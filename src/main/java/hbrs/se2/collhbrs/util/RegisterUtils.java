package hbrs.se2.collhbrs.util;

import com.vaadin.flow.component.notification.Notification;

import java.util.regex.Pattern;

public class RegisterUtils {

    private static final Pattern FIRST_NAME_PATTERN = Pattern.compile("^[A-Za-z\\s-]{3,30}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$");
    private static final Pattern COMPANY_NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]{3,}[A-Za-z\\d\\s]*$");
    private static final Pattern LAST_NAME_PATTERN = Pattern.compile("^[A-Za-z]{3,30}$");

    private RegisterUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static boolean validateInput(String username, String firstName, String lastName, String email, String password, String passwordConfirmation) {
        if (firstName.isEmpty() || !isValidFirstName(firstName)) {
            Notification.show("Please enter a valid first name.");
            return false;
        }
        if (lastName.isEmpty() || !isValidLastName(lastName)) {
            Notification.show("Please enter a valid surname.");
            return false;
        }
        return checkDefaultInput(username, email, password, passwordConfirmation);
    }

    public static boolean validateInput(String username, String businessName, String email, String password, String passwordConfirmation) {
        if (businessName.isEmpty() || !isValidCompanyName(businessName)) {
            Notification.show("Please enter a valid Company name.");
            return false;
        }
        return checkDefaultInput(username, email, password, passwordConfirmation);
    }

    private static boolean checkDefaultInput(String username, String email, String password, String passwordConfirmation) {
        if (username.isEmpty() || !isValidUsername(username)) {
            Notification.show("Please enter a valid username.");
            return false;
        }
        if (email.isEmpty() || !isValidEmail(email)) {
            Notification.show("Please enter a valid e-mail address.");
            return false;
        }
        if (!password.equals(passwordConfirmation)) {
            Notification.show("Passwords do not match.");
            return false;
        } else if (!isPasswordComplex(password)) {
            Notification.show("The password must be 8-16 characters long and contain upper case letters, lower case letters and numbers.");
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private static boolean isPasswordComplex(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private static boolean isValidCompanyName(String companyName) {
        return COMPANY_NAME_PATTERN.matcher(companyName).matches();
    }

    private static boolean isValidFirstName(String firstName) {
        return FIRST_NAME_PATTERN.matcher(firstName).matches();
    }

    private static boolean isValidLastName(String lastName) {
        return LAST_NAME_PATTERN.matcher(lastName).matches();
    }

    private static boolean isValidUsername(String username) {
        return Pattern.matches("^[a-zA-Z0-9]+$", username) && username.length() < 20 && username.length() > 3;
    }
}
