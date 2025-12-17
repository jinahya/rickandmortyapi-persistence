package io.github.jinahya.rickmortyapi.persistence.converter;

import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class _LocalDateConverter extends ___ReadOnlyConverter<LocalDate> {

    // -----------------------------------------------------------------------------------------------------------------
    public _LocalDateConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public LocalDate convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            return null;
        }
        return LocalDate.parse(dbData, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
