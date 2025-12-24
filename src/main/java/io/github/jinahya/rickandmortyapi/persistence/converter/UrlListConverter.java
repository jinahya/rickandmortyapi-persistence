package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UrlListConverter extends _BaseConverter<List<URL>> {

    private static final AttributeConverter<URL, String> CONVERTER = new UrlConverter();

    // -----------------------------------------------------------------------------------------------------------------
    UrlListConverter() {
        super(dd -> {
            return Arrays.stream(dd.split(","))
                         .map(String::strip)
                         .filter(v -> !v.isBlank())
                         .map(CONVERTER::convertToEntityAttribute)
                         .toList();
        });
    }
}
