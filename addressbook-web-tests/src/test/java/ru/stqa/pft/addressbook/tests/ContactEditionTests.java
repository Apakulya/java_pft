package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEditionTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size()==0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homepage();
      File photo = new File("src/test/resources/photo.jpg");
      app.contact().create(new ContactData().withFirstName("test").withLastName("test").withPhoto(photo).inGroup(app.db().groups().iterator().next()));
    }
  }
  @Test   public void testContactEditionTests() throws Exception {
    Contacts before = app.db().contacts();
    ContactData editedcontact = before.iterator().next();
    File photo = new File("src/test/resources/photo.jpg");
    ContactData contact = new ContactData().withId(editedcontact.getId()).withFirstName("test").withLastName("test").withPhoto(photo);
    app.contact().edit(contact);
    assertThat(app.contact().count(), CoreMatchers.equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after,CoreMatchers.equalTo(before.without(editedcontact).withAdded(contact)));
  }

}
