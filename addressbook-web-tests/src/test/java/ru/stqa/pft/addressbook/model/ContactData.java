package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lasttname;
  private final String company;


  public ContactData(String firstname, String lastname, String company) {
    this.firstname = firstname;
    this.lasttname = lastname;
    this.company = company;
  }

  public String getFirstName() {

    return firstname;
  }

  public String getLasttname() {
    return lasttname;
  }

  public String getCompany() {
    return company;
  }
}
