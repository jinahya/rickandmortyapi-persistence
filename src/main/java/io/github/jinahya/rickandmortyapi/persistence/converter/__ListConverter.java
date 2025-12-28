package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class __ListConverter<T, Y> implements AttributeConverter<List<T>, Y> {

    // -----------------------------------------------------------------------------------------------------------------
    protected __ListConverter() {
        super();
    }
}
