package org.example.dto.request;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequestDTO {
private String movieName;
private String category;
private Long cinemaId;
}
