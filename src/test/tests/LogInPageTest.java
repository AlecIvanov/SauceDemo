package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LogInPage;

public class LogInPageTest extends BaseTest {
    LogInPage logInPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        super.setUp();
        logInPage = new LogInPage(getDriver());
    }

    @Test
    public void verifyTitle(){
        logInPage.sleep(3000);
        Assert.assertEquals(getDriver().getTitle(), "Swag Labs");


    }
    @Test
    public void veryfyLogInStandardUser(){
        logInPage.logIn("standard");
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.sausedemo.com/inventory.html");
    }
    @Test
    public void verifyLockedUser(){
        logInPage.logIn("locked");
        Assert.assertEquals(logInPage.errorMsg.getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

}