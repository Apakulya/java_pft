package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactHelper extends HelperBase{
  public ContactHelper(WebDriver driver) {
    super(driver);
  }
  public void fillGontactForm (ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLasttname());
    type(By.name("company"), contactData.getCompany());
  }
}
