package ArlaScreens.BE;

public class LineChart {

    private int screenSetupID;
    private int row;
    private int column;
    private boolean isSelected;
    private String filePath;

    public LineChart(int screenSetupID, int row, int column, boolean isSelected, String filePath) {
        this.screenSetupID = screenSetupID;
        this.row = row;
        this.column = column;
        this.isSelected = isSelected;
        this.filePath = filePath;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
