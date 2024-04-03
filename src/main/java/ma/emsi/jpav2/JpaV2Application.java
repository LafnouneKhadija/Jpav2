package ma.emsi.jpav2;

import ma.emsi.jpav2.Service.UserService;
import ma.emsi.jpav2.entities.Role;
import ma.emsi.jpav2.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaV2Application {

    public static void main(String[] args) {

        SpringApplication.run(JpaV2Application.class, args);
    }

    @Bean
        //l'injection des dependances
    CommandLineRunner start(UserService userService) {
        return args -> {
            User u = new User();
            u.setUsername("user1");
            u.setPassword("123456");
            //pour enregistrer
            userService.addNewUser(u);

            User u2 = new User();
            u.setUsername("admin");
            u.setPassword("123456");
            userService.addNewUser(u2);

            //Role role1= new Role();
            //role1.setRolename("Student");
            //pour enregistrer
            //userService.addNewRole(role1);
            Stream.of("Student", "User", "Admin").forEach(r -> {
                Role role1 = new Role();
                role1.setRoleName(r);
                //pour enregistrer
                userService.addNewRole(role1);
            });

            //on affecte les roles aux users
            userService.addRoleToUser("user1", "Student");
            userService.addRoleToUser("user1", "User");

            userService.addRoleToUser("Admin", "User");
            userService.addRoleToUser("Admin", "Admin");

            try {
                User user = userService.authenticate("user1", "123456");
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                System.out.println("Role=>");
                user.getRoles().forEach(r -> {
                    System.out.println("Role=>"+r.toString());
                });

            }
            catch (Exception e) {
                //pour afficher la trace d'exception
                e.printStackTrace();
            }

        };




    }
}
