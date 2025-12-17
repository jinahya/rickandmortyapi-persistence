package io.github.jinahya.rickmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
abstract class ___ReadOnlyConverter<X> implements AttributeConverter<X, String> {

    // -----------------------------------------------------------------------------------------------------------------
    //    protected
    ___ReadOnlyConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public final String convertToDatabaseColumn(final X attribute) {
        throw new UnsupportedOperationException("not implemented");
    }
}
