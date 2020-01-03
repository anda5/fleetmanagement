package fleet.fleet.exception;

import java.util.Date;
/**
 * Object created for keep time, message and details about new exception
 **/
public class ErrorDetail {
    private Date mTime;
    private String mMessage;
    private String mDetails;

    public ErrorDetail(Date timestamp, String message, String details) {
        super();
        mTime = timestamp;
        mMessage = message;
        mDetails = details;
    }

    public Date getTimestamp() {
        return mTime;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getDetails() {
        return mDetails;
    }
}
