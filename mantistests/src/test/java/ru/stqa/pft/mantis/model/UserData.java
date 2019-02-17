package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@XStreamAlias("user")
@Entity
@Table(name="mantis_user_table")
public class UserData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name="username")
  @Type(type = "string")
  private String name;
  @Expose
  @Column(name="email")
  @Type(type = "string")
  private String email;
  @Expose
  @Column(name="access_level")
  private short access_level;


 // public Users getUsers() {
 //   return new Users(users);
 // }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withName(String name) {
    this.name = name;
    return this;
  }


  public int getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
            Objects.equals(name, userData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
