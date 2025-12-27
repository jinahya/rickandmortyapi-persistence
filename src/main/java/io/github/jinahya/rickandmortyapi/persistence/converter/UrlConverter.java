package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UrlConverter implements AttributeConverter<URL, String> {

    private static final AttributeConverter<URI, String> CONVERTER = new UriConverter();

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public UrlConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String convertToDatabaseColumn(final URL attribute) {
        return Optional.ofNullable(attribute)
                .map(a -> {
                    try {
                        return a.toURI();
                    } catch (final URISyntaxException urise) {
                        throw new RuntimeException(urise);
                    }
                })
                .map(CONVERTER::convertToDatabaseColumn)
                .orElse(null);
    }

    @Override
    public URL convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                .map(dd -> CONVERTER.convertToEntityAttribute(dd))
                .map(dd -> {
                    try {
                        return dd.toURL();
                    } catch (final MalformedURLException murle) {
                        throw new RuntimeException(murle);
                    }
                })
                .orElse(null);
    }
}
