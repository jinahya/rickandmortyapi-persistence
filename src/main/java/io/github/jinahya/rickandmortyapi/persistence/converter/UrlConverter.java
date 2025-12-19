package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.Converter;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UrlConverter extends _BaseConverter<URL> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    //    protected
    UrlConverter() {
        super(dd -> {
            try {
                return URI.create(dd).toURL();
            } catch (final MalformedURLException murle) {
                throw new RuntimeException("failed to convert " + dd + " into a URL", murle);
            }
        });
    }
}
