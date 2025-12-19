package io.github.jinahya.rickmortyapi.persistence.converter;

import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class LocalDateConverter extends _BaseConverter<LocalDate> {

    // -----------------------------------------------------------------------------------------------------------------
    LocalDateConverter() {
        super(dd -> LocalDate.parse(dd, DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
