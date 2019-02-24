package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
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

  @Test
  public void testGroupDeletionTests() throws Exception{
    app.goTo().homepage();
    Contacts before = app.db().contacts();
    ContactData deletecontact = before.iterator().next();
    app.contact().delete(deletecontact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletecontact)));
  }

}
