package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
@SuppressWarnings({
        "java:S114" // Interface names should comply with a naming convention
})
interface __ListConverter<T, Y> extends AttributeConverter<List<T>, Y> {

}
