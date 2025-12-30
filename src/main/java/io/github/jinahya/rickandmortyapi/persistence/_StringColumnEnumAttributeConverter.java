package io.github.jinahya.rickandmortyapi.persistence;

/**
 * An abstract attribute converter for {@link _StringColumnEnum}s.
 *
 * @param <E> enum type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _StringColumnEnumAttributeConverter<E extends Enum<E> & _StringColumnEnum<E>>
        extends __ColumnEnumAttributeConverter<E, String> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance for converting the specified enum class.
     *
     * @param enumClass the enum class.
     * @see #enumClass
     */
    protected _StringColumnEnumAttributeConverter(final Class<E> enumClass) {
        super(enumClass);
    }
}
