package org.example.dailydriver;

import org.example.dailydriver.model.entity.AuthUser;
import org.example.dailydriver.model.enums.Role;
import org.example.dailydriver.repository.AuthUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DailydriverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailydriverApplication.class, args);
    }

//    @Bean
    public CommandLineRunner commandLineRunner(
            PasswordEncoder passwordEncoder,
            AuthUserRepository repository
    ) {
        return args -> {
            AuthUser authUser = new AuthUser();
            authUser.setUsername("admin");
            authUser.setPassword(passwordEncoder.encode("admin"));
            authUser.setRole(Role.ADMIN);

            repository.save(authUser);

        };
    }
}
