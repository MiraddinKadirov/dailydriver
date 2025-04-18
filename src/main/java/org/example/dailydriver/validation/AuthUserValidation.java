package org.example.dailydriver.validation;

import org.example.dailydriver.exeptionHandler.ValidateException;
import org.example.dailydriver.model.dto.AuthUserBaseDto;
import org.example.dailydriver.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthUserValidation {


    private final AuthUserRepository authUserRepository;

    public AuthUserValidation(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    public void validate(AuthUserBaseDto authUser) {
        if (!isUsernameUnique(authUser.getUsername())) {
            throw new ValidateException("Username already exists");
        }

        if (!isPhoneNumberUnique(authUser.getPhoneNumber())) {
            throw new ValidateException("Phone number already exists");
        }

        if (!isPasswordStrong(authUser.getPassword())) {
            throw new ValidateException("Password is not strong enough");
        }
    }

    private boolean isUsernameUnique(String username) {
        return !authUserRepository.existsByUsername(username);
    }

    private boolean isPhoneNumberUnique(String phoneNumber) {
        if (!phoneNumber.startsWith("+998")) {
            return false;
        }
        return !authUserRepository.existsByPhoneNumber(phoneNumber);
    }

    private boolean isPasswordStrong(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password != null && password.matches(regex);
    }


}
