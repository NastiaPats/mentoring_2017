import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Login {

    public String START_URL = "http://mail.google.com/";
    //public String SIGN_IN_INPUT = "";
    public String PASSWORD_FIELD = "//*[@id=\"Passwd\"]";
    public String LOGIN_FIELD = "//*[@id=\"Email\"]";
    public String CHECK_PAGE = "";
    public String PASSWORD = "Test123#";
    public String LOGIN = "nastia.pats.test@gmail.com";
    public String NEXT_BUTTON = "//*[@id=\"next\"]";

    public WebDriver driver;

    @BeforeClass(description = "Start browser")
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anastasiya_Pats\\IdeaProjects\\anastasiya_pats_mentoring_2017\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
    }
    @BeforeClass(dependsOnMethods = "startBrowser", description = "Add implicite wait and maximize window")
    public void addImplicitly() {
        // setting standard timeout
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // navigating to test url
        driver.get(START_URL);
        // Maximize browser window
        driver.manage().window().maximize();
    }

    @Test
    public void LoginToGoogle()  {
        // Login via user-defined method
        doLogin(LOGIN, PASSWORD);

        // Verify the login procedure was correct
        Assert.assertTrue(isElementPresent(By.xpath(CHECK_PAGE)), "Looks you are NOT logged in correctly!");
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        driver.close();
    }

    private boolean isElementPresent(By by) {
        // Custom implementation for is ElementPresent
        return !driver.findElements(by).isEmpty();
    }

    private void doLogin(String login, String password) {

        // Find the "button" input element by link text
        // Find the text input element by "id" attribute with a certain value
        // and type the user name there
        WebElement loginInput = driver.findElement(By.xpath(LOGIN_FIELD));
        loginInput.clear();
        loginInput.sendKeys(login);
        driver.findElement(By.xpath(NEXT_BUTTON)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Find the text input element by xpath expression and type the password
        // there
        driver.findElement(By.xpath(PASSWORD_FIELD));
        driver.findElement(By.xpath(PASSWORD_FIELD)).click();
        driver.findElement(By.xpath(PASSWORD_FIELD)).clear();
        driver.findElement(By.xpath(PASSWORD_FIELD)).sendKeys(PASSWORD);

        // Now submit the form. WebDriver will find the form for us from the
        // element
    }

}