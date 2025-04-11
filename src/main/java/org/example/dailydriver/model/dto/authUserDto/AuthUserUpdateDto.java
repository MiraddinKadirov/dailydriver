package org.example.dailydriver.model.dto.authUserDto;

import lombok.*;
import org.example.dailydriver.model.entity.Address;
import org.example.dailydriver.model.enums.Role;
import org.example.dailydriver.model.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserUpdateDto {

    private String id;
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private Address address;

}
