package ArlaScreens.GUI.Model;

import ArlaScreens.BE.FilePath;
import ArlaScreens.BE.User;
import ArlaScreens.BLL.FilePathManager;

public class FilePathModel {

    private FilePathManager filePathManager;

    public FilePathModel() {
        filePathManager = new FilePathManager();
    }

    public void addFilePath(FilePath filePath) {
        filePathManager.addFilePath(filePath);
    }

    public boolean checkIfFilePathExist(User user) {
        return filePathManager.checkIfFilePathExist(user);
    }

    public void editFilePath(FilePath filePath) {
        filePathManager.editFilePath(filePath);
    }

    public FilePath getFilePath(User user) {
        return filePathManager.getFilePath(user);
    }
}
