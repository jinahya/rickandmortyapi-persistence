package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.Converter;

import java.time.LocalDate;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class LocalDateConverter extends _StringConverter<LocalDate> {

    // -----------------------------------------------------------------------------------------------------------------
    public LocalDateConverter() {
        super(LocalDate::toString, LocalDate::parse);
    }
}
