package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

@Converter
public class _UrlConverter implements AttributeConverter<URL, String> {

    //    protected
    public
    _UrlConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String convertToDatabaseColumn(final URL attribute) {
        throw new UnsupportedOperationException("not implemented");
    }

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
