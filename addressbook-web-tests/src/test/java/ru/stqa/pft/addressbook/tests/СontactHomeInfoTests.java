package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class СontactHomeInfoTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    Groups groups = app.group().all();
    GroupData group = groups.iterator().next();
    app.goTo().homepage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Илья").withLastName("Ильич").withGroup(group.getName()));
    }
  }

 @Test
 public void testContactInfo(){
   app.goTo().homepage();
   ContactData contact = app.contact().all().iterator().next();
   ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);
   assertThat((contact.getAllPhones()), equalTo(MergePhones(contactInfoFromEditForm)));
   assertThat((contact.getAllMails()), equalTo(MergeMails(contactInfoFromEditForm)));
   assertThat((contact.getAddress()), equalTo(contactInfoFromEditForm.getAddress()));
 }

  private Object MergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter(s -> !s.equals(""))
            .map(СontactHomeInfoTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private Object MergeMails(ContactData contact) {
    return Arrays.asList(contact.getMail1(), contact.getMail2(),contact.getMail3())
            .stream().filter(s -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){

    return phone.replaceAll("\\s","").replaceAll("[-()]","");
 }
  }
