package mobile_tests;

import config.AppiumConfig;
import dto.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.MyCarsScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import utils.PropertiesReader;

public class DeleteCarTests extends AppiumConfig {
    SearchScreen searchScreen;
    @BeforeMethod
    public void login(){
        new SplashScreen(driver).openSearchScreen(7);
        searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnLogin();
        User user = User.builder()
                .username(PropertiesReader.gerProperty("mobile.properties", "email"))
                .password(PropertiesReader.gerProperty("mobile.properties", "password"))
                .build();
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
    }

    @Test
    public void deleteCarPositiveTest(){
        MyCarsScreen myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.deleteCar();

    }
}
