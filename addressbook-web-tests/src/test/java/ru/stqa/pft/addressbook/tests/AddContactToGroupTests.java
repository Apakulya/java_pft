package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {
  @BeforeTest
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Тест1"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homepage();
      File photo = new File("src/test/resources/photo.jpg");
      app.contact().create(new ContactData().withFirstName("test").withLastName("test").withPhoto(photo).inGroup(app.db().groups().iterator().next()));
    }
  }

  @Test
  public void testAddContactToGroup() throws Exception {
    ContactData contactToAdd = app.db().contacts().iterator().next();
    GroupData group = app.db().groups().iterator().next();
    app.goTo().homepage();
    app.contact().AddToGroup(contactToAdd,group);
    Contacts contactAddedToGroup = app.db().contactsbyID(contactToAdd.getId());
    assertThat(contactAddedToGroup.iterator().next(), equalTo(contactToAdd.inGroup(group)));
  }
}
