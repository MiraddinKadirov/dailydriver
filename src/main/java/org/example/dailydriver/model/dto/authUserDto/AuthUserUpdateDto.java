package org.example.dailydriver.model.dto.authUserDto;

import lombok.*;
import org.example.dailydriver.model.dto.AuthUserBaseDto;
import org.example.dailydriver.model.entity.Address;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserUpdateDto implements AuthUserBaseDto {

    private String id;
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private Address address;

}
