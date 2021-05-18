package ArlaScreens.BE;

public class ScreenView {

    private int screenViewID;
    private int userID;
    private boolean webSite;
    private boolean pdf;
    private boolean csv;
    private boolean excel;

    public ScreenView(int screenViewID, int userID, boolean webSite, boolean pdf, boolean csv, boolean excel) {
        this.screenViewID = screenViewID;
        this.userID = userID;
        this.webSite = webSite;
        this.pdf = pdf;
        this.csv = csv;
        this.excel = excel;
    }

    public int getScreenViewID() {
        return screenViewID;
    }

    public void setScreenViewID(int screenViewID) {
        this.screenViewID = screenViewID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isWebSite() {
        return webSite;
    }

    public void setWebSite(boolean webSite) {
        this.webSite = webSite;
    }

    public boolean isPdf() {
        return pdf;
    }

    public void setPdf(boolean pdf) {
        this.pdf = pdf;
    }

    public boolean isCsv() {
        return csv;
    }

    public void setCsv(boolean csv) {
        this.csv = csv;
    }

    public boolean isExcel() {
        return excel;
    }

    public void setExcel(boolean excel) {
        this.excel = excel;
    }
}
