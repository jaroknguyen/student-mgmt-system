package edu.student.system.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * A Users.
 */
@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Users implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequenceGenerator"
  )
  @SequenceGenerator(name = "sequenceGenerator")
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "login")
  private String login;

  @Column(name = "hash_password")
  private String hashPassword;

  @Column(name = "activated")
  private Boolean activated;

  @ManyToOne
  private Roles role;

  public Long getId() {
    return this.id;
  }

  public Users id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public Users firstName(String firstName) {
    this.setFirstName(firstName);
    return this;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public Users lastName(String lastName) {
    this.setLastName(lastName);
    return this;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLogin() {
    return this.login;
  }

  public Users login(String login) {
    this.setLogin(login);
    return this;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getHashPassword() {
    return this.hashPassword;
  }

  public Users hashPassword(String hashPassword) {
    this.setHashPassword(hashPassword);
    return this;
  }

  public void setHashPassword(String hashPassword) {
    this.hashPassword = hashPassword;
  }

  public Boolean getActivated() {
    return this.activated;
  }

  public Users activated(Boolean activated) {
    this.setActivated(activated);
    return this;
  }

  public void setActivated(Boolean activated) {
    this.activated = activated;
  }

  public Roles getRole() {
    return this.role;
  }

  public void setRole(Roles roles) {
    this.role = roles;
  }

  public Users role(Roles roles) {
    this.setRole(roles);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Users)) {
      return false;
    }
    return id != null && id.equals(((Users) o).id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Users{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", login='" + getLogin() + "'" +
            ", hashPassword='" + getHashPassword() + "'" +
            ", activated='" + getActivated() + "'" +
            "}";
    }
}
