package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {

        /*
        Bu sınıf, JSON dönüşümü yaparken oluşabilecek exceptionları handle eder
         ve dönüşüm işlemlerini test metotlarınız için daha temiz hale getirir.
         Aşağıda, hem bir Map'e hem de bir Pojoya dönüşüm yapabilen jenerik bir
         yardımcı metot (utility method) ve bu metod oluşturacağız.
         */

    public static <T> T convertJsonStrToJava(String JsonStr, Class<T> classType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(JsonStr, classType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
