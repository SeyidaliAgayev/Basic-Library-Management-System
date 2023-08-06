package service;

public interface CustomerService {
    boolean signUp();
    void logIn();
    boolean updateCustomer();
    boolean deleteCustomer();
    void searchCustomer();
    void showCustomer();
}
