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

    private static final AttributeConverter<URI, String> C = new UriConverter();

    static String toDatabaseColumn(final URL attribute) {
        assert attribute != null;
        try {
            return C.convertToDatabaseColumn(attribute.toURI());
        } catch (final URISyntaxException urise) {
            throw new RuntimeException(urise);
        }
    }

    static URL toEntityAttribute(final String dbData) {
        assert dbData != null;
        assert !dbData.isBlank();
        try {
            return C.convertToEntityAttribute(dbData).toURL();
        } catch (final MalformedURLException murle) {
            throw new RuntimeException(murle);
        }
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    //    protected
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
                .map(converter::convertToDatabaseColumn)
                .orElse(null);
    }

    @Override
    public URL convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                .map(dd -> converter.convertToEntityAttribute(dd))
                .map(dd -> {
                    try {
                        return dd.toURL();
                    } catch (final MalformedURLException murle) {
                        throw new RuntimeException(murle);
                    }
                })
                .orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final AttributeConverter<URI, String> converter = new UriConverter();
}
