package io.github.jinahya.rickandmortyapi.persistence;

import java.util.Objects;

/**
 * Constants for {@value Character_#STATUS} attribute.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum Character_Status {

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
        for (final Character_Status value : values()) {
            if (value.columnValue.equals(columnValue)) {
                return value;
            }
        }
        throw new IllegalArgumentException("no value for column value: " + columnValue);
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
    public String columnValue() {
        return columnValue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final String columnValue;
}
