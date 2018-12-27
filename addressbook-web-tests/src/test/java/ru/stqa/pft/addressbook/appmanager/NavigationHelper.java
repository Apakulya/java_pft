package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver driver) {
    super(driver);
  }

  public void gotoGroupPage() {

    click(By.linkText("groups"));
  }

  public void gotoGontactCreationPage() {
    click(By.linkText("add new"));
  }

  public void gotoLogopage() {
    driver.findElement(By.id("logo")).click();
  }

}
