package com.epam.lab.reporting;

import com.epam.lab.reporting.utils.TestNGListener;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestNGListener.class})
public class GmailFailedLoginTest {
    private static final Logger LOGGER = Logger.getLogger(GmailLoginTest.class);
    GmailEmailHandler gmailEmailHandler;
    @Test
    public void testGmailLoginWithCSVData() {
        gmailEmailHandler.goToGmailLoginPage();
        gmailEmailHandler.enterEmail("failedlogin@gmail.com");
        gmailEmailHandler.enterPassword("test");

        if(gmailEmailHandler.checkSuccessLogin("failedlogin@gmail.com")){
            LOGGER.info("Test passed");
        }
        else {
            LOGGER.error("Test failed");
        }

        gmailEmailHandler.quit();
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