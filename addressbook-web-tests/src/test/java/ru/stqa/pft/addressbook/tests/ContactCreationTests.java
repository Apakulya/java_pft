package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreationTests() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("FFF", "Петрович", "luxoft", "16", "January", "1992", "test1");
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    before.get(before.size() - 1).setId(after.get(after.size() - 1).getId());
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
