package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class _DateConverter extends __ReadOnlyConverter<LocalDate> {

    private static final String DATE_TIME_FORMAT_AIR_DATE = "MMMM d, uuuu";

    /**
     * A date time formatter for parsing {@value #DATE_TIME_FORMAT_AIR_DATE} pattern.
     */
    public static final DateTimeFormatter DATE_TIME_PATTERN_AIR_DATE =
            DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_AIR_DATE);

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Creates a new instance.
     */
    public _DateConverter() {
        super();
    }

    // -------------------------------------------------------------------------- jakarta.persistence.AttributeConverter
    @Override
    public LocalDate convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            return null;
        }
        return LocalDate.parse(dbData, DATE_TIME_PATTERN_AIR_DATE);
    }
}
