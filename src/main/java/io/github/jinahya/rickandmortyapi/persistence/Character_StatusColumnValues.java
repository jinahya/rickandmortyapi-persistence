package io.github.jinahya.rickandmortyapi.persistence;

/**
 * Values for {@value Character#COLUMN_NAME_STATUS} column.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class Character_StatusColumnValues {

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_STATUS} column.
     */
    public static final String ALIVE = "Alive";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_STATUS} column.
     */
    public static final String DEAD = "Dead";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_STATUS} column.
     */
    public static final String UNKNOWN = "unknown";

    // -----------------------------------------------------------------------------------------------------------------
    private Character_StatusColumnValues() {
        throw new AssertionError("instantiation is not allowed");
    }
}
