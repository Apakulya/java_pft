package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @BeforeTest
  public void ensureGroupExist(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testContactCreationTests() throws Exception {
    app.goTo().groupPage();
    Groups groups = app.group().all();
    GroupData group = groups.iterator().next();
   
    app.goTo().homepage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/photo.jpg");
    ContactData contact = new ContactData().withFirstName("Настюха").withLastName("Настюха").withGroup(group.getName()).withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(),equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded
            (contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
