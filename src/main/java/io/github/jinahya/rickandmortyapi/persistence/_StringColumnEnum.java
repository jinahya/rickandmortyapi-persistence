package io.github.jinahya.rickandmortyapi.persistence;

/**
 * An interface for string column enums.
 *
 * @param <E> enum type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S114" // Interface names should comply with a naming convention
})
public interface _StringColumnEnum<E extends Enum<E> & _StringColumnEnum<E>> extends __ColumnEnum<E, String> {

}
