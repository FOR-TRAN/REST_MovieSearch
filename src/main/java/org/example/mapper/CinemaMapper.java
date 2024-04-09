package org.example.mapper;
import lombok.experimental.UtilityClass;
import org.example.dto.request.CinemaRequestDTO;
import org.example.dto.response.CinemaResponseDTO;
import org.example.dto.update.CinemaUpdateDTO;
import org.example.entity.Cinema;

/*@UtilityClass представляет собой экспериментальную аннотацию,
 которая позволяет создавать классы, содержащие только статические методы и константы,
  без неявного конструктора или возможности создать экземпляр этого класса.
   Данная аннотация генерирует приватный конструктор, делает все поля финальными и создает
    приватный статический внутренний класс, который содержит только статические методы
 */
@UtilityClass
public class CinemaMapper {


    public Cinema toEntity(CinemaRequestDTO dto) {
        return Cinema.builder()
                .name(dto.getName())
                .city(dto.getCity())
                .build();
    }

    public Cinema toEntity(CinemaUpdateDTO dto) {
        return Cinema.builder()
                .id(dto.getId())
                .name(dto.getName())
                .city(dto.getCity())
                .build();
    }

    public CinemaResponseDTO toDTO(Cinema cinema) {
        return CinemaResponseDTO.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .city(cinema.getCity())
                .build();
    }
}
