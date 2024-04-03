package ma.emsi.jpav2.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.emsi.jpav2.entities.Role;
import ma.emsi.jpav2.entities.User;
import ma.emsi.jpav2.repositories.RoleRepository;
import ma.emsi.jpav2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service //compenent pour la couche service
@Transactional
@AllArgsConstructor

public class UserServiceImpl implements UserService {

    //on implemente
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    //l'injection de dependance par constructeur ou par annotation @AllArgsConstructor
    //public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        //this.userRepository = userRepository;
        //this.roleRepository = roleRepository;
    //}

    @Override
    public User addNewUser(User user) {
        //charger un user 
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }
    @Override
    public Role addNewRole(Role role) {

        // id est generer automatiquement par bd
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {

        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        // charger user et role
        User user=findUserByUserName(username);
        Role role=findRoleByRoleName(roleName);

        //Ajouter role au user
        if (user != null) {
            if (user.getRoles() != null) {
                user.getRoles().add(role);
                role.getUsers().add(user);
            } }
            //userRepository.save(user);

    }

    @Override
    public User authenticate(String username, String password) {
        //on va charger user
        User user=userRepository.findByUsername(username);
        //on va verifier password
        if(user==null) throw new RuntimeException("Bad credentials");
        if(user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("Bad credentials");
    }
}
