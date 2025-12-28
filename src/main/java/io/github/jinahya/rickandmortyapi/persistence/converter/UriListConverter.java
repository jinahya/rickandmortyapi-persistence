package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UriListConverter implements AttributeConverter<List<URI>, String> {

    static final String DELIMITER = ",";

    private static final AttributeConverter<URI, String> CONVERTER = new UriConverter();

    // -----------------------------------------------------------------------------------------------------------------
    public UriListConverter() {
        super();
    }

    @Override
    public String convertToDatabaseColumn(final List<URI> attribute) {
        return Optional.ofNullable(attribute)
                .map(l -> l.stream()
                        .map(CONVERTER::convertToDatabaseColumn)
                        .collect(Collectors.joining(DELIMITER))
                )
                .orElse(null);
    }

    @Override
    public List<URI> convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                .map(dd -> Arrays.stream(dd.split(DELIMITER))
                        .map(CONVERTER::convertToEntityAttribute)
                        .collect(Collectors.toCollection(ArrayList::new))
                )
                .orElse(null);
    }
}
