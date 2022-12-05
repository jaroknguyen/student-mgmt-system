package edu.student.system.service.dto;

import edu.student.system.domain.enumeration.RoleName;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link edu.student.system.domain.Roles} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RolesDTO implements Serializable {

  private Long id;

  private RoleName name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RoleName getName() {
    return name;
  }

  public void setName(RoleName name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RolesDTO)) {
      return false;
    }

    RolesDTO rolesDTO = (RolesDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, rolesDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "RolesDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
