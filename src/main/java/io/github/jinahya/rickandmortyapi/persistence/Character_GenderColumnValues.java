package io.github.jinahya.rickandmortyapi.persistence;

/**
 * Value for the {@value  Character#COLUMN_NAME_GENDER} column.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
final class Character_GenderColumnValues implements _ColumnValues {

    /**
     * A column value of {@value} for the {@value  Character#COLUMN_NAME_GENDER} column.
     */
    static final String FEMALE = "Female";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_GENDER} column.
     */
    static final String GENDERLESS = "Genderless";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_GENDER} column.
     */
    static final String MALE = "Male";

    /**
     * A column value of {@value} for the {@value Character#COLUMN_NAME_GENDER} column.
     */
    static final String UNKNOWN = "unknown";

    // -----------------------------------------------------------------------------------------------------------------
    private Character_GenderColumnValues() {
        throw new AssertionError("instantiation is not allowed");
    }
}
