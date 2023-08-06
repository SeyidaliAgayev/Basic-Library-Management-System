package util;
import static util.InputUtil.*;
public class MenuUtil {
    public static byte firstEntryMenu() {
        System.out.println("""
                           [1].Library
                           [2].Customer
                           """
        );
        return byteInput("Choose an option: ");
    }
    public static byte bookEntryMenu() {
        System.out.println("""
                        ----------------Library Management System----------------
                        [0].Finish Program!
                        [1].Register Book
                        [2].Show Book
                        [3].Update Book
                        [4].Delete Book
                        [5].Search Book
                        [6].Carry to Stock
                        --------------------------------------------------------
                        """
        );
        return byteInput("Choose an option: ");
    }

    public static byte customerEntryMenu() {
        System.out.println("""
                           [0].Finish Program!
                           [1].Sign Up
                           [2].Log in
                           [3].Update Customer information
                           [4].Delete Customer
                           [5].Search Customer
                           [6].Show Customers
                           """
        );
        return byteInput("Choose an option: ");
    }

    public static byte bookEntryShow() {
        System.out.println(
                """
                ------------------
                [1].Storage
                [2].Stocks   
                ------------------
                """
        );
        return byteInput("Choose an option: ");
    }

    public static String bookEntryDetail() {
        System.out.println("Detailed information of book! ");
        return stringInput("Enter the book name: ");
    }
    public static String customerEntryDetail() {
        System.out.println("Detailed information of customer! ");
        return stringInput("Enter the customer name: ");
    }

    public static byte bookEntrySearch() {
        System.out.println(
                 """
                 [1].Name
                 [2].Author
                 [3].Genre
                 """);
        return byteInput("Choose an option to search: ");
    }
    public static byte customerEntrySearch() {
        System.out.println(
                """
                [1].Name
                [2].Surname
                [3].Username
                """);
        return byteInput("Choose an option to search: ");
    }
}
