package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreationTests() throws Exception
  {
    app.getContactHelper().createContact(new ContactData("Test","Test","Luxoft",
            "25", "February", "1992", "666"));

  }
}
