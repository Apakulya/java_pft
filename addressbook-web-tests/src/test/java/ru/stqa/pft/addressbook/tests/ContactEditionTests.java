package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactEditionTests extends TestBase{
  @Test
  public void testContactEditionTests() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (!app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Илья","Ильич","Luxoft",
              "25", "February", "1992", "666"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().editContact();
    ContactData contact = new ContactData("Тест","Тестович","Luxoft",
            "25", "February", "1992", null);
    app.getContactHelper().fillGontactForm(contact, false);
    app.getContactHelper().submitContactEdit();
    app.getContactHelper().returntoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
