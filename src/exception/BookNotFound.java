package exception;

import enums.ExceptionEnum;

public class BookNotFound extends RuntimeException {
    private String message;

    public BookNotFound(ExceptionEnum exceptionEnum) {
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
