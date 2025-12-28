package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.AttributeConverter;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
abstract class __ColumnEnumAttributeConverter<E extends Enum<E> & __ColumnEnum<E, T>, T>
        implements AttributeConverter<E, T> {

    // -----------------------------------------------------------------------------------------------------------------
    __ColumnEnumAttributeConverter(final Class<E> enumClass) {
        super();
        this.enumClass = Objects.requireNonNull(enumClass, "enumClass is null");
    }

    // -------------------------------------------------------------------------- jakarta.persistence.AttributeConverter
    @Override
    public final T convertToDatabaseColumn(final E attribute) {
        return Optional.ofNullable(attribute)
                       .map(__ColumnEnum::columnValue)
                       .orElse(null);
    }

    @Override
    public final E convertToEntityAttribute(final T dbData) {
        return Optional.ofNullable(dbData)
                       .map(v -> __ColumnEnumUtils.valueOfColumnValue(enumClass, v))
                       .orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<E> enumClass;
}
