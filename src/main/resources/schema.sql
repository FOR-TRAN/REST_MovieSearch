DROP TABLE IF EXISTS actor_movie;
DROP TABLE IF EXISTS actor;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS cinema;

CREATE TABLE actor
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    actor_name varchar(255) NOT NULL
);

CREATE TABLE cinema
(
    id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar(255),
    city     varchar(255) NOT NULL
);

--Many-to-One
CREATE TABLE movie
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    movie_name varchar(255) NOT NULL,
    category varchar(255) NOT NULL,
    cinema_id     BIGINT NOT NULL,
    CONSTRAINT fk_cinema_where_movie_exist FOREIGN KEY (cinema_id) REFERENCES cinema (id)
);

--Many-to-Many
CREATE TABLE actor_movie
(
    actor_id BIGINT,
    movie_id    BIGINT,
    CONSTRAINT actor_movie_pk PRIMARY KEY (actor_id, movie_id),
    CONSTRAINT actor_id_fk FOREIGN KEY (actor_id) REFERENCES actor (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT movie_id_fk FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE INDEX idx_cinema_id ON movie (cinema_id);
CREATE INDEX idx_actor_id ON actor_movie (actor_id);
CREATE INDEX idx_movie_id ON actor_movie (movie_id);