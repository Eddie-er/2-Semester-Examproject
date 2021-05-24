package ArlaScreens.BE;

public class WebSite {

    private int screenSetupID;
    private int row;
    private int column;
    private boolean isSelected;
    private String url;

    public WebSite(int screenSetupID, int row, int column, boolean isSelected, String url) {
        this.screenSetupID = screenSetupID;
        this.row = row;
        this.column = column;
        this.isSelected = isSelected;
        this.url = url;
    }

    public int getScreenSetupID() {
        return screenSetupID;
    }

    public void setScreenSetupID(int screenSetupID) {
        this.screenSetupID = screenSetupID;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
