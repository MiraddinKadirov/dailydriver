package org.example.dailydriver.model.dto.authUserDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.example.dailydriver.model.entity.Address;
import org.example.dailydriver.model.enums.Role;
import org.example.dailydriver.model.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserDto {

    private String id;
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private Role role;
    private Status status;
    private Address address;

}
