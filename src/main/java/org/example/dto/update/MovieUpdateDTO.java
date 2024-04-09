package org.example.dto.update;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieUpdateDTO {
    private Long id;
    private String movieName;
    private String category;
    private Long cinemaId;
}
