package mobile_tests;

import config.AppiumConfig;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import utils.PropertiesReader;

public class LoginTests extends AppiumConfig {

    LoginScreen loginScreen;
    SearchScreen searchScreen;

    @BeforeMethod
    public void openLoginForm(){
        new SplashScreen(driver).openSearchScreen(7);
        searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnLogin();
    }

    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .username(PropertiesReader.gerProperty("mobile.properties", "email"))
                .password(PropertiesReader.gerProperty("mobile.properties", "password"))
                .build();
        loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
        Assert.assertTrue(new SearchScreen(driver).validatePopUpMessageLoginSuccess("Login success!"));
    }

    @Test
    public void loginNegativeEmptyFiledEmailTest(){
        User user = User.builder()
                .username("")
                .password(PropertiesReader.gerProperty("mobile.properties", "password"))
                .build();
        loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("All fields must be filled and agree terms"));
    }
}
