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

public class GitHubTest {

    public String SIGN_IN_INPUT = "//input[@value='Sign in']";
    public String INPUT_PASSWORD = "//input[@id='password']";
    public String LOGIN_FIELD = "login_field";
    public String GO_TO_SIGN_IN_TEXT = "Sign in";
    public String YOUR_REPOSITORIES = "//div[@id='your_repos']//h3[contains(.,'Your repositories')]";
    public String PASSWORD = "ftm2016q4_!";
    public String LOGIN = "ftm2016q4";
    public String START_URL = "https://github.com/";

    public WebDriver driver;

    @BeforeClass(description = "Start browser")
    public void startBrowser() {
        // initialize WebDriver for Chrome. Please mind webdriver, chromedriver
        // version and chrome browser versions.
        // works for webdriver v3.0.1, chromedriver v2.26, chrome browser
        // v55
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

    @Test(description = "Login to Github account")
    public void loginToGithub() {
        // Login via user-defined method
        doLogin(LOGIN, PASSWORD);

        // Verify the login procedure was correct
        Assert.assertTrue(isElementPresent(By.xpath(YOUR_REPOSITORIES)), "Looks you are NOT logged in correctly!");
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
        driver.findElement(By.linkText(GO_TO_SIGN_IN_TEXT)).click();

        // Find the text input element by "id" attribute with a certain value
        // and type the user name there
        WebElement loginInput = driver.findElement(By.id(LOGIN_FIELD));
        loginInput.clear();
        loginInput.sendKeys(login);

        // Find the text input element by xpath expression and type the password
        // there
        WebElement passwordInput = driver.findElement(By.xpath(INPUT_PASSWORD));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        // Now submit the form. WebDriver will find the form for us from the
        // element
        driver.findElement(By.xpath(SIGN_IN_INPUT)).click();
    }

}
