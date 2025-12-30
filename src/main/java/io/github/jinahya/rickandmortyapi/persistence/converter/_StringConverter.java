package io.github.jinahya.rickandmortyapi.persistence.converter;

import java.util.Optional;
import java.util.function.Function;

/**
 * An attribute converter for converting an attribute of a specific types to and from strings.
 *
 * @param <X> attribute type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _StringConverter<X> extends __BaseConverter<X, String> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance with the specified formatter and parser.
     *
     * @param formatter the formatter for formatting attributes to strings
     * @param parser    the parser for parsing strings to attributes
     */
    protected _StringConverter(final Function<? super X, String> formatter,
                               final Function<? super String, ? extends X> parser) {
        super(formatter,
              dd -> Optional.of(dd)
                      .map(String::strip)
                      .filter(v -> !v.isBlank())
                      .map(parser)
                      .orElse(null));
    }
}
