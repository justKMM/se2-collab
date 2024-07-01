package hbrs.se2.collhbrs.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


public class Globals {

    public static final String CURRENT_USER = "current_user";
    public static final String BASE_URL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    private static final String EXCEPTION_1 = "This is a utility class and cannot be instantiated";
    private Globals() {
        throw new UnsupportedOperationException(EXCEPTION_1);
    }

    public static class Pages {

        public static final String MAIN = "";
        public static final String APP = "main/";
        public static final String REMINDER = "reminder";
        public static final String LOGIN = "login";
        public static final String FORGOT_PASSWORD = "forgot/password";
        public static final String RESET_PASSWORD = "reset/password";
        public static final String UPDATE_PASSWORD = "update/password";
        public static final String SIGNUP = "signup";
        public static final String PROFIL_STUDENT = "student/profile";
        public static final String PROFIL_BUSINESS = "business/profile";
        public static final String SHOW_APPLICATION = "business/show/application";
        public static final String VACANCY = "business/vacancy";
        public static final String SEARCH_STUDENT = "student/search";
        private Pages() {
            throw new UnsupportedOperationException(EXCEPTION_1);
        }
    }

    public static class Roles {

        public static final String STUDENT = "student";
        public static final String BUSINESS = "business";
        private Roles() {
            throw new UnsupportedOperationException(EXCEPTION_1);
        }
    }
}
