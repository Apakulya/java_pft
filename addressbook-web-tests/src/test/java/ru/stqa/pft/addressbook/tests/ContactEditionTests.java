package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactEditionTests extends TestBase{
  @Test
  public void testContactEditionTests() throws Exception {
    app.getNavigationHelper().gotoLogopage();
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fillGontactForm(new ContactData("Тест","Тестович","Luxoft",
            "25", "February", "1992", null), false);
    app.getContactHelper().submitContactEdit();
    app.getContactHelper().returntoContactPage();
  }
}
