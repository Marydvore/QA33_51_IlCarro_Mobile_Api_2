package mobile_tests;

import config.AppiumConfig;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import utils.PropertiesReader;

public class LoginTests extends AppiumConfig {
    @BeforeMethod
    public void openLoginForm(){
        new SplashScreen(driver).openSearchScreen(7);
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnLogin();
    }

    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .username(PropertiesReader.gerProperty("mobile.properties", "email"))
                .password(PropertiesReader.gerProperty("mobile.properties", "password"))
                .build();
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
        Assert.assertTrue(new SearchScreen(driver).validatePopUpMessageLoginSuccess("Login success!"));
    }
}
