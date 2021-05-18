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

    public String getWebSiteURL(User user) {
        return filePathManager.getWebSiteURL(user);
    }

    public String getPDFPath(User user) {
        return filePathManager.getPDFPath(user);
    }

    public String getCSVPath(User user) {
        return filePathManager.getCSVPath(user);
    }

    public String getExcelPath(User user) {
        return filePathManager.getExcelPath(user);
    }
}
