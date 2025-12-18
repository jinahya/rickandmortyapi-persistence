package io.github.jinahya.rickmortyapi.persistence.converter;

import jakarta.persistence.Converter;

import java.time.Instant;

/**
 * .
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Instant#parse(CharSequence)
 * @see java.time.format.DateTimeFormatter#ISO_INSTANT
 */
@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class InstantConverter extends _BaseConverter<Instant> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public InstantConverter() {
        super(Instant::parse);
    }
}
