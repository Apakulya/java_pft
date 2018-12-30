package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lasttname;
  private final String company;
  private final String bday;
  private final String bmonth;
  private final String byear;
  private final String group;


  public ContactData(String firstname, String lastname, String company, String bday, String bmonth, String byear, String group) {
    this.firstname = firstname;
    this.lasttname = lastname;
    this.company = company;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.group = group;
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
  public String getBday() {

    return bday;
  }
  public String getBmonth() {

    return bmonth;
  }
  public String getByear() {

    return byear;
  }
  public String getGroup() {

    return group;
  }
}
