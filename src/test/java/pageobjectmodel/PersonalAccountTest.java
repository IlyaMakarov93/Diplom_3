package pageobjectmodel;

import databaseuri.UserClient;
import informationuser.UserInformation;
import informationuser.UserCreator;
import jdk.jfr.Description;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PersonalAccountTest {
    private static UserLogin userLogin;
    private static final UserInformation userInformation = UserCreator.getRandom();
    private static WebDriver driver;
    private static UserClient userClient;

    @Before
    public void setUp() {
        userClient = new UserClient();
        userClient.createUser(userInformation);
        driver = new ChromeDriver();
        userLogin = new UserLogin(driver);
        userLogin.loginSingInAccountButton(userInformation.getEmail(), userInformation.getPassword());
        userLogin.clickOnPersonalAccountButton();
    }

    @Test
    @Description("Переход в личный кабинет")
    public void checkLogin() {
        Assert.assertTrue(userLogin.checkSuccessfulProfile());
    }

    @Test
    @Description("Выход из личного кабинета")
    public void checkLogout() {
        userLogin.logoutOfPersonalAccount();
        Assert.assertTrue(userLogin.checkLogout());
        userLogin.userLogin(userInformation.getEmail(), userInformation.getPassword());
        Assert.assertTrue(userLogin.checkUserLogin());//для получения токена
    }

    @Test
    @Description("Переход в конструктор из личного кабинета")
    public void checkTrekToConstructor() {
        userLogin.clickOnConstructorButton();
        Assert.assertTrue(userLogin.checkUserLogin());
    }

    @Test
    @Description("Переход на главную страницу через логотип из личного кабинета")
    public void checkTrekToLogo() {
        userLogin.clickOnLogoButton();
        Assert.assertTrue(userLogin.checkUserLogin());
    }

    @After
    public void cleanUp() {
        Assert.assertTrue(userClient.checkRemoveUser(userLogin.getToken()));
        driver.quit();
    }
}