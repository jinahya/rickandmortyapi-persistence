package io.github.jinahya.rickandmortyapi.persistence;

import io.github.jinahya.rickandmortyapi.persistence.converter._StringConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * An attribute converter for converting {@value Episode_#AIR_DATE} attribute to
 * {@value Episode_AirDateConverter#PATTERN} string.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Episode_AirDateConverter extends _StringConverter<LocalDate> {

    private static final String PATTERN = "MMMM d, uuuu";

    /**
     * A date time formatter for parsing {@value #PATTERN} pattern.
     */
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN, Locale.ENGLISH);

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public Episode_AirDateConverter() {
        super(FORMATTER::format, v -> LocalDate.parse(v, FORMATTER));
    }
}
