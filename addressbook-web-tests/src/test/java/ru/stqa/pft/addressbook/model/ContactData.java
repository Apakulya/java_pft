package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  @Expose
  private String firstname;
  @Expose
  private String lastname;
  private String homephone;
  private String workphone;
  private String mobilephone;
  @Expose
  private String group;
  private String allphones;
  @Expose
  private String address;
  private String mail1;
  private String mail2;
  private String mail3;
  private String allmails;
  private String detailedinfo;
  private File photo;

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public File getPhoto() {
    return photo;
  }

  public ContactData WithDerailedinfo(String detailedinfo) {
    this.detailedinfo = detailedinfo;
    return  this;
  }
  public ContactData withAllPhones(String allphones) {
    this.allphones = allphones;
    return  this;
  }

  public String getAllPhones() {
    return allphones;
  }
  public String getAllMails() {
    return allmails;
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
  public ContactData withAllMails(String allmails) {
    this.allmails = allmails;
    return this;
  }
  public ContactData withMail1(String mail1) {
    this.mail1 = mail1;
    return this;
  }
  public ContactData withMail2(String mail2) {
    this.mail2 = mail2;
    return this;
  }
  public ContactData withMail3(String mail3) {
    this.mail3 = mail3;
    return this;
  }
  public ContactData withAddress(String address) {
    this.address = address;
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
  public String getDetailedinfo() {

    return detailedinfo;
  }
  public String getAddress() {
    return address;
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

  public String getMail1() {
    return mail1;
  }
  public String getMail2() {
    return mail2;
  }
  public String getMail3() {
    return mail3;
  }
}
