package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.Converter;

import java.net.URI;

/**
 * An attribute converter for {@link URI} attributes to and from strings.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Converter
public class UriStringConverter extends _StringConverter<URI> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public UriStringConverter() {
        super(URI::toASCIIString, URI::create);
    }
}
