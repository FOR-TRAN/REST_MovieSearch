package org.example.entity;
import lombok.*;


import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    private Long id;
    private String name;
    private String city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return Objects.equals(id, cinema.id) && Objects.equals(name, cinema.name) && Objects.equals(city, cinema.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city);
    }

}
