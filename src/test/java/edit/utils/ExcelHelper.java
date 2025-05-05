package edit.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelHelper {
    public static Object[][] readExcel(String filePath, String sheetName) throws IOException {
        try(
                FileInputStream file = new FileInputStream(new File(filePath));
                XSSFWorkbook book = new XSSFWorkbook(file)) {

            XSSFSheet sheet = book.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("La hoja " + sheetName + " no existe en el archivo de Excel");
            }

            int rows = sheet.getPhysicalNumberOfRows();
            if (rows <= 1) {
                return new Object[0][0];
            }

            XSSFRow headerRow = sheet.getRow(0);
            int columns = headerRow.getLastCellNum();

            Object[][] cellValues = new Object[rows - 1][columns];
            DataFormatter dataFormatter = new DataFormatter();

            // Procesamos cada fila de datos (saltando la fila de encabezados)
            for (int i = 1; i < rows; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                for (int j = 0; j < columns; j++) {
                    XSSFCell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cellValues[i - 1][j] = (cell == null) ? "" : dataFormatter.formatCellValue(cell);
                }
            }
            return cellValues;
        }
    }
}
