package org.example.dto.update;
import lombok.*;

import javax.management.MXBean;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CinemaUpdateDTO {
    private Long id;
    private String name;
    private String city;
}
