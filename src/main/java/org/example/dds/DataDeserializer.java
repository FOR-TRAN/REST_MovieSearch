package org.example.dds;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
@NoArgsConstructor
public class DataDeserializer <T> {

    /*Здесь создается экземпляр класса ObjectMapper, который применяется для преобразования данных
     в формат JSON и обратно. ObjectMapper из библиотеки Jackson используется для сериализации
      и десериализации объектов Java в JSON и обратно
     */

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * метод fromJson, который принимает JSON-строку и класс,
     * в который должен быть преобразован JSON. Метод возвращает объект типа T,
     * который будет результирующим объектом после десериализации JSON
     */
    public T fromJson(String json, Class<T> cls) {
        try { //попытка выполнить операцию десериализации JSON в объект типа T
            return objectMapper.readValue(json, cls); //используем объект objectMapper для преобразования JSON-строки json в объект типа T, указанный в переменной cls
        } catch (JsonProcessingException e) { //перехватывает исключение JsonProcessingException, возникающее при проблемах с обработкой JSON, таких как некорректный формат JSON или другие ошибки парсинга
            throw new RuntimeException(e); // В случае исключения JsonProcessingException, исключение оборачивается в новый объект RuntimeException и выбрасывается дальше. Это гарантирует, что любые проблемы с обработкой JSON будут обнаружены и обработаны на уровне, где вызван метод fromJson
        }
    }
}
