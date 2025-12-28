package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;

import java.util.Objects;

/**
 * Constants for {@value Character_#SPECIES} attribute.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum Character_Species implements _StringColumnEnum<Character_Species> {

    /**
     * A constant for the {@code "Alien"} column value.
     */
    ALIEN("Alien"),

    /**
     * A constant for the {@code "Animal"} column value.
     */
    ANIMAL("Animal"),

    /**
     * A constant for the {@code "Cronenberg"} column value.
     */
    CRONENBERG("Cronenberg"),

    /**
     * A constant for the {@code "Disease"} column value.
     */
    DISEASE("Disease"),

    /**
     * A constant for the {@code "Human"} column value.
     */
    HUMAN("Human"),

    /**
     * A constant for the {@code "Humanoid"} column value.
     */
    HUMANOID("Humanoid"),

    /**
     * A constant for the {@code "Mythological Creature"} column value.
     */
    MYTHOLOGICAL_CREATURE("Mythological Creature"),

    /**
     * A constant for the {@code "Poopybutthole"} column value.
     */
    POOPYBUTTHOLE("Poopybutthole"),

    /**
     * A constant for the {@code "Robot"} column value.
     */
    ROBOT("Robot"),

    /**
     * A constant for the {@code "unknown"} column value.
     */
    UNKNOWN("unknown");

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a value whose {@link #columnValue() columnValue} property matches to the specified value.
     *
     * @param columnValue the value for the {@link #columnValue} property to match.
     * @return the value whose {@link #columnValue() columnValue} property matches to the specified value.
     */
    public static Character_Species valueOfColumnValue(final String columnValue) {
        return _StringColumnEnumUtils.valueOfColumnValue(Character_Species.class, columnValue);
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
    @Nonnull
    @Override
    public String columnValue() {
        return columnValue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final String columnValue;
}
