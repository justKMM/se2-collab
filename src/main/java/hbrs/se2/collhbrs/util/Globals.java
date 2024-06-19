package hbrs.se2.collhbrs.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Globals {

    public static String CURRENT_USER = "current_user";
    public static final String BASE_URL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

    public static class Pages {
        public static final String MAIN = "";
        public static final String REMINDER = "reminder";
        public static final String LOGIN = "login";
        public static final String FORGOT_PASSWORD = "forgot-password";
        public static final String RESET_PASSWORD = "reset-password";
        public static final String SIGNUP = "signup";
        public static final String PROFIL_STUDENT = "student/profile-page";
        public static final String PROFIL_BUSINESS = "business/profile-page";
        public static final String BUSINESS_VACANCY = "business/add-vacancy";

    }

    public static class Roles {
        public static final String ADMIN = "admin";
        public static final String USER = "user";
        public static final String STUDENT = "student";
        public static final String BUSINESS = "business";
    }
}
