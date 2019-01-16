package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void  ensurePreconditions(){
    app.goTo().homepage();
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData().withFirstName("Илья").withLastName("Ильич"));
    }
  }
  @Test
  public void testGroupDeletionTests() throws Exception {
    Contacts before = app.contact().all();
    ContactData deletecontact = before.iterator().next();
    app.contact().delete(deletecontact);
    assertThat(app.contact().count(), equalTo(before.size()-1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletecontact)));
  }

}
