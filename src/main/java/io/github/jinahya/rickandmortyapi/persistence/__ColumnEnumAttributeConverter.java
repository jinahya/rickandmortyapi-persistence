package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.AttributeConverter;

import java.util.Objects;
import java.util.Optional;

/**
 * An abstract attribute converter for {@link __ColumnEnum}s.
 *
 * @param <E> enum type parameter
 * @param <T> column type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class __ColumnEnumAttributeConverter<E extends Enum<E> & __ColumnEnum<E, T>, T>
        implements AttributeConverter<E, T> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance for converting the specified enum class.
     *
     * @param enumClass the enum class.
     * @see #enumClass
     */
    protected __ColumnEnumAttributeConverter(final Class<E> enumClass) {
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

    /**
     * The enum class to convert.
     */
    protected final Class<E> enumClass;
}
