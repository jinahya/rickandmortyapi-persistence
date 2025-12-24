package io.github.jinahya.rickandmortyapi.persistence;

import java.util.Objects;

/**
 * Constants for the {@value Location#COLUMN_NAME_DIMENSION} column of the {@value Location#TABLE_NAME} table.
 *
 * @see Location_DimensionColumnValues
 */
public enum Location_Dimension {

    /**
     * A constant for the {@value Location_DimensionColumnValues#CHAIR_DIMENSION} column value.
     */
    CHAIR_DIMENSION(Location_DimensionColumnValues.CHAIR_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#CROMULON_DIMENSION} column value.
     */
    CROMULON_DIMENSION(Location_DimensionColumnValues.CROMULON_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#CRONENBERG_DIMENSION} column value.
     */
    CRONENBERG_DIMENSION(Location_DimensionColumnValues.CRONENBERG_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#DIMENSION_5_126} column value.
     */
    DIMENSION_5_126(Location_DimensionColumnValues.DIMENSION_5_126),

    /**
     * A constant for the {@value Location_DimensionColumnValues#DIMENSION_C_137} column value.
     */
    DIMENSION_C_137(Location_DimensionColumnValues.DIMENSION_C_137),

    /**
     * A constant for the {@value Location_DimensionColumnValues#DIMENSION_C_35} column value.
     */
    DIMENSION_C_35(Location_DimensionColumnValues.DIMENSION_C_35),

    /**
     * A constant for the {@value Location_DimensionColumnValues#DIMENSION_C_500A} column value.
     */
    DIMENSION_C_500A(Location_DimensionColumnValues.DIMENSION_C_500A),

    /**
     * A constant for the {@value Location_DimensionColumnValues#DIMENSION_D_99} column value.
     */
    DIMENSION_D_99(Location_DimensionColumnValues.DIMENSION_D_99),

    /**
     * A constant for the {@value Location_DimensionColumnValues#DIMENSION_D716} column value.
     */
    DIMENSION_D716(Location_DimensionColumnValues.DIMENSION_D716),

    /**
     * A constant for the {@value Location_DimensionColumnValues#DIMENSION_D716_B} column value.
     */
    DIMENSION_D716_B(Location_DimensionColumnValues.DIMENSION_D716_B),

    /**
     * A constant for the {@value Location_DimensionColumnValues#DIMENSION_D716_C} column value.
     */
    DIMENSION_D716_C(Location_DimensionColumnValues.DIMENSION_D716_C),

    /**
     * A constant for the {@value Location_DimensionColumnValues#DIMENSION_J_22} column value.
     */
    DIMENSION_J_22(Location_DimensionColumnValues.DIMENSION_J_22),

    /**
     * A constant for the {@value Location_DimensionColumnValues#DIMENSION_J19_ZETA_7} column value.
     */
    DIMENSION_J19_7(Location_DimensionColumnValues.DIMENSION_J19_ZETA_7),

    /**
     * A constant for the {@value Location_DimensionColumnValues#DIMENSION_K_22} column value.
     */
    DIMENSION_K_22(Location_DimensionColumnValues.DIMENSION_K_22),

    /**
     * A constant for the {@value Location_DimensionColumnValues#DIMENSION_K_83} column value.
     */
    DIMENSION_K_83(Location_DimensionColumnValues.DIMENSION_K_83),

    /**
     * A constant for the {@value Location_DimensionColumnValues#ERIC_STOLTZ_MASK_DIMENSION} column value.
     */
    ERIC_STOLTZ_MASK_DIMENSION(Location_DimensionColumnValues.ERIC_STOLTZ_MASK_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#EVIL_RICKS_TARGET_DIMENSION} column value.
     */
    EVIL_RICKS_TARGET_DIMENSION(Location_DimensionColumnValues.EVIL_RICKS_TARGET_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#FANTASY_DIMENSION} column value.
     */
    FANTASY_DIMENSION(Location_DimensionColumnValues.FANTASY_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#FASCIST_DIMENSION} column value.
     */
    FASCIST_DIMENSION(Location_DimensionColumnValues.FASCIST_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#FASCIST_SHRIMP_DIMENSION} column value.
     */
    FASCIST_SHRIMP_DIMENSION(Location_DimensionColumnValues.FASCIST_SHRIMP_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#FASCIST_TEDDY_BEAR_DIMENSION} column value.
     */
    FASCIST_TEDDY_BEAR_DIMENSION(Location_DimensionColumnValues.FASCIST_TEDDY_BEAR_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#GIANT_TELEPATHIC_SPIDERS_DIMENSION} column value.
     */
    GIANT_TELEPATHIC_SPIDERS_DIMENSION(
            Location_DimensionColumnValues.GIANT_TELEPATHIC_SPIDERS_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#MAGIC_DIMENSION} column value.
     */
    MAGIC_DIMENSION(Location_DimensionColumnValues.MAGIC_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#MERGED_DIMENSION} column value.
     */
    MERGED_DIMENSION(Location_DimensionColumnValues.MERGED_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#PHONE_DIMENSION} column value.
     */
    PHONE_DIMENSION(Location_DimensionColumnValues.PHONE_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#PIZZA_DIMENSION} column value.
     */
    PIZZA_DIMENSION(Location_DimensionColumnValues.PIZZA_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#POST_APOCALYPTIC_DIMENSION} column value.
     */
    POST_APOCALYPTIC_DIMENSION(Location_DimensionColumnValues.POST_APOCALYPTIC_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#REPLACEMENT_DIMENSION} column value.
     */
    REPLACEMENT_DIMENSION(Location_DimensionColumnValues.REPLACEMENT_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#TESTICLE_MONSTER_DIMENSION} column value.
     */
    TESTICLE_MONSTER_DIMENSION(Location_DimensionColumnValues.TESTICLE_MONSTER_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#TUSK_DIMENSION} column value.
     */
    TUSK_DIMENSION(Location_DimensionColumnValues.TUSK_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#UNKNOWN_DIMENSION} column value.
     */
    UNKNOWN_DIMENSION(Location_DimensionColumnValues.UNKNOWN_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#WASP_DIMENSION} column value.
     */
    WASP_DIMENSION(Location_DimensionColumnValues.WASP_DIMENSION),

    /**
     * A constant for the {@value Location_DimensionColumnValues#UNKNOWN} column value.
     */
    UNKNOWN(Location_DimensionColumnValues.UNKNOWN);

    // -----------------------------------------------------------------------------------------------------------------
    public static Location_Dimension valueOfColumnValue(final String columnValue) {
        for (final Location_Dimension value : values()) {
            if (value.columnValue().equals(columnValue)) {
                return value;
            }
        }
        throw new IllegalArgumentException("no value for column value: " + columnValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    Location_Dimension(final String columnValue) {
        this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String columnValue() {
        return columnValue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final String columnValue;
}
