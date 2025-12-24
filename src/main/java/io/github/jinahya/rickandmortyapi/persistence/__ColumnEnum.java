package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings({
        "java:S114" // Interface names should comply with a naming convention
})
public interface __ColumnEnum<E extends Enum<E> & __ColumnEnum<E, T>, T> {

    /**
     * Returns the column value of this constant.
     *
     * @return the column value of this constant.
     */
    @Nonnull
    @NotNull
    T columnValue();
}
