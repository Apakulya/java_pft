package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
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

  @Test
  public void testGroupDeletionTests() throws Exception {
    Contacts before = app.contact().all();
    ContactData deletecontact = before.iterator().next();
    app.contact().delete(deletecontact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletecontact)));
  }

}
