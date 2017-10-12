package com.epam.lab.reporting.utils;

import com.epam.lab.reporting.models.User;
import org.testng.annotations.DataProvider;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class UserDataProvider {
    @DataProvider(name = "csvdata", parallel = false)
    public static Object[][] getDataFromCSV() throws IOException, SAXException, ParserConfigurationException {
        List<User> users = CSVExelParser.readUsersFromCSV("data.csv");
        Object dataForTest[][] = new Object[users.size()][users.size()];
        for (int i = 0; i < users.size(); i++) {
            dataForTest[i][0] = users.get(i).getLogin();
            dataForTest[i][1] = users.get(i).getPassword();
        }

        return dataForTest;
    }

    @DataProvider(name = "exceldata", parallel = false)
    public static Object[][] getDataFromXLSX() throws IOException, SAXException, ParserConfigurationException {
        List<User> users = CSVExelParser.readMessagesFromXLSXFile("gmail.xlsx");
        Object dataForTest[][] = new Object[users.size()][users.size()];
        for (int i = 0; i < users.size(); i++) {
            dataForTest[i][0] = users.get(i).getLogin();
            dataForTest[i][1] = users.get(i).getPassword();
        }

        return dataForTest;
    }
}
