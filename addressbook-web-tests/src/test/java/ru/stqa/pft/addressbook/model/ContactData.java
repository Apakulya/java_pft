package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String homephone;
  private String workphone;
  private String mobilephone;
  private String group;
  private String allphones;

  public ContactData withAllPhones(String allphones) {
    this.allphones = allphones;
    return  this;
  }

  public String getAllPhones() {
    return allphones;
  }

  public ContactData withId (int id) {
    this.id = id;
    return this;
  }
  public ContactData withFirstName(String firstname) {
    this.firstname = firstname;
    return this;
  }
  public ContactData withLastName(String lastname) {
    this.lastname = lastname;
    return this;
  }
  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {

    return firstname;
  }

  public String getLasttname() {

    return lastname;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  public int getId() {
    return id;
  }

  public String getGroup() {
    return group;
  }

  public ContactData withHomePhone(String homephone) {
    this.homephone = homephone;
    return this;
  }
  public ContactData withWorkPhone(String workphone) {
    this.workphone = workphone;
    return this;
  }
  public ContactData withMobilePhone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public String getHomePhone() {
    return homephone;
  }
  public String getWorkPhone() {
    return workphone;
  }
  public String getMobilePhone() {
    return mobilephone;
  }

}
