package service.impl;

import enums.ExceptionEnum;
import enums.StatusEnum;
import exception.*;
import globalData.GlobalDataCustomer;
import globalData.GlobalDataLibrary;
import model.Customer;
import service.CustomerService;
import static util.InputUtil.*;
import static helper.CustomerServiceHelper.*;
import static util.MenuUtil.*;
import static helper.CustomerServiceHelper.bookRentHelper;

public class ICustomerService implements CustomerService {
    @Override
    public boolean signUp() {
        int customerCount = intInput("How many customer will be registered: ");
        if (GlobalDataCustomer.customers == null){
            GlobalDataCustomer.customers = new Customer[customerCount];
            for (int i = 0; i < GlobalDataCustomer.customers.length; i++) {
                signUpHelper();
            }
            System.out.println(StatusEnum.SIGN_UP_SUCCESSFULLY);
        } else {
            Customer[] tempCustomer = GlobalDataCustomer.customers;
            GlobalDataCustomer.customers = new Customer[GlobalDataCustomer.customers.length + customerCount];
            for (int i = 0; i < GlobalDataCustomer.customers.length; i++) {
                if (i < tempCustomer.length) {
                    GlobalDataCustomer.customers[i] = tempCustomer[i];
                } else {
                    signUpHelper();
                }
            }
            System.out.println(StatusEnum.SIGN_UP_SUCCESSFULLY);
        }
        int nullCustomerCount = 0;
        for (Customer customer:GlobalDataCustomer.customers) {
            if (customer == null) {
                nullCustomerCount++;
            }
        }
        Customer[] customers = GlobalDataCustomer.customers;
        GlobalDataCustomer.customers = new Customer[GlobalDataCustomer.customers.length -  nullCustomerCount];
        for (int i = 0; i < GlobalDataCustomer.customers.length; i++) {
            GlobalDataCustomer.customers[i] = customers[i];
        }
        return true;
    }

    @Override
    public void logIn() {
        String username = stringInput("Enter the username: ");
        String password = stringInput("Enter the password: ");

        for (int i = 0; i < GlobalDataCustomer.customers.length; i++) {
            if (GlobalDataCustomer.customers[i].getUserName().equals(username) && GlobalDataCustomer.customers[i].getPassword().equals(password)){
                System.out.println(StatusEnum.LOG_IN_SUCCESSFULLY);
                for (int j = 0; j < GlobalDataLibrary.libraries.length; j++) {
                    if (GlobalDataLibrary.libraries[j].getStockStatus() == 1 && GlobalDataLibrary.libraries[j].getCount() >= 1) {
                        System.out.println(GlobalDataLibrary.libraries[j].toString());
                    }
                }
                bookRentHelper(i);
            } else {
                throw new CustomerNotFound(ExceptionEnum.CUSTOMER_NOT_FOUND);
            }
        }

    }


    @Override
    public boolean updateCustomer() {
        if (GlobalDataCustomer.customers == null || GlobalDataCustomer.customers.length == 0)
            throw new EmptyList(ExceptionEnum.EMPTY_LIST);
        int customerId = intInput("Which customer do you want to update: ");
        boolean isTrue = false;

        for (int i = 0; i < GlobalDataCustomer.customers.length; i++) {
            if (GlobalDataCustomer.customers[i].getCustomerId() == customerId){
                String parameter = stringInput("Which information of customer do you want to update: ");
                String[] parameterString = parameter.toLowerCase().split(",");
                for (String str: parameterString) {
                    switch (str) {
                        case "name":
                            GlobalDataCustomer.customers[i].setName("Enter the changed name: ");
                            isTrue = true;
                            break;
                        case "surname":
                            GlobalDataCustomer.customers[i].setSurname("Enter the changer surname: ");
                            isTrue = true;
                            break;
                        case "username":
                            GlobalDataCustomer.customers[i].setUserName("Enter the changed username: ");
                            isTrue = true;
                            break;
                        case "password":
                            GlobalDataCustomer.customers[i].setPassword("Enter the changed password: ");
                            isTrue = true;
                            break;
                        default:
                            throw new CustomerNotFound(ExceptionEnum.CUSTOMER_NOT_FOUND);
                    }
                }
                if (isTrue){
                    System.out.println(StatusEnum.UPDATE_SUCCESSFULLY);
                } else {
                    throw new CustomerNotFound(ExceptionEnum.CUSTOMER_NOT_FOUND);
                }
            }
        }
        return true;
    }

    @Override
    public boolean deleteCustomer() {
        if (GlobalDataCustomer.customers == null || GlobalDataCustomer.customers.length == 0)
            throw new EmptyList(ExceptionEnum.EMPTY_LIST);
        int customerId = intInput("Which customer do you want to delete: ");

        for (int i = 0; i < GlobalDataCustomer.customers.length; i++) {
            if (GlobalDataCustomer.customers[i].getCustomerId() == customerId){
                Customer[] customers = GlobalDataCustomer.customers;
                GlobalDataCustomer.customers = new Customer[GlobalDataCustomer.customers.length - 1];

                int k = 0;
                for (Customer customer:GlobalDataCustomer.customers) {
                    if (customer.getCustomerId() == customerId) {
                        continue;
                    }
                    GlobalDataCustomer.customers[k] = customer;
                    k++;
                }
                System.out.println(StatusEnum.DELETE_SUCCESSFULLY);
                break;
            }
        }
        return false;
    }

    @Override
    public void searchCustomer() {
        if (GlobalDataCustomer.customers == null || GlobalDataCustomer.customers.length == 0)
            throw new EmptyList(ExceptionEnum.EMPTY_LIST);

        int customerSearchOption = customerEntrySearch();
        for (int i = 0; i < GlobalDataCustomer.customers.length; i++) {
            switch (customerSearchOption) {
                case 1:
                    String name = stringInput("Enter the name to search: ");
                    if (GlobalDataCustomer.customers[i].getName().equals(name)) {
                        System.out.println(GlobalDataCustomer.customers[i]);
                    } else {
                        throw new CustomerNotFound(ExceptionEnum.CUSTOMER_NOT_FOUND);
                    }
                    break;
                case 2:
                    String surname = stringInput("Enter the surname to search: ");
                    if (GlobalDataCustomer.customers[i].getSurname().equals(surname)){
                        System.out.println(GlobalDataCustomer.customers[i]);
                    } else {
                        throw new CustomerNotFound(ExceptionEnum.CUSTOMER_NOT_FOUND);
                    }
                    break;
                case 3:
                    String username = stringInput("Enter the username to search: ");
                    if (GlobalDataCustomer.customers[i].getUserName().equals(username)){
                        System.out.println(GlobalDataCustomer.customers[i]);
                    } else {
                        throw new CustomerNotFound(ExceptionEnum.CUSTOMER_NOT_FOUND);
                    }
                    break;
                default:
                    throw new InvalidOption(ExceptionEnum.INVALID_OPTION);
            }
        }
    }

    @Override
    public void showCustomer() {
        if (GlobalDataCustomer.customers == null || GlobalDataCustomer.customers.length == 0)
            throw new EmptyList(ExceptionEnum.EMPTY_LIST);

        System.out.println("----------------------Customer List----------------------");
        for (int i = 0; i < GlobalDataCustomer.customers.length; i++) {
            GlobalDataCustomer.customers[i].getInfo();
        }
        String customerName = customerEntryDetail();
        for (int i = 0; i < GlobalDataCustomer.customers.length; i++) {
            if (GlobalDataCustomer.customers[i].getName().equals(customerName)) {
                System.out.println(GlobalDataCustomer.customers[i].toString());
            } else {
                throw new CustomerNotFound(ExceptionEnum.CUSTOMER_NOT_FOUND);
            }
        }
    }
}
