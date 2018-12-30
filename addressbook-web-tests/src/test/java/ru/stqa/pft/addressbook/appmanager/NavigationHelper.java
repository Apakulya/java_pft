package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver driver) {
    super(driver);
  }

  public void gotoGroupPage() {
    if (isElementPresent(By.tagName("h1"))
            && driver.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.tagName("new"))) {
      return;
    }
      click(By.linkText("groups"));
  }


  public void gotoLogopage() {
    driver.findElement(By.id("logo")).click();
  }
  public void gotoHomepage() {
    if (isElementPresent(By.id("maintable")))
    {
      return;
    }
    click(By.linkText("home"));
  }
}
