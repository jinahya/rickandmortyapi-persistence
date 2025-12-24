package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UriListConverter implements AttributeConverter<List<URI>, String> {

    static final String DELIMITER = ",";

    static String join(final Stream<String> split) {
        Objects.requireNonNull(split, "split is null");
        return split.collect(Collectors.joining(DELIMITER));
    }

    static Stream<String> split(final String dbData) {
        Objects.requireNonNull(dbData, "dbData is null");
        return Arrays.stream(dbData.split(DELIMITER));
    }

    // -----------------------------------------------------------------------------------------------------------------
    UriListConverter() {
        super();
    }

    @Override
    public String convertToDatabaseColumn(final List<URI> attribute) {
        return Optional.ofNullable(attribute)
                       .map(l -> l.stream()
                                  .map(converter::convertToDatabaseColumn)
                                  .collect(Collectors.joining(DELIMITER))
                       )
                       .orElse(null);
    }

    @Override
    public List<URI> convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                       .map(dd -> Arrays.stream(dd.split(DELIMITER))
                                        .map(converter::convertToEntityAttribute)
                                        .collect(Collectors.toCollection(ArrayList::new))
                       )
                       .orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final AttributeConverter<URI, String> converter = new UriConverter();
}
