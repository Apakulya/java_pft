package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreationTests() throws Exception
  {
    app.getNavigationHelper().gotoGontactCreationPage();
    app.getContactHelper().fillGontactForm(new ContactData("Илья","Ильич","Luxoft",
            "25", "February", "1992", "666"), true);
    app.getContactHelper().submitContactreation();
    app.getContactHelper().returntoContactPage();
  }
}
