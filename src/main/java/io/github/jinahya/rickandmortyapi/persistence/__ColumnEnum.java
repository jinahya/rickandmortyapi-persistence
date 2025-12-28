package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;

/**
 * An interface for column enums.
 *
 * @param <E> enum type parameter
 * @param <T> column type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S114" // Interface names should comply with a naming convention
})
interface __ColumnEnum<E extends Enum<E> & __ColumnEnum<E, T>, T> {

    /**
     * Returns the column value of this constant.
     *
     * @return the column value of this constant.
     */
    @Nonnull
    @NotNull
    T columnValue();
}
