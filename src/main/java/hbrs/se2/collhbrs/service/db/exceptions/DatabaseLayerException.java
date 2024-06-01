package hbrs.se2.collhbrs.service.db.exceptions;

public class DatabaseLayerException extends Exception {

    private String reason = null;

    public DatabaseLayerException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
