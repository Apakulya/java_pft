package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="addressbook")
public class ContactData {
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name="firstname")
  private String firstname;
  @Expose
  @Column(name="lastname")
  private String lastname;
  @Column(name="home")
  @Type(type = "text")
  @Expose
  private String homephone;
  @Column(name="work")
  @Type(type = "text")
  private String workphone;
  @Column(name="mobile")
  @Type(type = "text")
  private String mobilephone;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="address_in_groups",joinColumns
          = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name="group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();
  @Transient
  private String allphones;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(homephone, that.homephone) &&
            Objects.equals(groups, that.groups) &&
            Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, homephone, groups, address);
  }

  @Expose
  //@Transient
  @Column(name="address")
  @Type(type = "text")
  private String address;
  @Transient
  private String mail1;
  @Transient
  private String mail2;

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", homephone='" + homephone + '\'' +
            ", groups=" + groups +
            ", address='" + address + '\'' +
            '}';
  }

  @Transient
  private String mail3;
  @Transient
  private String allmails;
  @Transient
  private String detailedinfo;

  @Column(name="photo")
  @Type(type="text")
  private String photo;

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public File getPhoto() {
    return new File(photo);
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

  public Groups getGroups() {
    return new Groups(groups);
  }

  public String getDetailedinfo() {

    return detailedinfo;
  }
  public String getAddress() {
    return address;
  }

  public int getId() {
    return id;
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

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
  public ContactData removefromGroup(GroupData group) {
    groups.remove(group);
    return this;
  }
}
