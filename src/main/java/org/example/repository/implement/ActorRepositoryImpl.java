package org.example.repository.implement;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.EntityNotFoundException;
import org.example.util.HikariCPDataSource;
import org.example.entity.Actor;
import org.example.repository.ActorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ActorRepositoryImpl implements ActorRepository {

    @Override
    public Actor find(Long actorId) {
        Actor actor;
        String sql = "select * from actor where id = ?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, actorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            actor = mapRowToActor(resultSet);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException("Строка в базе данных с id=" + actorId + " не найдена");
        }
        return actor;
    }

    @Override
    public void save(Actor actor) {
        String sql = "insert into actor(actor_name) values (?)";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, actor.getActorName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Actor actor) {
        String sql = "update actor set actor_name=? where id=?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, actor.getActorName());
            preparedStatement.setLong(2, actor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Long actorId) {
        String sql = "delete from actor where id=?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, actorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findMovie(Long actorId, Long movieId) {
        String sql = "insert into actor_movie(actor_id, movie_id) values (?,?)";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, actorId);
            preparedStatement.setLong(2, movieId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Actor> findMovieActors(Long movieId) {
        Set<Actor> actors = new HashSet<>();
        String sql = "select * " +
                "from actor " +
                "where id in (select actor_id " +
                "from actor_movie " +
                "where movie_id=?)";
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actors.add(mapRowToActor(resultSet));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return actors;
    }

    private Actor mapRowToActor(ResultSet resultSet) throws SQLException {
        return Actor.builder()
                .id(resultSet.getLong("id"))
                .actorName(resultSet.getString("actor_name"))
                .build();
    }
}
