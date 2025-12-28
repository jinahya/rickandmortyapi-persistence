package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _ListStringConverter<T> extends _StringConverter<List<T>> implements __ListConverter<T, String> {

    // -----------------------------------------------------------------------------------------------------------------
    protected _ListStringConverter(final AttributeConverter<T, String> converter, final String delimiter) {
        super(a -> a.stream()
                    .filter(Objects::nonNull)
                    .map(converter::convertToDatabaseColumn)
                    .collect(Collectors.joining(delimiter)),
              dd -> Arrays.stream(dd.split(delimiter))
                          .map(converter::convertToEntityAttribute)
                          .toList()
        );
    }
}
