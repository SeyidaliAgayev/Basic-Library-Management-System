package enums;

public enum ExceptionEnum {
    WRONG_FORMAT("Wrong Format"),
    BOOK_NOT_FOUND("Book not found"),
    INVALID_OPTION("Invalid option"),
    EMPTY_LIST("Empty list"),
    CUSTOMER_NOT_FOUND("Customer not found"),
    LIMIT_EXCEEDED("Limit exceeded!");

    private String message;

    ExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
