package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
  private final Properties properties;
  private WebDriver driver;
  private String browser;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    System.setProperty("webdriver.gecko.driver", "C:\\Users\\Asus\\Documents\\Geckodriver\\geckodriver.exe");
    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

  }

  public void stop() {
    if (driver != null) {
      driver.quit();
    }
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }


  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public WebDriver getDriver() {
    if (driver == null) {
      if (browser.equals(BrowserType.FIREFOX)) {
        driver = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        driver = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
        driver = new InternetExplorerDriver();
      }
      driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      driver.get(properties.getProperty("web.baseURL"));
    }
    return driver;
  }

  public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }
}
