package org.example.dto.update;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorUpdateDTO {
    private Long id;
    private String actorName;
}
