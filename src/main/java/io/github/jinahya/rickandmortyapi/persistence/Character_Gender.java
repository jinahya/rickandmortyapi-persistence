package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;

import java.util.Objects;

/**
 * Constants for the {@value Character_#GENDER} attribute.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Character_GenderColumnValues
 */
public enum Character_Gender implements _StringColumnEnum<Character_Gender> {

    /**
     * A constant for the {@value Character_GenderColumnValues#FEMALE} column value.
     */
    FEMALE(Character_GenderColumnValues.FEMALE),

    /**
     * A constant for the {@value Character_GenderColumnValues#GENDERLESS} column value.
     */
    GENDERLESS(Character_GenderColumnValues.GENDERLESS),

    /**
     * A constant for the {@value Character_GenderColumnValues#MALE} column value.
     */
    MALE(Character_GenderColumnValues.MALE),

    /**
     * A constant for the {@value Character_GenderColumnValues#UNKNOWN} column value.
     */
    UNKNOWN(Character_GenderColumnValues.UNKNOWN);

    /**
     * Returns the constants whose {@link #columnValue() columnValue} matches the specified {@code columnValue}.
     *
     * @param columnValue the value for the {@link #columnValue() columnValue} to match.
     * @return the constants whose {@link #columnValue()} matches the specified {@code columnValue}.
     */
    public static Character_Gender valueOfColumnValue(final String columnValue) {
        for (final var value : values()) {
            if (value.columnValue.equals(columnValue)) {
                return value;
            }
        }
        throw new IllegalArgumentException("no value for column value: " + columnValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    Character_Gender(final String columnValue) {
        this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    public String columnValue() {
        return columnValue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final String columnValue;
}
