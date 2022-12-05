package edu.student.system.domain;

import edu.student.system.domain.enumeration.RoleName;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Roles.
 */
@Entity
@Table(name = "roles")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Roles implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequenceGenerator"
  )
  @SequenceGenerator(name = "sequenceGenerator")
  @Column(name = "id")
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "name")
  private RoleName name;

  public Long getId() {
    return this.id;
  }

  public Roles id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RoleName getName() {
    return this.name;
  }

  public Roles name(RoleName name) {
    this.setName(name);
    return this;
  }

  public void setName(RoleName name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Roles)) {
      return false;
    }
    return id != null && id.equals(((Roles) o).id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Roles{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
