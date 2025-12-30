package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;

import java.util.List;

@SuppressWarnings({
        "java:S114" // Interface names should comply with a naming convention
})
interface __ListConverter<T, Y> extends AttributeConverter<List<T>, Y> {

}
