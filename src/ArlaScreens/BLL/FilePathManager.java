package ArlaScreens.BLL;

import ArlaScreens.BE.FilePath;
import ArlaScreens.BE.User;
import ArlaScreens.DAL.FilePathDBDAO;

public class FilePathManager {

    private FilePathDBDAO filePathDBDAO;

    public FilePathManager() {
        filePathDBDAO = new FilePathDBDAO();
    }

    public void addFilePath(FilePath filePath) {
        filePathDBDAO.addFilePath(filePath);
    }

    public String getWebSiteURL(User user) {
        return filePathDBDAO.getWebSiteURL(user);
    }

    public String getPDFPath(User user) {
        return filePathDBDAO.getPDFPath(user);
    }

    public String getCSVPath(User user) {
        return filePathDBDAO.getCSVPath(user);
    }

    public String getExcelPath(User user) {
        return filePathDBDAO.getExcelPath(user);
    }
}
