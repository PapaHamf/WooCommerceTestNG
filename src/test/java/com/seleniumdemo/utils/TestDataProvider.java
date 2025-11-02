package com.seleniumdemo.utils;

import com.github.javafaker.Faker;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class TestDataProvider {

    private Faker faker;
    private Random random;

    public TestDataProvider() {
        faker = new Faker();
        random = new Random();
    }

    public static Object[][] readExcel(String fileName) throws IOException {
        File file = new File("src/test/resources/" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = null;
        String fileExt = fileName.substring(fileName.indexOf("."));
        if (fileExt.equals(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if ( fileExt.equals(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheetAt(0);
        int rowNumber = sheet.getLastRowNum();
        // fetching the column number
        int columnNumber = sheet.getRow(0).getLastCellNum();
        // array used to store the data
        Object[][] data = new Object[rowNumber][columnNumber];
        for (int i = 1; i <= rowNumber; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < columnNumber; j++) {
                data[i-1][j] = row.getCell(j).getStringCellValue();
            }
        }
        return data;
    }

    public static List<String[]> readCSV(String fileName) throws CsvException, IOException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(fileName)).build();
        List<String[]> myEntries = reader.readAll();
        return myEntries;
    }

    public String generatePasswordShorterThan12Chars(){
        String password = (faker.letterify("??????", false))
                + (faker.letterify("??", true))
                + (faker.numerify("##"))
                + generateRandomSpecialChar();
        return password;
    }

    public String generatePasswordWithoutUpperCase(){
        String password = (faker.letterify("?????????", false))
                + (faker.numerify("##"))
                + generateRandomSpecialChar();
        return password;
    }

    public String generatePasswordWithoutNumber(){
        String password = (faker.letterify("?????????", false))
                + (faker.letterify("??", true))
                + generateRandomSpecialChar();
        return password;
    }

    public String generatePasswordWithoutSpecialSymbol() {
        String password = (faker.letterify("?????????", false))
                + (faker.letterify("??", true))
                + (faker.numerify("##"));
        return password;
    }

    public String generateCorrectPassword() {
        String password = (faker.letterify("?????????", false))
                + (faker.letterify("??", true))
                + (faker.numerify("##"))
                + generateRandomSpecialChar();
        return password;
    }

    private Character generateRandomSpecialChar() {
        String chars = "!@#$%^&*()-_=+[]{};:,.<>?";
        int index = random.nextInt(chars.length()-1);
        return chars.charAt(index);
    }
}