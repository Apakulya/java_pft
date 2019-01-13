package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void  ensurePreconditions(){
    app.goTo().homepage();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData("Илья","Ильич","Luxoft",
              "25", "February", "1992", "666"));
    }
  }
  @Test
  public void testGroupDeletionTests() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before,after);
  }

  private void delete(int index) {
    app.contact().selectContact(index);
    app.contact().deleteSelectedContacts();
    app.contact().returntoHomePage();
  }
}
