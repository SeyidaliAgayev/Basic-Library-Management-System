package exception;

import enums.ExceptionEnum;

public class InvalidOption extends RuntimeException{
    private String message;

    public InvalidOption(ExceptionEnum exceptionEnum) {
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
