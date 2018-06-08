package ex.guesser.areas.user.repositories;

import ex.guesser.areas.user.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findFirstByAuthority(String authority);

}
