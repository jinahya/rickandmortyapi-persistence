package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class DateConverter extends _StringConverter<LocalDate> {

    private static final String PATTERN = "MMMM d, uuuu";

    /**
     * A date time formatter for parsing {@value #PATTERN} pattern.
     */
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN, Locale.ENGLISH);

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public DateConverter() {
        super(FORMATTER::format, v -> LocalDate.parse(v, FORMATTER));
    }
}
