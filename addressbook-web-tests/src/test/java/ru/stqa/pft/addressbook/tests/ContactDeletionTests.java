package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testGroupDeletionTests() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (!app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Илья","Ильич","Luxoft",
              "25", "February", "1992", "666"));
    }

    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().returntoHomePage();
  }
}
