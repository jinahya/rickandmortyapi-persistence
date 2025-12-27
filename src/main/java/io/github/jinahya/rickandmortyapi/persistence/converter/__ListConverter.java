package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;
import java.util.Objects;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class __ListConverter<T, Y> implements AttributeConverter<List<T>, Y> {

    // -----------------------------------------------------------------------------------------------------------------
    public __ListConverter(final AttributeConverter<T, Y> converter) {
        super();
        this.converter = Objects.requireNonNull(converter, "converter is null");
    }

    private final AttributeConverter<T, Y> converter;
}
