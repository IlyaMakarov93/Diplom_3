package pageobjectmodel;

import databaseuri.UserClient;
import informationuser.UserInformation;
import informationuser.UserCreator;
import jdk.jfr.Description;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserLoginTest {
    protected static final String URL = "https://stellarburgers.nomoreparties.site/login";
    private final UserInformation userInformation = UserCreator.getRandom();
    private UserLogin userLogin;
    private WebDriver driver;
    private UserClient userClient;

    @Before
    public void setUp() {
        userClient = new UserClient();
        userClient.createUser(userInformation);
        driver = new ChromeDriver();
        userLogin = new UserLogin(driver);
    }
    @Test
    @Description("Авторизация после регистрации")
    public void checkOfAuthorizationAfterRegistration() {
        driver.get(URL);
        userLogin.userLogin(userInformation.getEmail(), userInformation.getPassword());
        Assert.assertTrue(userLogin.checkUserLogin());
    }
    @Test
    @Description("Авторизация через кнопку 'Войти в аккаунт'")
    public void checkOfAuthorizationThroughTheLoginButton() {
        userLogin.loginSingInAccountButton(userInformation.getEmail(), userInformation.getPassword());
        Assert.assertTrue(userLogin.checkUserLogin());
    }
    @Test
    @Description("Авторизация через кнопку 'Личный  кабинет'")
    public void checkOfAuthorizationThroughThePersonalAccountButton() {
        userLogin.loginPersonalAccountButton(userInformation.getEmail(), userInformation.getPassword());
        Assert.assertTrue(userLogin.checkUserLogin());
    }
    @Test
    @Description("Авторизация через кнопку в форме регистрации")
    public void checkOfAuthorizationThroughTheButtonInTheRegistrationForm() {
        userLogin.loginByRegistrationForm(userInformation.getEmail(), userInformation.getPassword());
        Assert.assertTrue(userLogin.checkUserLogin());
    }
    @Test
    @Description("Авторизация через кнопку в форме восстановления пароля")
    public void checkOfAuthorizationThroughTheButtonInThePwdRecoveryForm() {
        userLogin.loginByPasswordRecovery(userInformation.getEmail(), userInformation.getPassword());
        Assert.assertTrue(userLogin.checkUserLogin());
    }
    @After
    public void cleanUp() {
        Assert.assertTrue(userClient.checkRemoveUser(userLogin.getToken()));
        driver.quit();
    }
}