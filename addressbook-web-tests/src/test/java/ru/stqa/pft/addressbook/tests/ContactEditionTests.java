package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEditionTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    Groups groups = app.group().all();
    GroupData group = groups.iterator().next();
    app.goTo().homepage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Илья").withLastName("Ильич").withGroup(group.getName()));
    }
  }
  @Test   public void testContactEditionTests() throws Exception {
    Contacts before = app.contact().all();
    ContactData editedcontact = before.iterator().next();
    ContactData contact = new ContactData().withId(editedcontact.getId()).withFirstName("Илья").withLastName("Ильич");
    app.contact().edit(contact);
    assertThat(app.contact().count(), CoreMatchers.equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after,CoreMatchers.equalTo(before.without(editedcontact).withAdded(contact)));
  }


}
