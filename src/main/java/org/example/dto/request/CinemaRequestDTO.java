package org.example.dto.request;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaRequestDTO {
    private String name;
    private String city;
}
