package service.impl;

import enums.ExceptionEnum;
import exception.BookNotFound;
import exception.EmptyList;
import exception.InvalidOption;
import exception.WrongFormat;
import service.LibraryManagementService;
import service.LibraryService;

import static util.MenuUtil.*;

public class ILibraryManagementService implements LibraryManagementService {
    @Override
    public void libraryManagement() {
            try {
                int managementOption = bookEntryMenu();
                LibraryService libraryService = new ILibraryService();
                switch (managementOption) {
                    case 0:
                        System.exit(-1);
                        break;
                    case 1:
                        libraryService.register();
                        break;
                    case 2:
                        libraryService.show();
                        break;
                    case 3:
                        libraryService.update();
                        break;
                    case 4:
                        libraryService.delete();
                        break;
                    case 5:
                        libraryService.search();
                        break;
                    case 6:
                        libraryService.carryToStock();
                        break;
                    default:
                        throw new InvalidOption(ExceptionEnum.INVALID_OPTION);
                }
            } catch (BookNotFound | EmptyList | InvalidOption | WrongFormat exception ) {
                System.out.println(exception.getMessage());
            }catch (RuntimeException exception){
                System.out.println(exception.getMessage());
            }
    }
}
