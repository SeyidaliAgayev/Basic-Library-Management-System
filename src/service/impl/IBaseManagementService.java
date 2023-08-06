package service.impl;

import enums.ExceptionEnum;
import exception.*;
import service.BaseManagementService;
import service.CustomerService;
import service.LibraryService;

import static util.MenuUtil.*;

public class IBaseManagementService implements BaseManagementService {
    @Override
    public void baseManagement() {
        while (true) {
            try {
                int baseManagementOption = firstEntryMenu();
                ILibraryManagementService libraryService = new ILibraryManagementService();
                ICustomerManagementService customerService = new ICustomerManagementService();
                switch (baseManagementOption) {
                    case 1:
                        libraryService.libraryManagement();
                        break;
                    case 2:
                        customerService.customerManagement();
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
}
