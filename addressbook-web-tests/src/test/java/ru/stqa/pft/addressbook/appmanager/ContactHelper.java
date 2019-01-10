package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void fillGontactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLasttname());
    type(By.name("company"), contactData.getCompany());
    typeDropDown(By.name("bday"), contactData.getBday());
    typeDropDown(By.name("bmonth"), contactData.getBmonth());
    type(By.name("byear"), contactData.getByear());
    if (creation) {
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactreation() {
    click(By.name("submit"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void returntoHomePage() {
    click(By.linkText("home"));
  }

  public void selectContact(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContacts() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]")).click();
    driver.switchTo().alert().accept();
  }

  public void editContact() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='import'])[1]/following::img[2]")).click();
  }

  public void submitContactEdit() {
    driver.findElement(By.name("update")).click();
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillGontactForm(contact, true);
    submitContactreation();
    returntoHomePage();
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    WebElement table = driver.findElement(By.cssSelector("tbody"));
    List<WebElement> elements = table.findElements(By.tagName("tr"));
    for (WebElement element : elements) {
      if (element.getAttribute("name") != null) {
        String lastname = element.findElement(By.xpath(".//td[2]")).getText();
        String firstname = element.findElement(By.xpath(".//td[3]")).getText();
        int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
        ContactData contact = new ContactData(id, firstname, lastname);
        contacts.add(contact);
      }
    }
    return contacts;
  }
}
