package org.example.repository;

import org.example.entity.Cinema;

public interface CinemaRepository {
    Cinema find(Long cinemaId);

    void save(Cinema cinema);

    void update(Cinema cinema);

    void delete(Long cinemaId);
}
