package org.example.dto.response;
import lombok.*;
import org.example.entity.Actor;
import java.util.Objects;

import java.util.Objects;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDTO {
private Long id;
private String movieName;
private String category;
private CinemaResponseDTO cinema;
private Set<Actor> actors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieResponseDTO that = (MovieResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(movieName, that.movieName) && Objects.equals(category, that.category) && Objects.equals(cinema, that.cinema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieName, category, cinema);
    }
}
