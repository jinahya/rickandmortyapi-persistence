package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UrlStringConverter2 extends _StringConverter<URL> {

    private static final AttributeConverter<URI, String> CONVERTER = new UriStringConverter();

    static String toDatabaseColumn(final URL attribute) {
        assert attribute != null;
        try {
            return CONVERTER.convertToDatabaseColumn(attribute.toURI());
        } catch (final URISyntaxException urise) {
            throw new RuntimeException(urise);
        }
    }

    static URL toEntityAttribute(final String dbData) {
        assert dbData != null;
        assert !dbData.isBlank();
        try {
            return CONVERTER.convertToEntityAttribute(dbData).toURL();
        } catch (final MalformedURLException murle) {
            throw new RuntimeException(murle);
        }
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public UrlStringConverter2() {
        super(UrlStringConverter2::toDatabaseColumn, UrlStringConverter2::toEntityAttribute);
    }
}
