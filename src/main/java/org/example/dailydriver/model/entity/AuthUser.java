package org.example.dailydriver.model.entity;

import jakarta.persistence.*;
import lombok.*;

import org.example.dailydriver.model.entity.baseEntity.BaseEntity;
import org.example.dailydriver.model.enums.Role;
import org.example.dailydriver.model.enums.Status;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class AuthUser extends BaseEntity {

    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Enumerated(EnumType.STRING)
    private Status status = Status.REGISTERED;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
