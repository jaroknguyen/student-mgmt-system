package edu.student.system.service.mapper;

import edu.student.system.domain.Roles;
import edu.student.system.service.dto.RolesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Roles} and its DTO {@link RolesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RolesMapper extends EntityMapper<RolesDTO, Roles> {

    default Roles fromId(Long id) {
        if (id == null) {
            return null;
        }
        Roles roles = new Roles();
        return roles;
    }
}
