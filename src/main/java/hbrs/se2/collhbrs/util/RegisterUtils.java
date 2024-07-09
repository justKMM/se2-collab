package hbrs.se2.collhbrs.util;

import com.vaadin.flow.component.notification.Notification;

public class RegisterUtils {

    private RegisterUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static boolean validateInput(String username, String firstName, String lastName, String email, String password, String passwordConfirmation) {
        if (!isValidFirstName(firstName)) {
            Notification.show("Bitte geben Sie einen gültigen Vornamen ein (3-30 Zeichen, nur Buchstaben, Leerzeichen und Bindestriche).");
            return false;
        }
        if (!isValidLastName(lastName)) {
            Notification.show("Bitte geben Sie einen gültigen Vornamen ein (3-30 Zeichen, nur Buchstaben, Leerzeichen und Bindestriche).");
            return false;
        }
        return checkDefaultInput(username, email, password, passwordConfirmation);
    }

    public static boolean validateInput(String username, String businessName, String email, String password, String passwordConfirmation) {
        if (!isValidCompanyName(businessName)) {
            Notification.show("Bitte geben Sie einen gültigen Firmennamen ein (mindestens 3-30 Zeichen, nur Buchstaben, Leerzeichen und Ziffern).");
            return false;
        }
        return checkDefaultInput(username, email, password, passwordConfirmation);
    }

    public static boolean validateInput(String username, String email, String password, String passwordConfirmation) {
        return checkDefaultInput(username, email, password, passwordConfirmation);
    }

    private static boolean checkDefaultInput(String username, String email, String password, String passwordConfirmation) {
        if (!isValidUsername(username)) {
            Notification.show("Bitte geben Sie einen gültigen Benutzernamen ein (4-20 Zeichen, nur Buchstaben und Ziffern).");
            return false;
        }
        if (!isValidEmail(email)) {
            Notification.show("Bitte geben Sie eine gültige E-Mail Adresse ein.");
            return false;
        }
        if (!password.equals(passwordConfirmation)) {
            Notification.show("Die Passwörter stimmen nicht überein.");
            return false;
        }
        if (!isPasswordComplex(password)) {
            Notification.show("Das Passwort muss 8-16 Zeichen lang sein und mindestens einen Großbuchstaben, einen Kleinbuchstaben und eine Zahl enthalten.");
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        if (atIndex <= 0 || dotIndex <= atIndex + 1 || dotIndex >= email.length() - 1) {
            return false;
        }

        for (char c : email.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '@' && c != '.' && c != '_' && c != '-' && c != '+') {
                return false;
            }
        }
        return true;
    }

    private static boolean isPasswordComplex(String password) {
        if (password.length() < 8 || password.length() > 16) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
        }

        return hasUpper && hasLower && hasDigit;
    }

    private static boolean isValidCompanyName(String companyName) {
        if (companyName.length() < 3) {
            return false;
        }

        for (char c : companyName.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidFirstName(String firstName) {
        return isValidName(firstName);
    }

    private static boolean isValidLastName(String lastName) {
        return isValidName(lastName);
    }

    private static boolean isValidName(String name) {
        if (name.length() < 3 || name.length() > 30) {
            return false;
        }

        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ' && c != '-') {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidUsername(String username) {
        if (username.length() < 4 || username.length() > 20) {
            return false;
        }

        for (char c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }
}