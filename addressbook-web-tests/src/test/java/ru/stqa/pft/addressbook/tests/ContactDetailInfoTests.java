package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailInfoTests extends TestBase {
  @Test
  public void testContactInfo(){
    app.goTo().homepage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);
    app.goTo().homepage();
    ContactData contactInfoFromDetailedForm = app.contact().InfoFromDetailedForm(contact);
    assertThat((MergeDetailedInfo(contactInfoFromDetailedForm.getDetailedinfo())), equalTo(MergeInfo(contactInfoFromEditForm)));
  }
  private Object MergeInfo(ContactData contact) {
    return Arrays.asList(contact.getFirstName()+" "+contact.getLasttname()+"\n\nH: "+contact.getHomePhone()+"\nM: "+contact.getMobilePhone()+"\nW: "+ contact.getWorkPhone()+"\n",contact.getMail1(),contact.getMail2(),contact.getMail3(),contact.getAddress())
            .stream().filter(s -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }
  private Object MergeDetailedInfo(String Detailedinfo) {
    return Arrays.asList(Detailedinfo)
            .stream().filter(s -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}
