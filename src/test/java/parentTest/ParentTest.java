package parentTest;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.LoginPage;
import pages.MainPage;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class ParentTest {


    public WebDriver webDriver;
    String browser = System.getProperty("browser");                       //імя браузера, якого потрібно запустити
    Logger log;
    protected MainPage mainPage;
    protected LoginPage loginPage;


    @Before
    public void setUp() {

        log = Logger.getLogger(getClass());
        setBrowser();                                                       // буде відкривати вказаний браузер             //
        mainPage = new MainPage(webDriver);                                 // ініціалізуємо клас MainPage + Дали браузер
        loginPage = new LoginPage(webDriver);                               // ініціалізуємо клас MainPage + Дали браузер


        webDriver.manage().window().maximize();                              // відкрили вікно браузера.розгорнули
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  // чекай поки відкриється браузер (макс 10 с.)
    }

    private void setBrowser() {
        if(browser == null){
            browser="chrome";}
        if ("chrome".equals(browser)) {
            File fileFF = new File("./drivers/chromedriver.exe");                    // заімпортили веб драйвер для хрома
            System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath()); // установлюємо системну змінну, щоб знала шлях до файлу
            webDriver = new ChromeDriver();
        } else if ("ie".equals(browser)) {
            log.info("IE will be started");
            File file1 = new File(".././drivers/IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file1.getAbsolutePath());
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability("ignoreZoomSetting", true);
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            webDriver = new InternetExplorerDriver();
            log.info(" IE is started");
        } else {
            Assert.fail("Can not opens browser " + browser);
        }

    }

    @After
    public void tearDown() {
        webDriver.quit();                                        // Закриє браузер
    }
    @Step
    protected void checkAC(String message, Boolean actual, Boolean expected) {
        Assert.assertEquals(message + "Browser - " + browser + " ",  actual, expected);
    }



}
