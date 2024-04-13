package org.example.ds;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataSerializer <T> {
    private final ObjectMapper objectMapper = new ObjectMapper(); //экземпляр объекта ObjectMapper, который используется для преобразования данных в формат JSON и обратно

    /**
     * Метод преобразует объект типа T в формат JSON с помощью объекта ObjectMapper и возвращает результат.
     *
     * @param dto Объект типа T, который необходимо преобразовать в JSON.
     * @return JSON-строку, представляющую переданный объект типа T.
     * @throws RuntimeException если происходит ошибка при сериализации объекта в JSON
     */
    public String toJson(T dto) {
        try {
            return objectMapper.writeValueAsString(dto); //в обьект класса ObjectMapper записывается DTO и преобразуется в JSON
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
