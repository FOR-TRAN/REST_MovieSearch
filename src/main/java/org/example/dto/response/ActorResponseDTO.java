package org.example.dto.response;
import lombok.*;
import org.example.entity.Movie;


import java.util.Objects;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorResponseDTO {
    private Long id;
    private String actorName;
    private Set <Movie> movies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorResponseDTO that = (ActorResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(actorName, that.actorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actorName);
    }
}
