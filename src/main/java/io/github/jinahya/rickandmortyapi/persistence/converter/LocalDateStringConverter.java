package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.Converter;

import java.time.LocalDate;

/**
 * An attribute converter for converting {@link LocalDate} attributes to and from strings.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class LocalDateStringConverter extends _StringConverter<LocalDate> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public LocalDateStringConverter() {
        super(LocalDate::toString, LocalDate::parse);
    }
}
