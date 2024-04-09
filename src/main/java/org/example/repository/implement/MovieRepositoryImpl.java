package org.example.repository.implement;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Movie;
import org.example.exception.EntityNotFoundException;
import org.example.repository.MovieRepository;
import org.example.util.HikariCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public Movie find(Long movieId) {
        Movie movie;
        String sql = "select * from movie where id = ?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            movie = mapRowToMovie(resultSet);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException("Строка в базе данных с id=" + movieId + " не найдена");
        }
        return movie;
    }

    @Override
    public void save(Movie movie) {
        String sql = "insert into movie(movie_name, category, cinema_id) values (?,?,?)";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.setString(2, movie.getCategory());
            preparedStatement.setLong(3, movie.getCinemaId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Movie movie) {
        String sql = "update movie set movie_name=?, category=?, cinema_id=?  where id=?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.setString(2, movie.getCategory());
            preparedStatement.setLong(3, movie.getCinemaId());
            preparedStatement.setLong(4, movie.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long movieId) {
        String sql = "delete from movie where id=?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, movieId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Movie> findActorMovies(Long actorId) {
        Set<Movie> movies = new HashSet<>();
        String sql = "select * " +
                "from movie " +
                "where id in (select movie_id " +
                "from actor_movie " +
                "where actor_id=?)";
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, actorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movies.add(mapRowToMovie(resultSet));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return movies;
    }

    private Movie mapRowToMovie(ResultSet resultSet) throws SQLException {
        return Movie.builder()
                .id(resultSet.getLong("id"))
                .movieName(resultSet.getString("movie_name"))
                .category(resultSet.getString("category"))
                .cinemaId(resultSet.getLong("cinema_id"))
                .build();
    }
}
