package service.impl;

import enums.ExceptionEnum;
import exception.*;
import service.BaseManagementService;
import service.CustomerManagementService;
import service.CustomerService;

import static util.MenuUtil.*;

public class ICustomerManagementService implements CustomerManagementService {
    @Override
    public void customerManagement() {
            try {
                int customerManagementOption = customerEntryMenu();
                CustomerService customerService = new ICustomerService();
                switch (customerManagementOption) {
                    case 0:
                        System.exit(-1);
                        break;
                    case 1:
                        customerService.signUp();
                        break;
                    case 2:
                        customerService.logIn();
                        break;
                    case 3:
                        customerService.updateCustomer();
                        break;
                    case 4:
                        customerService.deleteCustomer();
                        break;
                    case 5:
                        customerService.searchCustomer();
                        break;
                    case 6:
                        customerService.showCustomer();
                        break;
                    default:
                        throw new InvalidOption(ExceptionEnum.INVALID_OPTION);
                }
            } catch (BookNotFound | CustomerNotFound | InvalidOption | WrongFormat | EmptyList exception) {
                System.out.println(exception.getMessage());
            } catch (RuntimeException exception) {
                System.out.println(exception.getMessage());
            }
    }
}
