package org.example.dailydriver.model.dto.fileDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {
    private String id;
    private String originalName;
    private String storedName;
    private String path;
    private Long size;
    private String contentType;
    private String carId;
}
