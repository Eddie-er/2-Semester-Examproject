package ArlaScreens.BLL.Utils;

import com.gembox.spreadsheet.*;

import java.io.File;
import java.io.IOException;

public class ExcelReader {

    public ExcelReader() {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }

    public String[][] loadExcel() throws IOException {
        File file = new File("Data/Excel/MOCK_DATA.xlsx");

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
