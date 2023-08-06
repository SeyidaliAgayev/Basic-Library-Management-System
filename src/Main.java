import service.BaseManagementService;
import service.LibraryManagementService;
import service.impl.IBaseManagementService;
import service.impl.ILibraryManagementService;

public class Main {
    public static void main(String[] args) {
        BaseManagementService baseManagementService = new IBaseManagementService();
        baseManagementService.baseManagement();


    }
}