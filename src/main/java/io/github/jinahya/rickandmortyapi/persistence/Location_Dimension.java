package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;

import java.util.Objects;

/**
 * Constants for the {@value Location#COLUMN_NAME_DIMENSION} column of the {@value Location#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum Location_Dimension implements _StringColumnEnum<Location_Dimension> {

    /**
     * A constant for the {@code "Chair Dimension"} column value.
     */
    CHAIR_DIMENSION("Chair Dimension"),

    /**
     * A constant for the {@code "Cromulon Dimension"} column value.
     */
    CROMULON_DIMENSION("Cromulon Dimension"),

    /**
     * A constant for the {@code "Cronenberg Dimension"} column value.
     */
    CRONENBERG_DIMENSION("Cronenberg Dimension"),

    /**
     * A constant for the {@code "Dimension 5-126"} column value.
     */
    DIMENSION_5_126("Dimension 5-126"),

    /**
     * A constant for the {@code "Dimension C-137"} column value.
     */
    DIMENSION_C_137("Dimension C-137"),

    /**
     * A constant for the {@code "Dimension C-35"} column value.
     */
    DIMENSION_C_35("Dimension C-35"),

    /**
     * A constant for the {@code "Dimension C-500A"} column value.
     */
    DIMENSION_C_500A("Dimension C-500A"),

    /**
     * A constant for the {@code "Dimension D-99"} column value.
     */
    DIMENSION_D_99("Dimension D-99"),

    /**
     * A constant for the {@code "Dimension D716"} column value.
     */
    DIMENSION_D716("Dimension D716"),

    /**
     * A constant for the {@code "Dimension D716-B"} column value.
     */
    DIMENSION_D716_B("Dimension D716-B"),

    /**
     * A constant for the {@code "Dimension D716-C"} column value.
     */
    DIMENSION_D716_C("Dimension D716-C"),

    /**
     * A constant for the {@code "Dimension J-22"} column value.
     */
    DIMENSION_J_22("Dimension J-22"),

    /**
     * A constant for the {@code "Dimension J19ζ7"} column value.
     */
    DIMENSION_J19_7("Dimension J19ζ7"),

    /**
     * A constant for the {@code "Dimension K-22"} column value.
     */
    DIMENSION_K_22("Dimension K-22"),

    /**
     * A constant for the {@code "Dimension K-83"} column value.
     */
    DIMENSION_K_83("Dimension K-83"),

    /**
     * A constant for the {@code "Eric Stoltz Mask Dimension"} column value.
     */
    ERIC_STOLTZ_MASK_DIMENSION("Eric Stoltz Mask Dimension"),

    /**
     * A constant for the {@code "Evil Rick's Target Dimension"} column value.
     */
    EVIL_RICKS_TARGET_DIMENSION("Evil Rick's Target Dimension"),

    /**
     * A constant for the {@code "Fantasy Dimension"} column value.
     */
    FANTASY_DIMENSION("Fantasy Dimension"),

    /**
     * A constant for the {@code "Fascist Dimension"} column value.
     */
    FASCIST_DIMENSION("Fascist Dimension"),

    /**
     * A constant for the {@code "Fascist Shrimp Dimension"} column value.
     */
    FASCIST_SHRIMP_DIMENSION("Fascist Shrimp Dimension"),

    /**
     * A constant for the {@code "Fascist Teddy Bear Dimension"} column value.
     */
    FASCIST_TEDDY_BEAR_DIMENSION("Fascist Teddy Bear Dimension"),

    /**
     * A constant for the {@code "Giant Telepathic Spiders Dimension"} column value.
     */
    GIANT_TELEPATHIC_SPIDERS_DIMENSION("Giant Telepathic Spiders Dimension"),

    /**
     * A constant for the {@code "Magic Dimension"} column value.
     */
    MAGIC_DIMENSION("Magic Dimension"),

    /**
     * A constant for the {@code "Merged Dimension"} column value.
     */
    MERGED_DIMENSION("Merged Dimension"),

    /**
     * A constant for the {@code "Phone Dimension"} column value.
     */
    PHONE_DIMENSION("Phone Dimension"),

    /**
     * A constant for the {@code "Pizza Dimension"} column value.
     */
    PIZZA_DIMENSION("Pizza Dimension"),

    /**
     * A constant for the {@code "Post-Apocalyptic Dimension"} column value.
     */
    POST_APOCALYPTIC_DIMENSION("Post-Apocalyptic Dimension"),

    /**
     * A constant for the {@code "Replacement Dimension"} column value.
     */
    REPLACEMENT_DIMENSION("Replacement Dimension"),

    /**
     * A constant for the {@code "Testicle Monster Dimension"} column value.
     */
    TESTICLE_MONSTER_DIMENSION("Testicle Monster Dimension"),

    /**
     * A constant for the {@code "Tusk Dimension"} column value.
     */
    TUSK_DIMENSION("Tusk Dimension"),

    /**
     * A constant for the {@code "Unknown dimension"} column value.
     */
    UNKNOWN_DIMENSION("Unknown dimension"),

    /**
     * A constant for the {@code "Wasp Dimension"} column value.
     */
    WASP_DIMENSION("Wasp Dimension"),

    /**
     * A constant for the {@code "unknown"} column value.
     */
    UNKNOWN("unknown");

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a value whose {@link #columnValue() columnValue} property matches to the specified value.
     *
     * @param columnValue the value for the {@link #columnValue() columnValue} to match.
     * @return the matching value
     */
    @Nonnull
    public static Location_Dimension valueOfColumnValue(final String columnValue) {
        return _StringColumnEnumUtils.valueOfColumnValue(Location_Dimension.class, columnValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    Location_Dimension(final String columnValue) {
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
