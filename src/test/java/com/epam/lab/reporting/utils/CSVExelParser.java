package com.epam.lab.reporting.utils;

import com.epam.lab.reporting.models.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CSVExelParser {
    public static List<User> readUsersFromCSV(String file) {
        List<User> users = new ArrayList<User>();
        try {
            CSVParser records = new CSVParser(new FileReader(file), CSVFormat.DEFAULT.withHeader());
            for (CSVRecord record : records) {
                users.add(new User(record.get("EMAIL"), record.get("PASSWORD")));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<User> readMessagesFromXLSXFile(String file) throws IOException {
        List<User> users = new ArrayList<>();

        InputStream ExcelFileToRead = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        Iterator rows = sheet.rowIterator();
        int counter = 0;
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();

            User user = new User();
            while (cells.hasNext()) {
                cell = (XSSFCell) cells.next();
                if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    if (counter == 0) {
                        user.setLogin(cell.getStringCellValue());
                    } else if (counter == 1) {
                        user.setPassword(cell.getStringCellValue());
                    }
                    ++counter;
                }
            }
            counter = 0;
            users.add(user);
        }
        // deletes last value wich is null
        return users.stream().filter(message -> !(message.getLogin() == null)).collect(Collectors.toList());
    }
}