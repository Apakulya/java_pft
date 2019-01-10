package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String firstname;
  private final String lasttname;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lasttname, that.lasttname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lasttname);
  }

  private  String company;
  private  String bday;
  private  String bmonth;
  private  String byear;
  private  String group;

  public void setId(int id) {
    this.id = id;
  }
  public ContactData(Integer id, String firstname, String lastname) {
    this.id = id;
    this.firstname = firstname;
    this.lasttname = lastname;
  }
  public ContactData(String firstname, String lastname, String company, String bday, String bmonth, String byear, String group) {
    this.firstname = firstname;
    this.lasttname = lastname;
    this.company = company;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.group = group;
  }
  public ContactData(String firstname, String lastname) {
    this.firstname = firstname;
    this.lasttname = lastname;
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

  public int getId() {
    return id;
  }
}
