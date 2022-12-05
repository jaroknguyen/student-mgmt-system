package edu.student.system.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link edu.student.system.domain.Users} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UsersDTO implements Serializable {

  private Long id;

  private String firstName;

  private String lastName;

  private String login;

  private String hashPassword;

  private Boolean activated;

  private RolesDTO role;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getHashPassword() {
    return hashPassword;
  }

  public void setHashPassword(String hashPassword) {
    this.hashPassword = hashPassword;
  }

  public Boolean getActivated() {
    return activated;
  }

  public void setActivated(Boolean activated) {
    this.activated = activated;
  }

  public RolesDTO getRole() {
    return role;
  }

  public void setRole(RolesDTO role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UsersDTO)) {
      return false;
    }

    UsersDTO usersDTO = (UsersDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, usersDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "UsersDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", login='" + getLogin() + "'" +
            ", hashPassword='" + getHashPassword() + "'" +
            ", activated='" + getActivated() + "'" +
            ", role=" + getRole() +
            "}";
    }
}
