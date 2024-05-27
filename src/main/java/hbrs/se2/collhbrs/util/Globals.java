package hbrs.se2.collhbrs.util;
/*
    Klasse beinhaltet alle Routenamen der Views
    (sollte noch alle Rollen und später spzielle Errors bekommen)

    siehe carlook

 */
public class Globals {

    //Current User
    public static String CURRENT_USER = "current_user";

    // Current language
    public static LANGUAGE CURRENT_LANGUAGE = LANGUAGE.DEUTSCH;
    public enum LANGUAGE {
        DEUTSCH {
            public String toString() {
                return "Deutsch";
            }
        },
        ENGLISH {
            public String toString() {
                return "English";
            }
        };
    };

    // Name jeder Page
    public static class Pages{
        public static final String MAIN = "main";
        public static final String MERKZETTEL = "merkzettel";
        public static final String LOGIN = "";
        public static final String LOGIN_ALIAS = "login";
        public static final String FORGOT_PASSWORD = "forgot_password";
        public static final String STUDENTREGISTRATION = "student/registration";
        public static final String UNTERNEHMENREGISTRATION = "unternehmen/registration";
        public static final String PROFILSTUDENT = "profile-page";
    }



}
