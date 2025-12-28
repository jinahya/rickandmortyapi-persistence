package io.github.jinahya.rickandmortyapi.persistence;

/**
 * Values for {@value Character#COLUMN_NAME_SPECIES} column.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
final class Character_SpeciesColumnValues {

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_SPECIES} column.
     */
    static final String ALIEN = "Alien";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_SPECIES} column.
     */
    static final String ANIMAL = "Animal";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_SPECIES} column.
     */
    static final String CRONENBERG = "Cronenberg";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_SPECIES} column.
     */
    static final String DISEASE = "Disease";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_SPECIES} column.
     */
    static final String HUMAN = "Human";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_SPECIES} column.
     */
    static final String HUMANOID = "Humanoid";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_SPECIES} column.
     */
    static final String MYTHOLOGICAL_CREATURE = "Mythological Creature";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_SPECIES} column.
     */
    static final String POOPYBUTTHOLE = "Poopybutthole";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_SPECIES} column.
     */
    static final String ROBOT = "Robot";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_SPECIES} column.
     */
    static final String UNKNOWN = "unknown";

    // -----------------------------------------------------------------------------------------------------------------
    private Character_SpeciesColumnValues() {
        throw new AssertionError("instantiation is not allowed");
    }
}
