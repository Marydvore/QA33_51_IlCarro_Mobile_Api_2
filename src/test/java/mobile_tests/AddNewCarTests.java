package mobile_tests;

import config.AppiumConfig;
import dto.Car;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;
import utils.PropertiesReader;
import static utils.CarFactory.*;

public class AddNewCarTests extends AppiumConfig {

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
    public void addNewCarPositiveTest(){
        Car car = positiveCar();
        //car.setCarClass("");   negative
        new MyCarsScreen(driver).clickBtnAddNewCar();
        AddNewCarScreen addNewCarScreen = new AddNewCarScreen(driver);
        addNewCarScreen.typeAddCarForm(car);
        addNewCarScreen.clickBtnAddCar();
        Assert.assertTrue(new MyCarsScreen(driver).validateMessageSuccess("Car was added!"));
    }
}
