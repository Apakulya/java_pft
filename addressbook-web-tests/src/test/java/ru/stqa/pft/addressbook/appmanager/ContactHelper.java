package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void fillGontactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLasttname());
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

  public void deleteSelectedContacts() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]")).click();
    driver.switchTo().alert().accept();
  }

  public void edit() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='import'])[1]/following::img[2]")).click();
  }

  public void submitContactEdit() {
    driver.findElement(By.name("update")).click();
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillGontactForm(contact, true);
    submitContactreation();
    contactCache = null;
    returntoHomePage();
  }
  public void edit(ContactData contact) {
    selectContactById(contact.getId());
    edit();
    fillGontactForm(contact, false);
    submitContactEdit();
    contactCache = null;
    returntoHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    contactCache = null;
    returntoHomePage();
  }


  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }
  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache!=null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    WebElement table = driver.findElement(By.cssSelector("tbody"));
    List<WebElement> elements = table.findElements(By.tagName("tr"));
    for (WebElement element : elements) {
      if (element.getAttribute("name") != null) {
        String lastname = element.findElement(By.xpath(".//td[2]")).getText();
        String firstname = element.findElement(By.xpath(".//td[3]")).getText();
        int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
        contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
      }
    }
    return new Contacts(contactCache);
  }

  public int count() {
    return driver.findElements(By.name("selected[]")).size();
  }


  private void selectContactById(int id) {
      driver.findElement(By.cssSelector("input[value='"+ id + "']")).click();
  }
}
