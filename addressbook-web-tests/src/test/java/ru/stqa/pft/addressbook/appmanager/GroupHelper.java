package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.*;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver driver) {
    super(driver);
  }

  public void returntoGroupPage() {
    click(By.linkText("group page"));
  }

  private void selectGroupById(int id) {
    driver.findElement(By.cssSelector("input[value='"+ id + "']")).click();
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {

    click(By.name("delete"));
  }

  public void editGroup() {
    driver.findElement(By.name("edit")).click();
  }

  public void submitGroupEdition() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Groups'])[1]/following::form[1]")).click();
    driver.findElement(By.name("update")).click();
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returntoGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    returntoGroupPage();
  }

  public void edit(GroupData group) {
    selectGroupById(group.getId());
    editGroup();
    fillGroupForm(group);
    submitGroupEdition();
    returntoGroupPage();
  }

  public boolean isThereGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public Groups all() {
    Groups groups = new Groups();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }

}
