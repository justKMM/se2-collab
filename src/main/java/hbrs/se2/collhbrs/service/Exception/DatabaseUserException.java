package hbrs.se2.collhbrs.service.Exception;

/**
 * @author dwang2s
 */
public class DatabaseUserException extends Exception {
    private String reason = null;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public DatabaseUserException( String reason ) {
        this.reason = reason;
    }
}
