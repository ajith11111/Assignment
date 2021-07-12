package comn.firstproject.demo.springprac.assesment.entities;

import java.time.LocalTime;

public class ErrorObject {

    private LocalTime localTime;
    private String errorMessage;

    ErrorObject()
    {

    }

    public ErrorObject(LocalTime localTime, String errorMessage) {
        this.localTime = localTime;
        this.errorMessage = errorMessage;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
