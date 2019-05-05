package library.domain;

public class AdmissionException extends RuntimeException {
    private ErrorCode errorCode;

    public AdmissionException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getCode() {
        return errorCode;
    }
}
