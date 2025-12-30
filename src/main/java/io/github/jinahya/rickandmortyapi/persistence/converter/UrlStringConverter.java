package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

/**
 * An attribute converter for converting {@link URL} attributes to and from strings.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see UriListStringConverter
 * @see UrlStringConverter
 */
@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UrlStringConverter implements AttributeConverter<URL, String> {

    private static final AttributeConverter<URI, String> CONVERTER = new UriStringConverter();

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public UrlStringConverter() {
        super();
    }

    // -------------------------------------------------------------------------- jakarta.persistence.AttributeConverter
    @Override
    public String convertToDatabaseColumn(final URL attribute) {
        return Optional.ofNullable(attribute)
                .map(a -> {
                    try {
                        return a.toURI();
                    } catch (final URISyntaxException urise) {
                        throw new RuntimeException("failed to convert " + attribute + " to URI", urise);
                    }
                })
                .map(CONVERTER::convertToDatabaseColumn)
                .orElse(null);
    }

    @Override
    public URL convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                .map(CONVERTER::convertToEntityAttribute)
                .map(dd -> {
                    try {
                        return dd.toURL();
                    } catch (final MalformedURLException murle) {
                        throw new RuntimeException("failed to convert " + dbData + " to URL", murle);
                    }
                })
                .orElse(null);
    }
}
