package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.URI;
import java.util.Optional;

@Converter
public class UriConverter implements AttributeConverter<URI, String> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    UriConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String convertToDatabaseColumn(final URI attribute) {
        return Optional.ofNullable(attribute)
                       .map(URI::toASCIIString)
                       .orElse(null);
    }

    @Override
    public URI convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                       .map(URI::create)
                       .orElse(null);
    }
}
