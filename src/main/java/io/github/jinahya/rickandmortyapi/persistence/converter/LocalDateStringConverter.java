package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.Converter;

import java.time.LocalDate;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class LocalDateStringConverter extends _StringConverter<LocalDate> {

    // -----------------------------------------------------------------------------------------------------------------
    public LocalDateStringConverter() {
        super(LocalDate::toString, LocalDate::parse);
    }
}
