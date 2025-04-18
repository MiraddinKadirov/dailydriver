package org.example.dailydriver.model.dto.addressDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressCreateDto {

    private String city;
    private String street;
    private String houseNumber;

}
