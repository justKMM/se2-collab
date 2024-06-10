package hbrs.se2.collhbrs.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Globals {

    public static String CURRENT_USER = "current_user";
    public static final String BASE_URL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

    public static class Pages {
        public static final String MAIN = "main";
        public static final String REMINDER = "reminder";
        public static final String LOGIN = "";
        public static final String FORGOT_PASSWORD = "forgot-password";
        public static final String RESET_PASSWORD = "reset-password";
        public static final String SIGNUP = "signup";
        public static final String PROFILSTUDENT = "main/student/profile-page";
        public static final String PROFILBUSSINESS = "main/bussiness/profile-page";
        public static final String BUSINESSVACANCY = "main/bussiness/addvacancy";

    }
}
