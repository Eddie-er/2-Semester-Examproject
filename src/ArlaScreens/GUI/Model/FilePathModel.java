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

    public FilePath getFilePath(User user) {
        return filePathManager.getFilePath(user);
    }
}
