package pageobjectmodel;

import databaseuri.UserClient;
import informationuser.UserInformation;
import informationuser.UserCreator;

import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserRegistrationTest {
    private UserInformation userInformation;
    private UserRegistration registration;
    private UserLogin userLogin;
    private WebDriver driver;
    private UserClient userClient;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        registration = new UserRegistration(driver);
        userLogin = new UserLogin(driver);
        userClient = new UserClient();
    }
    @Test
    @Description("Успешная регистрация пользователя")
    public void checkingUserSuccessfulRegistration() {
        userInformation= UserCreator.getRandom();
        registration.userRegistration(userInformation);
        Assert.assertTrue(registration.checkSuccessfulRegistration());
    }
    @Test
    @Description("Регистрация пользователя с шестизначным паролем")
    public void checkingUserSuccessfulRegistrationWhitSixDigitPassword() {
        userInformation = UserCreator.getRandom(6);
        registration.userRegistration(userInformation);
        Assert.assertTrue(registration.checkSuccessfulRegistration());
    }
    @Test
    @Description("Регистрация пользователя с неверным паролем")
    public void checkingUserRegistrationWithWrongPassword() {
        userInformation = UserCreator.getRandom(5);
        registration.userRegistration(userInformation);
        Assert.assertTrue(registration.checkIncorrectPassword());
    }
    @Test
    @Description("Регистрация пользователя c авторизованными данными")
    public void checkingUserRegistrationWithAlreadyExistUserData() {
        userInformation = UserCreator.getRandom();
        registration.userRegistration(userInformation);
        registration.userRegistration(userInformation);
        Assert.assertTrue(registration.checkUserAlreadyExist());
    }
    @After
    public void cleanUp() {
        if (!(userLogin.loginSingInAccountButton(userInformation.getEmail(), userInformation.getPassword()) == null) &&
                (!(userLogin.getToken() == null))) {
            Assert.assertTrue(userClient.checkRemoveUser(userLogin.getToken()));
        }
        driver.quit();
    }
}