package ArlaScreens.BE;

public class ScreenView {

    private int screenViewID;
    private int screenSetupID;
    private boolean barChart;
    private boolean pieChart;
    private boolean webSite;
    private boolean pdf;
    private boolean csv;
    private boolean excel;

    public ScreenView(int screenViewID, int screenSetupID, boolean barChart, boolean pieChart, boolean webSite, boolean pdf, boolean csv, boolean excel) {
        this.screenViewID = screenViewID;
        this.screenSetupID = screenSetupID;
        this.barChart = barChart;
        this.pieChart = pieChart;
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

    public int getScreenSetupID() {
        return screenSetupID;
    }

    public void setScreenSetupID(int screenSetupID) {
        this.screenSetupID = screenSetupID;
    }

    public boolean isBarChart() {
        return barChart;
    }

    public void setBarChart(boolean barChart) {
        this.barChart = barChart;
    }

    public boolean isPieChart() {
        return pieChart;
    }

    public void setPieChart(boolean pieChart) {
        this.pieChart = pieChart;
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
