package io.github.jinahya.rickmortyapi.persistence.converter;

import jakarta.persistence.Converter;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class _UrlConverter extends ___ReadOnlyConverter<URL> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    //    protected
    public _UrlConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public URL convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return URI.create(dbData).toURL();
        } catch (final MalformedURLException murle) {
            throw new RuntimeException("failed to convert " + dbData + " into a URL", murle);
        }
    }
}
