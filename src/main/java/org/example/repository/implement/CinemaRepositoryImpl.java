package org.example.repository.implement;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Cinema;
import org.example.exception.EntityNotFoundException;
import org.example.repository.CinemaRepository;
import org.example.util.HikariCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*Аннотация @Slf4j автоматически генерирует приватное статическое
 поле для логгера в классе на основе SLF4J
 SLF4J - это фасад (обертка) для различных библиотек логирования в Java
 */
@Slf4j
public class CinemaRepositoryImpl implements CinemaRepository {

    @Override
    public Cinema find(Long cinemaId) {
        Cinema cinema;
        String sql = "select * from cinema where id = ?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) { //передаем запрос в БД
            preparedStatement.setLong(1, cinemaId);
            ResultSet resultSet = preparedStatement.executeQuery();
            cinema = mapRowToCinema(resultSet);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return cinema;
    }

    @Override
    public void save(Cinema cinema) {
        String sql = "insert into cinema (name, city) values (?,?)";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cinema.getName());
            preparedStatement.setString(2, cinema.getCity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Cinema cinema) {
        String sql = "update cinema set name=?, city=? where id=?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cinema.getName());
            preparedStatement.setString(2, cinema.getCity());
            preparedStatement.setLong(3, cinema.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long cinemaId) {
        String sql = "delete from cinema where id=?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, cinemaId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Cinema mapRowToCinema(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return Cinema.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .city(resultSet.getString("city"))
                    .build();
        } else {
            log.error("Не найдена запись в базе данных");
            throw new EntityNotFoundException("Не найдена запись в базе данных");
        }
    }
}
