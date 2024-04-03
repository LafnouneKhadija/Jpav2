package ma.emsi.jpav2.Service;

import ma.emsi.jpav2.entities.Role;
import ma.emsi.jpav2.entities.User;

public interface UserService {
    // methode permet de cree un user et un role
    User addNewUser(User user);
    Role addNewRole(Role role);
    // methodes permet de chercher  user par son nom , et le role par son nom
    User findUserByUserName(String username);
    Role findRoleByRoleName(String rolename);
    // Ajouter un role à un user
    void addRoleToUser(String username,String rolename);
    User authenticate(String username, String password);

}
