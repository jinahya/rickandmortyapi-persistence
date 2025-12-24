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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    //    protected
    UrlConverter() {
        super();
    }

    @Override
    public String convertToDatabaseColumn(final URL attribute) {
        return Optional.ofNullable(attribute)
                       .map(a -> {
                           try {
                               return converter.convertToDatabaseColumn(a.toURI());
                           } catch (final URISyntaxException urise) {
                               throw new RuntimeException(urise);
                           }
                       })
                       .orElse(null);
    }

    @Override
    public URL convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                       .map(dd -> {
                           try {
                               return converter.convertToEntityAttribute(dd).toURL();
                           } catch (final MalformedURLException murle) {
                               throw new RuntimeException(murle);
                           }
                       })
                       .orElse(null);
    }

    private final AttributeConverter<URI, String> converter = new UriConverter();
}
