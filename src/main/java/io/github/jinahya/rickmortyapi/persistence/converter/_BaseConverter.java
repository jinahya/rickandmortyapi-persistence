package io.github.jinahya.rickmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
abstract class _BaseConverter<X> implements AttributeConverter<X, String> {

    // -----------------------------------------------------------------------------------------------------------------
    _BaseConverter(final Function<? super String, ? extends X> converter) {
        super();
        this.converter = Objects.requireNonNull(converter, "converter is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public final String convertToDatabaseColumn(final X attribute) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public final X convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                .map(converter)
                .orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final Function<? super String, ? extends X> converter;
}
