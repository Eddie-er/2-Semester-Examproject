package ArlaScreens.BE;

public class FilePath {

    private int filePathID;
    private int userID;
    private String webSiteURL;
    private String pdfPath;
    private String csvPath;
    private String excelPath;

    public FilePath(int filePathID, int userID, String webSiteURL, String pdfPath, String csvPath, String excelPath) {
        this.filePathID = filePathID;
        this.userID = userID;
        this.webSiteURL = webSiteURL;
        this.pdfPath = pdfPath;
        this.csvPath = csvPath;
        this.excelPath = excelPath;
    }

    public int getFilePathID() {
        return filePathID;
    }

    public void setFilePathID(int filePathID) {
        this.filePathID = filePathID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getWebSiteURL() {
        return webSiteURL;
    }

    public void setWebSiteURL(String webSiteURL) {
        this.webSiteURL = webSiteURL;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getCsvPath() {
        return csvPath;
    }

    public void setCsvPath(String csvPath) {
        this.csvPath = csvPath;
    }

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }
}
