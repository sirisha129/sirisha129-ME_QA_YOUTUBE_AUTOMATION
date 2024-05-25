package demo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataProvider {

    @DataProvider(name = "searchNames")
    public Object[][] getRowValues() throws IOException {
        
        String filePath = "/sirisha129-ME_QA_YOUTUBE_AUTOMATION/src/test/resources/YoutubeReadFile.xlsx"; // path to my Excel file
        String sheetName = "Sheet1"; // My excel sheet name
        return getDataFromExcel(filePath, sheetName);
    }

    public Object[][] getDataFromExcel(String filePath, String sheetName) throws IOException {
        List<String> searchTerms = new ArrayList<>();

        File fileName = new File(filePath);
        FileInputStream file = new FileInputStream(fileName);

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            XSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                StringBuilder rowValues = new StringBuilder();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowValues.append(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowValues.append(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            rowValues.append(cell.getBooleanCellValue());
                            break;
                        default:
                            break;
                    }
                }
                String searchTerm = rowValues.toString().trim();
                if (!searchTerm.isEmpty()) {
                    searchTerms.add(searchTerm);
                }
            }
        }

        workbook.close();
        file.close();

        Object[][] data = new Object[searchTerms.size()][1];
        for (int i = 0; i < searchTerms.size(); i++) {
            data[i][0] = searchTerms.get(i);
        }

        return data;
    }
}
