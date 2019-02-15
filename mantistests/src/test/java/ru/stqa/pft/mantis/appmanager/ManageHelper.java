package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageHelper extends HelperBase {

  public ManageHelper(ApplicationManager app) {
    super(app);
  }


  public void startResetUserPassword(String username) {
    click(By.linkText("Manage"));
    click(By.linkText("Manage Users"));
    click(By.linkText(username));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void stopResetUserPassword(String ConfirmationLink, String password) {
    driver.get(ConfirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("input[value='Update User']"));
  }
}
