package helper;

import enums.ExceptionEnum;
import enums.StatusEnum;
import exception.BookNotFound;
import globalData.GlobalDataCustomer;
import globalData.GlobalDataLibrary;
import model.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static util.InputUtil.*;

public class CustomerServiceHelper {
    static int customerCount = 0;
    private static int customerId = 0;
    public static Customer fillCustomer() {
        try {
            String name = stringInput("Enter the customer name: ");
            String surname = stringInput("Enter the surname: ");
            String userName = stringInput("Enter the username: ");
            String password = stringInput("Enter the password: ");
            LocalDate birthday = birthdayHelperService();
            LocalDateTime registerDate = nowDate();
            LocalDateTime updateDate = null;
            return new Customer(++customerId,name,surname,userName,password,birthday,registerDate,updateDate);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
    public static void signUpHelper() {
        Customer customer = fillCustomer();
        if (customer != null) {
            GlobalDataCustomer.customers[customerCount] = customer;
            customerCount++;
        }
    }


    public static LocalDate birthdayHelperService() {
        try {
            String str1 = stringInput("Enter the Birth Date(day-month-years): ");
            String[] str2 = str1.split("-");
            int day = Integer.parseInt(str2[0]);
            int month = Integer.parseInt(str2[1]);
            int years = Integer.parseInt(str2[2]);
            return LocalDate.of(day, month, years);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }
    public static LocalDateTime nowDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.withNano(0);
    }
    public static void bookRentHelper(int count) {
        String bookName = stringInput("Which book do you want to rent: ");
        boolean isTrue = false;
        for (int i = 0; i < GlobalDataLibrary.libraries.length; i++) {
            if (GlobalDataLibrary.libraries[i].getName().equals(bookName) && GlobalDataLibrary.libraries[i].getCount() != 0
                    && GlobalDataLibrary.libraries[i].getStockStatus() == 1) {
                GlobalDataLibrary.libraries[i].setCount(GlobalDataLibrary.libraries[i].getCount() - 1);
                GlobalDataCustomer.customers[count].setBookId((int)GlobalDataLibrary.libraries[i].getId());
                isTrue = true;
                System.out.println(StatusEnum.BOOK_RENTED_SUCCESSFULLY);
            }
        }
        if (!isTrue){
            throw new BookNotFound(ExceptionEnum.BOOK_NOT_FOUND);
        }
    }
}
