package ArlaScreens.BLL.Utils;

import ArlaScreens.BE.Excel;
import ArlaScreens.BE.ScreenSetup;
import ArlaScreens.BLL.ScreenSetupManager;
import ArlaScreens.GUI.Model.LoginModel;
import com.gembox.spreadsheet.*;

import java.io.File;
import java.io.IOException;

public class ExcelReader {

    private LoginModel loginModel;
    private ScreenSetupManager screenSetupManager;

    public ExcelReader() {
        loginModel = LoginModel.getInstance();
        screenSetupManager = new ScreenSetupManager();
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }

    /**
     * Loads an excel file and creates the rows and columns used in the table
     * Link: https://www.gemboxsoftware.com/spreadsheet-java/examples/javafx-import-export-excel-tableview/5301
     * @return
     * @throws IOException
     */
    public String[][] loadExcel() throws IOException {

        ScreenSetup screenSetup = screenSetupManager.getScreenSetup(loginModel.getLoggedInUser());
        Excel excel = screenSetupManager.getExcel(screenSetup);
        String path = excel.getFilePath();

        //"Data/Excel/MOCK_DATA.xlsx"
        File file = new File(path);

        ExcelFile workbook = ExcelFile.load(file.getAbsolutePath());
        ExcelWorksheet worksheet = workbook.getWorksheet(0);
        String[][] sourceData = new String[100][26];
        for (int row = 0; row < sourceData.length; row++) {
            for (int column = 0; column < sourceData[row].length; column++) {
                ExcelCell cell = worksheet.getCell(row, column);
                if (cell.getValueType() != CellValueType.NULL) {
                    sourceData[row][column] = cell.getValue().toString();
                }
            }
        }
        return sourceData;
    }
}
