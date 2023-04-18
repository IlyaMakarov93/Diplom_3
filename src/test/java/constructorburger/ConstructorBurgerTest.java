package constructorburger;

import jdk.jfr.Description;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConstructorBurgerTest {
    private static final String URL = "https://stellarburgers.nomoreparties.site/";
    private static ConstructorBurger constructorBurger;
    private static WebDriver driver;
    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        constructorBurger = new ConstructorBurger(driver);
        driver.get(URL);
    }
    @Test
    @Description("Переход в раздел 'Булки'")
    public void checkTrekOnBuns() {
        Assert.assertTrue(constructorBurger.checkTextBuns());
        constructorBurger.clickSectionFillings();
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
        driver.quit();
    }
}