package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{
  public ContactHelper(WebDriver driver) {
    super(driver);
  }
  public void fillGontactForm (ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLasttname());
    type(By.name("company"), contactData.getCompany());
    typeDropDown(By.name("bday"), contactData.getBday());
    typeDropDown(By.name("bmonth"), contactData.getBmonth());
    type(By.name("byear"), contactData.getBYear());
  }

  public void submitContactreation() {
    click(By.name("submit"));
  }

  public void returntoContactPage() {
    click(By.linkText("add new"));
  }
  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContacts() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]")).click();
    driver.switchTo().alert().accept();
  }
}
