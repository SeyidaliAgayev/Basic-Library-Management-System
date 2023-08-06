package helper;


import enums.ExceptionEnum;
import exception.BookNotFound;
import globalData.GlobalDataLibrary;
import model.Library;

import static util.InputUtil.*;

public class LibraryServiceHelper {
    static int bookCount = 0;
    private static int id = 0;

    public static Library fillLibrary() {
        try {


            String name = stringInput("Enter the book name: ");
            String author = stringInput("Enter the author name: ");
            String genre = stringInput("Enter the genre: ");
            int pageCount = intInput("Enter the page count: ");
            String language = stringInput("Enter the language: ");
            int price = intInput("Enter the price: ");
            int count = intInput("Enter the count of book: ");
            byte stockStatus = byteInput("Enter stock status(if book is in stock, status will be 1 and vice versa): ");
            byte rentStatus = byteInput("Enter rent status(if it is rent, status will be 1 and vice versa): ");
            if (stockStatus != 0 && stockStatus != 1) {
                stockStatus = 0;
            }

            return new Library(++id, name, author, genre, pageCount, language, price, count, stockStatus,rentStatus);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }



    public static void registerHelper() {
        Library library = fillLibrary();
        if (library != null) {
            GlobalDataLibrary.libraries[bookCount] = library;
            bookCount++;
        }
    }

    public static Library findById(int id) {
        Library library = new Library();
        if (GlobalDataLibrary.libraries == null) {
            throw new BookNotFound(ExceptionEnum.BOOK_NOT_FOUND);
        } else {
            for (int i = 0; i < GlobalDataLibrary.libraries.length; i++) {
                if (GlobalDataLibrary.libraries[i].getId() == id) {
                    library = GlobalDataLibrary.libraries[i];
                    break;
                }
            }
        }
        return library;
    }
}
