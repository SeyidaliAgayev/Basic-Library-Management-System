package exception;

import enums.ExceptionEnum;

public class CustomerNotFound extends RuntimeException {
    private String message;

    public CustomerNotFound(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.message = exceptionEnum.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
