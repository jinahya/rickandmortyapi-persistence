package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UrlListStringConverter implements AttributeConverter<List<URL>, String> {

    private static final AttributeConverter<URL, String> CONVERTER = new UrlStringConverter();

    // -----------------------------------------------------------------------------------------------------------------
    public UrlListStringConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String convertToDatabaseColumn(final List<URL> attribute) {
        return Optional.ofNullable(attribute)
                .map(a -> a.stream()
                        .map(CONVERTER::convertToDatabaseColumn)
                        .collect(Collectors.joining(UriListStringConverter.DELIMITER)))
                .orElse(null);
    }

    @Override
    public List<URL> convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                .map(dd -> Arrays.stream(dd.split(UriListStringConverter.DELIMITER))
                        .map(CONVERTER::convertToEntityAttribute)
                        .collect(Collectors.toCollection(ArrayList::new))
                )
                .orElse(null);
    }
}
