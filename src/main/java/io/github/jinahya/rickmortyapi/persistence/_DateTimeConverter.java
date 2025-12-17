package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.Converter;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * .
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see java.time.format.DateTimeFormatter#ISO_INSTANT
 */
@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
abstract class _DateTimeConverter extends __ReadOnlyConverter<Instant> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    _DateTimeConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Instant convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            return null;
        }
        return Instant.parse(dbData);
    }

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_INSTANT;
}
