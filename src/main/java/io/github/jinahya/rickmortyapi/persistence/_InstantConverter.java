package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Instant;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class _InstantConverter implements AttributeConverter<Instant, String> {

    //    protected
    public _InstantConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String convertToDatabaseColumn(final Instant attribute) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Instant convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            return null;
        }
        return Instant.parse(dbData);
    }
}
