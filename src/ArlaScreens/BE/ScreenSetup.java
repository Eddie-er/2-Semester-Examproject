package ArlaScreens.BE;

public class ScreenSetup {

    private int screenSetupID;
    private int userID;
    private int rows;
    private int columns;

    public ScreenSetup(int screenSetupID, int userID, int rows, int columns) {
        this.screenSetupID = screenSetupID;
        this.userID = userID;
        this.rows = rows;
        this.columns = columns;
    }

    public int getScreenSetupID() {
        return screenSetupID;
    }

    public void setScreenSetupID(int screenSetupID) {
        this.screenSetupID = screenSetupID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
