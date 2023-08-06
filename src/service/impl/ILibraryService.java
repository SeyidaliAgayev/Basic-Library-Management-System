package service.impl;

import enums.ExceptionEnum;
import enums.StatusEnum;
import exception.BookNotFound;
import exception.EmptyList;
import exception.InvalidOption;
import exception.WrongFormat;
import globalData.GlobalDataLibrary;
import model.Library;
import service.LibraryService;

import static helper.LibraryServiceHelper.fillLibrary;
import static util.InputUtil.*;
import static util.MenuUtil.*;


public class ILibraryService implements LibraryService {
    private static int countIndex = 0;

    @Override
    public void register() {
        int count = intInput("How many book will be registered: ");
        boolean isTrue = false;
        if (GlobalDataLibrary.libraries == null) {
            GlobalDataLibrary.libraries = new Library[count];
            for (int i = 0; i < GlobalDataLibrary.libraries.length; i++) {
                Library library = fillLibrary();
                if (library != null) {
                    GlobalDataLibrary.libraries[countIndex] = library;
                    countIndex++;
                    isTrue = true;
                }
            }
        } else {
            Library[] tempLibrary = GlobalDataLibrary.libraries;
            GlobalDataLibrary.libraries = new Library[GlobalDataLibrary.libraries.length + count];
            for (int i = tempLibrary.length; i < GlobalDataLibrary.libraries.length; i++) {
                if (i < tempLibrary.length) {
                    GlobalDataLibrary.libraries[i] = tempLibrary[i];
                } else {
                    Library library = fillLibrary();
                    if (library != null) {
                        GlobalDataLibrary.libraries[countIndex] = library;
                        isTrue = true;
                        countIndex++;
                    }
                }
            }

        }
        if (isTrue)
            System.out.println(StatusEnum.REGISTER_SUCCESSFULLY);
        byte nullCount = 0;
        for (int i = 0; i < GlobalDataLibrary.libraries.length; i++) {
            if (GlobalDataLibrary.libraries[i] == null) {
                nullCount++;
            }

        }

        Library[] tempLibrary = GlobalDataLibrary.libraries;
        GlobalDataLibrary.libraries = new Library[GlobalDataLibrary.libraries.length - nullCount];
        for (int i = 0; i < GlobalDataLibrary.libraries.length; i++) {
            GlobalDataLibrary.libraries[i] = tempLibrary[i];
        }

        if (GlobalDataLibrary.libraries.length == 0) {
            GlobalDataLibrary.libraries = null;
            countIndex = 0;
        }
        IBaseManagementService baseManagementService = new IBaseManagementService();
        baseManagementService.baseManagement();
    }

    @Override
    public void show() {
        if (GlobalDataLibrary.libraries == null || GlobalDataLibrary.libraries.length == 0)
            throw new EmptyList(ExceptionEnum.EMPTY_LIST);


        byte option = bookEntryShow();
        for (int i = 0; i < GlobalDataLibrary.libraries.length; i++) {
            switch (option) {
                case 1:
                    if (GlobalDataLibrary.libraries[i].getStockStatus() == 0) {
                        GlobalDataLibrary.libraries[i].getInfo();
                    }
                    break;
                case 2:
                    if (GlobalDataLibrary.libraries[i].getStockStatus() == 1) {
                        GlobalDataLibrary.libraries[i].getInfo();
                    }
                default:
                    throw new InvalidOption(ExceptionEnum.INVALID_OPTION);
            }
        }


        String bookName = bookEntryDetail();
        for (int i = 0; i < GlobalDataLibrary.libraries.length; i++) {
            if (GlobalDataLibrary.libraries[i].getName().equals(bookName)) {
                System.out.println(GlobalDataLibrary.libraries[i].toString());
            } else {
                throw new BookNotFound(ExceptionEnum.BOOK_NOT_FOUND);
            }
        }

        IBaseManagementService baseManagementService = new IBaseManagementService();
        baseManagementService.baseManagement();
    }

    @Override
    public void search() {
        if (GlobalDataLibrary.libraries == null || GlobalDataLibrary.libraries.length == 0)
            throw new EmptyList(ExceptionEnum.EMPTY_LIST);
        bookEntrySearch();
        int searchOption = intInput("Choose an option: ");
        boolean isTrue = false;

        for (int i = 0; i < GlobalDataLibrary.libraries.length; i++) {
            switch (searchOption) {
                case 1:
                    String keyName = stringInput("Enter the name: ");
                    if (GlobalDataLibrary.libraries[i].getName().equals(keyName)) {
                        System.out.println(GlobalDataLibrary.libraries[i]);
                    } else {
                        throw new BookNotFound(ExceptionEnum.BOOK_NOT_FOUND);
                    }
                    break;
                case 2:
                    String keyAuthor = stringInput("Enter the author: ");
                    if (GlobalDataLibrary.libraries[i].getAuthor().equals(keyAuthor)) {
                        System.out.println(GlobalDataLibrary.libraries[i]);
                    } else {
                        throw new BookNotFound(ExceptionEnum.BOOK_NOT_FOUND);
                    }
                    break;
                case 3:
                    String keyGenre = stringInput("Enter the genre: ");
                    if (GlobalDataLibrary.libraries[i].getGenre().equals(keyGenre)) {
                        System.out.println(GlobalDataLibrary.libraries[i]);
                    } else {
                        throw new BookNotFound(ExceptionEnum.BOOK_NOT_FOUND);
                    }
                    break;
                default:
                    throw new InvalidOption(ExceptionEnum.INVALID_OPTION);
            }
        }
        IBaseManagementService baseManagementService = new IBaseManagementService();
        baseManagementService.baseManagement();
    }

    @Override
    public boolean update() {
        if (GlobalDataLibrary.libraries == null || GlobalDataLibrary.libraries.length == 0)
            throw new EmptyList(ExceptionEnum.EMPTY_LIST);
        int id = intInput("Which book do you want to update: ");
        boolean isTrue = false;

        for (int i = 0; i < GlobalDataLibrary.libraries.length; i++) {
            if (GlobalDataLibrary.libraries[i].getId() == id) {
                String parameter = stringInput("Which parameter of book do you want to update: ");
                String[] parameterString = parameter.toLowerCase().split(",");
                for (String str : parameterString) {
                    switch (str) {
                        case "name":
                            GlobalDataLibrary.libraries[i].setName(stringInput("Enter the changed name:"));
                            isTrue = true;
                            break;
                        case "language":
                            GlobalDataLibrary.libraries[i].setLanguage(stringInput("Enter the changed language:"));
                            isTrue = true;
                            break;
                        case "count":
                            GlobalDataLibrary.libraries[i].setCount(intInput("Enter the changed count:"));
                            isTrue = true;
                            break;
                        case "price":
                            GlobalDataLibrary.libraries[i].setPrice(intInput("Enter the changed price:"));
                            isTrue = true;
                            break;
                        case "stockStatus":
                            GlobalDataLibrary.libraries[i].setStockStatus(byteInput("Enter the changed stock status:"));
                            isTrue = true;
                            break;
                        default:
                            throw new WrongFormat(ExceptionEnum.WRONG_FORMAT);
                    }
                }
                if (isTrue) {
                    System.out.println(StatusEnum.UPDATE_SUCCESSFULLY);
                }
            } else {
                throw new BookNotFound(ExceptionEnum.BOOK_NOT_FOUND);
            }

        }
        IBaseManagementService baseManagementService = new IBaseManagementService();
        baseManagementService.baseManagement();
        return true;
    }

    @Override
    public boolean delete() {
        if (GlobalDataLibrary.libraries == null || GlobalDataLibrary.libraries.length == 0)
            throw new EmptyList(ExceptionEnum.EMPTY_LIST);
        int id = intInput("Which book do you want to delete: ");
        GlobalDataLibrary.libraries = new Library[GlobalDataLibrary.libraries.length - 1];
        boolean isTrue = false;
        for (int i = 0; i < GlobalDataLibrary.libraries.length; i++) {
            if (GlobalDataLibrary.libraries[i].getId() == id) {
                System.out.println(StatusEnum.DELETE_SUCCESSFULLY);
                countIndex--;
                isTrue = true;
                Library[] tempLibrary = GlobalDataLibrary.libraries;
                GlobalDataLibrary.libraries = new Library[GlobalDataLibrary.libraries.length - 1];
                for (int j = 0; j < GlobalDataLibrary.libraries.length; j++) {
                    if (j < i) {
                        GlobalDataLibrary.libraries[j] = tempLibrary[j];
                    } else {
                        GlobalDataLibrary.libraries[j] = tempLibrary[j + 1];
                    }
                }
            }

        }
        if (GlobalDataLibrary.libraries.length == 0) {
            GlobalDataLibrary.libraries = null;
            countIndex = 0;
        }
//        if (isTrue) {
//            System.out.println(StatusEnum.DELETE_SUCCESSFULLY.name());
//        }
        IBaseManagementService baseManagementService = new IBaseManagementService();
        baseManagementService.baseManagement();
        return false;
    }

    @Override
    public void carryToStock() {
        for (int i = 0; i < GlobalDataLibrary.libraries.length; i++) {
            if (GlobalDataLibrary.libraries[i].getStockStatus() == 1) {
                GlobalDataLibrary.libraries[i].setStockStatus((byte) 0);
            } else if (GlobalDataLibrary.libraries[i].getStockStatus() == 0) {
                GlobalDataLibrary.libraries[i].setStockStatus((byte) 1);
            } else {
                throw new WrongFormat(ExceptionEnum.WRONG_FORMAT);
            }
            System.out.println(StatusEnum.CARRIED_SUCCESSFULLY);
        }
        IBaseManagementService baseManagementService = new IBaseManagementService();
        baseManagementService.baseManagement();
    }
}
