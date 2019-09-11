package dashboard.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.util.Date;

public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        Date date = new Date(source);
        return null;
    }
}
