package ma.emsi.jpav2.repositories;

import ma.emsi.jpav2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);

}
