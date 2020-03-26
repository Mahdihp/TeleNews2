package com.telenews.config;

import com.telenews.models.comment.Comment;
import com.telenews.models.comment.repository.CommentRepo;
import com.telenews.models.news.repository.NewsRepo;
import com.telenews.models.role.Role;
import com.telenews.models.role.RoleName;
import com.telenews.models.role.repository.RoleRepo;
import com.telenews.models.user.User;
import com.telenews.models.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        addRole();
        addUser();
    }

    private void addUser() {
        if (userRepo.count() <= 0) {
            User userAdmin = new User();
            userAdmin.setUserName("mahdihp");
            userAdmin.setPassWord(encoder.encode("mahdihp"));
            userAdmin.setActive(true);
            userAdmin.setDisplayName("Mahdi Hp");
            userAdmin.setEmail("mahdihp.devsc@gmail.com");
            Set<Role> roles = new HashSet<>();
            Role roleAdmin = roleRepo.findByName(RoleName.Role_Admin);
            roles.add(roleAdmin);
            userAdmin.setRoles(roles);

            userRepo.save(userAdmin);
            System.out.println("Add Users...");
        }
    }

    private void addRole() {
        if (roleRepo.count() <= 0) {
            Role roleAdmin = new Role();
            roleAdmin.setName(RoleName.Role_Admin);

            Role roleUser = new Role();
            roleUser.setName(RoleName.Role_User);

            Role roleAuther = new Role();
            roleAuther.setName(RoleName.Role_Author);

            roleRepo.save(roleAdmin);
            roleRepo.save(roleUser);
            roleRepo.save(roleAuther);
            System.out.println("Add Roles...");
        }
    }
}
