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
abstract class _StringColumnEnumAttributeConverter<E extends Enum<E> & _StringColumnEnum<E>>
        extends __ColumnEnumAttributeConverter<E, String> {

    _StringColumnEnumAttributeConverter(final Class<E> enumClass) {
        super(enumClass);
    }
}
