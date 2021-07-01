package com.gaurav.CommonUtils;

import com.gaurav.Base.TestBase;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestUtils extends TestBase {

    public static final String TEST_DATA_PATH = "/Users/Gaurav/Documents/GitHub/SeleniumProject/src/main/resources/testDataSheet.xlsx";
    public static FileInputStream file;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static XSSFCell cell;
    public static XSSFRow row;
    public static LocalDateTime now = LocalDateTime.now();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Fetches tomorrow's data and formats it
     *
     * @return Formatted tomorrows date
     */
    public static String getTomorrowDate() {
        return now.plusDays(2).format(formatter);
    }

    /**
     * Fetches the date after x days and formats it
     *
     * @param days Round trip date as input
     * @return Formatted date
     */
    public static String getDateAfterXDays(int days) {
        LocalDateTime date = now.plusDays(days);
        return date.format(formatter);
    }

    /**
     * This method takes the screenshot of the webpage
     *
     * @param filename Name of the file
     */
    public static void takeScreenShot(String filename) {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filePath = "./snaps" + File.separator + filename + ".png";
        try {
            FileUtils.copyFile(source, new File(filePath));
        } catch (IOException e) {
            System.out.println("Error while saving screenshot: " + e.getMessage());
        }
    }

    /**
     * Reads and fetches the data from excel
     *
     * @param sheetName Name of the sheet that has test data
     * @return Multidimensional object array of test data
     */
    public static Object[][] getTestDataFromExcel(String sheetName) {
        try {
            file = new FileInputStream(TEST_DATA_PATH);
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                cell = row.getCell(j);
                CellType type = cell.getCellType();
                if (type == CellType.STRING) {
                    data[i][j] = cell.getStringCellValue();
                } else if (type == CellType.NUMERIC) {
                    data[i][j] = cell.getNumericCellValue();
                }
            }
        }
        return data;
    }
}
