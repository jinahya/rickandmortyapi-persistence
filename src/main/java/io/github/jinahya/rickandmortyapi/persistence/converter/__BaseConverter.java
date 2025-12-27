package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.AttributeConverter;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
abstract class __BaseConverter<X, Y> implements AttributeConverter<X, Y> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    __BaseConverter(final Function<? super X, ? extends Y> formatter,
                    final Function<? super Y, ? extends X> parser) {
        super();
        this.formatter = Objects.requireNonNull(formatter, "formatter is null");
        this.parser = Objects.requireNonNull(parser, "parser is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public final Y convertToDatabaseColumn(final X attribute) {
        return Optional.ofNullable(attribute)
                .map(formatter)
                .orElse(null);
    }

    @Override
    public final X convertToEntityAttribute(final Y dbData) {
        return Optional.ofNullable(dbData)
                .map(parser)
                .orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final Function<? super X, ? extends Y> formatter;

    private final Function<? super Y, ? extends X> parser;
}
