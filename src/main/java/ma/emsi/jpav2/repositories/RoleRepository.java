package ma.emsi.jpav2.repositories;

import ma.emsi.jpav2.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role,Long> {
Role findByRoleName (String roleName);
}