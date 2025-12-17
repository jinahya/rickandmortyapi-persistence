package io.github.jinahya.rickmortyapi.persistence.converter;

import java.time.temporal.TemporalAccessor;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
abstract class __DateTimeConverter<TEMPORAL extends TemporalAccessor> extends ___ReadOnlyConverter<TEMPORAL> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    __DateTimeConverter(final Function<? super String, ? extends TEMPORAL> converter) {
        super();
        this.converter = Objects.requireNonNull(converter, "converter is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public final TEMPORAL convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                .map(converter)
                .orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final Function<? super String, ? extends TEMPORAL> converter;
}
