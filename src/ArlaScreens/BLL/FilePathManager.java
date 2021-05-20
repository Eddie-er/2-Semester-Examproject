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

    public boolean checkIfFilePathExist(User user) {
        return filePathDBDAO.checkIfFilePathExist(user);
    }

    public void editFilePath(FilePath filePath) {
        filePathDBDAO.editFilePath(filePath);
    }

    public FilePath getFilePath(User user) {
        return filePathDBDAO.getFilePath(user);
    }
}
