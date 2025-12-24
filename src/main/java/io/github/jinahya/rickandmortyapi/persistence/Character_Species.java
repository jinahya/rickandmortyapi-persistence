package io.github.jinahya.rickandmortyapi.persistence;

import java.util.Objects;

/**
 * Constants for {@value Character_#SPECIES} attribute.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum Character_Species {

    /**
     * A constant for {@value Character_SpeciesColumnValues#ALIEN} column value.
     */
    ALIEN(Character_SpeciesColumnValues.ALIEN),

    /**
     * A constant for {@value Character_SpeciesColumnValues#ANIMAL} column value.
     */
    ANIMAL(Character_SpeciesColumnValues.ANIMAL),

    /**
     * A constant for {@value Character_SpeciesColumnValues#CRONENBERG} column value.
     */
    CRONENBERG(Character_SpeciesColumnValues.CRONENBERG),

    /**
     * A constant for {@value Character_SpeciesColumnValues#DISEASE} column value.
     */
    DISEASE(Character_SpeciesColumnValues.DISEASE),

    /**
     * A constant for {@value Character_SpeciesColumnValues#HUMAN} column value.
     */
    HUMAN(Character_SpeciesColumnValues.HUMAN),

    /**
     * A constant for {@value Character_SpeciesColumnValues#HUMANOID} column value.
     */
    HUMANOID(Character_SpeciesColumnValues.HUMANOID),

    /**
     * A constant for {@value Character_SpeciesColumnValues#MYTHOLOGICAL_CREATURE} column value.
     */
    MYTHOLOGICAL_CREATURE(Character_SpeciesColumnValues.MYTHOLOGICAL_CREATURE),

    /**
     * A constant for {@value Character_SpeciesColumnValues#POOPYBUTTHOLE} column value.
     */
    POOPYBUTTHOLE(Character_SpeciesColumnValues.POOPYBUTTHOLE),

    /**
     * A constant for {@value Character_SpeciesColumnValues#ROBOT} column value.
     */
    ROBOT(Character_SpeciesColumnValues.ROBOT),

    /**
     * A constant for {@value Character_SpeciesColumnValues#UNKNOWN} column value.
     */
    UNKNOWN(Character_SpeciesColumnValues.UNKNOWN);

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a value whose {@link #columnValue() columnValue} property matches to the specified value.
     *
     * @param columnValue the value for the {@link #columnValue} property to match.
     * @return the value whose {@link #columnValue() columnValue} property matches to the specified value.
     */
    public static Character_Species valueOfColumnValue(final String columnValue) {
        for (final var value : values()) {
            if (value.columnValue.equals(columnValue)) {
                return value;
            }
        }
        throw new IllegalArgumentException("no value for column value: " + columnValue);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance with the specified column value.
     *
     * @param columnValue the column value.
     */
    Character_Species(final String columnValue) {
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
