package org.example.dailydriver.model.entity.baseEntity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;


import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Identity {

    @Id
    private String id = UUID.randomUUID().toString();

}
