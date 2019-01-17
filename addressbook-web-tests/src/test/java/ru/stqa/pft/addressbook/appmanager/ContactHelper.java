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
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    String bag = contactData.getGroup();
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

  public Contacts Oldall() {
    if (contactCache != null) {
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
  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = driver.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allphones = cells.get(5).getText();
      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
              .withAllPhones(allphones));
     }
    return new Contacts(contactCache);
  }
  public int count() {
    return driver.findElements(By.name("selected[]")).size();
  }


  private void selectContactById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public ContactData InfoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
    String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
    String home = driver.findElement(By.name("home")).getAttribute("value");
    String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
    String work = driver.findElement(By.name("work")).getAttribute("value");
    driver.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }
  private void initContactModificationById(int id) {
    // WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']",id)));
    // WebElement row = checkbox.findElement(By.xpath("./../.."));
    // List<WebElement> cells = row.findElements(By.tagName("td"));
    // cells.get(7).findElement(By.tagName("a")).click();
    // driver.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click(); //последовательный переход
    // driver.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id))).click(); //подзапрос
    driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

}
