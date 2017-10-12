package com.epam.lab.reporting;

import com.epam.lab.reporting.utils.TestNGListener;
import com.epam.lab.reporting.utils.UserDataProvider;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

@Listeners({TestNGListener.class})
public class GmailLoginTest {
    private static final Logger LOGGER = Logger.getLogger(GmailLoginTest.class);
    GmailEmailHandler gmailEmailHandler;

    @Test(dataProvider = "csvdata", dataProviderClass = UserDataProvider.class, alwaysRun = true)
    public void testGmailLoginWithCSVData(String login, String password) {
        gmailEmailHandler.goToGmailLoginPage();
        gmailEmailHandler.enterEmail(login);
        gmailEmailHandler.enterPassword(password);

        if(gmailEmailHandler.checkSuccessLogin(login)){
            LOGGER.info("Test passed");
        }
        else {
            LOGGER.error("Test failed");
        }

        gmailEmailHandler.quit();
    }

    @Test(dataProvider = "exceldata", dataProviderClass = UserDataProvider.class, alwaysRun = true)
    public void testGmailLoginWithExcelData(String login, String password) {
        gmailEmailHandler.goToGmailLoginPage();
        gmailEmailHandler.enterEmail(login);
        gmailEmailHandler.enterPassword(password);

        if(gmailEmailHandler.checkSuccessLogin(login)){
            LOGGER.info("Test passed");
        }
        else {
            LOGGER.error("Test failed");
        }
    }

    @BeforeMethod
    public void setup() {
        gmailEmailHandler = new GmailEmailHandler();
    }

    @AfterMethod
    public void tearDown() {
        gmailEmailHandler.quit();
    }
}
