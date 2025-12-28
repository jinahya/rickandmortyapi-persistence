package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;

import java.util.Objects;

/**
 * Constants for {@value Character_#STATUS} attribute.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum Character_Status implements _StringColumnEnum<Character_Status> {

    /**
     * A value for {@value Character_StatusColumnValues#ALIVE} column value.
     */
    ALIVE(Character_StatusColumnValues.ALIVE),

    /**
     * A value for {@value Character_StatusColumnValues#DEAD} column value.
     */
    DEAD(Character_StatusColumnValues.DEAD),

    /**
     * A value for {@value Character_StatusColumnValues#UNKNOWN} column value.
     */
    UNKNOWN(Character_StatusColumnValues.UNKNOWN);

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the value whose {@link #columnValue() columnValue} property matches to the specified value.
     *
     * @param columnValue the value for the {@link #columnValue() columnValue} to match.
     * @return the value whose {@link #columnValue() columnValue} property matches to the specified value.
     */
    public static Character_Status valueOfColumnValue(final String columnValue) {
        return _StringColumnEnumUtils.valueOfColumnValue(Character_Status.class, columnValue);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Character_Status(final String columnValue) {
        this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the column value of this constant.
     *
     * @return the column value of this constant.
     */
    @Nonnull
    public String columnValue() {
        return columnValue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final String columnValue;
}
