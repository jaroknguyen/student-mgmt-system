package edu.student.system.service.mapper;

import edu.student.system.domain.Roles;
import edu.student.system.domain.Users;
import edu.student.system.service.dto.RolesDTO;
import edu.student.system.service.dto.UsersDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Users} and its DTO {@link UsersDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UsersMapper extends EntityMapper<UsersDTO, Users> {
  @Mapping(target = "role", source = "role", qualifiedByName = "rolesId")
  UsersDTO toDto(Users s);

  @Named("rolesId")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  RolesDTO toDtoRolesId(Roles roles);
}
