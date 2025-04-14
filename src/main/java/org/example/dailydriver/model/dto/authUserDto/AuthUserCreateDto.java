package org.example.dailydriver.model.dto.authUserDto;

import lombok.*;
import org.example.dailydriver.model.dto.AuthUserBaseDto;
import org.example.dailydriver.model.entity.Address;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserCreateDto implements AuthUserBaseDto {

    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
/*
    private Address address;
*/

}
