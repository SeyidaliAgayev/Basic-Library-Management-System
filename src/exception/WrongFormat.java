package exception;

import enums.ExceptionEnum;

public class WrongFormat extends RuntimeException{
    private String message;

    public WrongFormat(ExceptionEnum exceptionEnum) {
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
