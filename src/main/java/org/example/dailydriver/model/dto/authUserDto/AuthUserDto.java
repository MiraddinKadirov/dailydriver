package org.example.dailydriver.model.dto.authUserDto;

import lombok.*;
import org.example.dailydriver.model.dto.addressDto.AddressDto;
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
    private String fullName;
    private String phoneNumber;
    private Role role;
    private Status status;
    private AddressDto address;

}
