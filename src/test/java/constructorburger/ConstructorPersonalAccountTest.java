package constructorburger;

import databaseuri.UserClient;
import informationuser.UserInformation;
import informationuser.UserCreator;
import jdk.jfr.Description;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjectmodel.UserLogin;

public class ConstructorPersonalAccountTest {
    private static final UserInformation userInformation = UserCreator.getRandom();
    private static UserLogin userLogin;
    private static WebDriver driver;
    private static ConstructorBurger constructorBurger;
    private static UserClient userClient;

    @BeforeClass
    public static void setUp() {
        userClient = new UserClient();
        userClient.createUser(userInformation);
        driver = new ChromeDriver();
        userLogin = new UserLogin(driver);
        userLogin.loginSingInAccountButton(userInformation.getEmail(), userInformation.getPassword());
        constructorBurger = new ConstructorBurger(driver);
    }
    @Test
    @Description("Переход в раздел 'Булки'")
    public void checkTextOnBuns() {
        Assert.assertTrue(constructorBurger.checkTextBuns());
        constructorBurger.checkTextFillings();
        constructorBurger.clickSectionBuns();
        Assert.assertTrue(constructorBurger.checkTextBuns());
    }
    @Test
    @Description("Переход в раздел 'Соусы'")
    public void checkTrekSauces() {
        constructorBurger.clickSectionSauces();
        Assert.assertTrue(constructorBurger.checkTextSauces());
    }
    @Test
    @Description("Переход в раздел 'Начинки'")
    public void checkTrekFillings() {
        constructorBurger.clickSectionFillings();
        Assert.assertTrue(constructorBurger.checkTextFillings());
    }
    @AfterClass
    public static void cleanUp() {
        userClient.checkRemoveUser(userLogin.getToken());
        driver.quit();
    }
}
