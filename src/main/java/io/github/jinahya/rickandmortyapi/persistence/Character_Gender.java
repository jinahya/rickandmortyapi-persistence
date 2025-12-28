package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;

import java.util.Objects;

/**
 * Constants for the {@value Character_#GENDER} attribute.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum Character_Gender implements _StringColumnEnum<Character_Gender> {

    /**
     * A constant for the {@code "Female"} column value.
     */
    FEMALE("Female"),

    /**
     * A constant for the {@code "Genderless"} column value.
     */
    GENDERLESS("Genderless"),

    /**
     * A constant for the {@code "Male"} column value.
     */
    MALE("Male"),

    /**
     * A constant for the {@code "unknown"} column value.
     */
    UNKNOWN("unknown");

    /**
     * Returns the constants whose {@link #columnValue() columnValue} matches the specified {@code columnValue}.
     *
     * @param columnValue the value for the {@link #columnValue() columnValue} to match.
     * @return the constants whose {@link #columnValue()} matches the specified {@code columnValue}.
     */
    public static Character_Gender valueOfColumnValue(final String columnValue) {
        return _StringColumnEnumUtils.valueOfColumnValue(Character_Gender.class, columnValue);
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
