/*
package org.example.dailydriver.validation;

import org.example.dailydriver.model.entity.AuthUser;
import org.example.dailydriver.repository.AuthUserRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class SpringShell {

    private final AuthUserRepository authUserRepository;


    public SpringShell(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @ShellMethod
    public String users() {
        StringBuilder builder = new StringBuilder();
        List<AuthUser> authUsers = authUserRepository.findAllByDeletedFalse().orElse(null);
        assert authUsers != null;
        for (AuthUser authUser : authUsers) {
            builder.append(authUser.toString());
        }
        return builder.toString();
    }

}
*/
