package io.github.jinahya.rickandmortyapi.persistence;

import java.util.Objects;

/**
 * Constants forthe  {@value Location#COLUMN_NAME_TYPE} column of the {@value Location#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Location_TypeColumnValues
 */
public enum Location_Type {

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_ACID_PLANT} column value.
     */
    ACID_PLANT(Location_TypeColumnValues.COLUMN_VALUE_TYPE_ACID_PLANT),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_ARCADE} column value.
     */
    ARCADE(Location_TypeColumnValues.COLUMN_VALUE_TYPE_ARCADE),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_ARTIFICIALLY_GENERATED_WORLD} column
     * value.
     */
    ARTIFICIALLY_GENERATED_WORLD(Location_TypeColumnValues.COLUMN_VALUE_TYPE_ARTIFICIALLY_GENERATED_WORLD),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_ASTEROID} column value.
     */
    ASTEROID(Location_TypeColumnValues.COLUMN_VALUE_TYPE_ASTEROID),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_BASE} column value.
     */
    BASE(Location_TypeColumnValues.COLUMN_VALUE_TYPE_BASE),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_BOX} column value.
     */
    BOX(Location_TypeColumnValues.COLUMN_VALUE_TYPE_BOX),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_CLUSTER} column value.
     */
    CLUSTER(Location_TypeColumnValues.COLUMN_VALUE_TYPE_CLUSTER),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_CONSCIOUSNESS} column value.
     */
    CONSCIOUSNESS(Location_TypeColumnValues.COLUMN_VALUE_TYPE_CONSCIOUSNESS),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_CONVENTION} column value.
     */
    CONVENTION(Location_TypeColumnValues.COLUMN_VALUE_TYPE_CONVENTION),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_COUNTRY} column value.
     */
    COUNTRY(Location_TypeColumnValues.COLUMN_VALUE_TYPE_COUNTRY),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_CUSTOMS} column value.
     */
    CUSTOMS(Location_TypeColumnValues.COLUMN_VALUE_TYPE_CUSTOMS),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_DAYCARE} column value.
     */
    DAYCARE(Location_TypeColumnValues.COLUMN_VALUE_TYPE_DAYCARE),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_DEATH_STAR} column value.
     */
    DEATH_STAR(Location_TypeColumnValues.COLUMN_VALUE_TYPE_DEATH_STAR),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_DIEGESIS} column value.
     */
    DIEGESIS(Location_TypeColumnValues.COLUMN_VALUE_TYPE_DIEGESIS),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_DIMENSION} column value.
     */
    DIMENSION(Location_TypeColumnValues.COLUMN_VALUE_TYPE_DIMENSION),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_DREAM} column value.
     */
    DREAM(Location_TypeColumnValues.COLUMN_VALUE_TYPE_DREAM),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_DWARF_PLANET_CELESTIAL_DWARF} column
     * value.
     */
    DWARF_PLANET_CELESTIAL_DWARF(Location_TypeColumnValues.COLUMN_VALUE_TYPE_DWARF_PLANET_CELESTIAL_DWARF),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_ELEMENTAL_RINGS} column value.
     */
    ELEMENTAL_RINGS(Location_TypeColumnValues.COLUMN_VALUE_TYPE_ELEMENTAL_RINGS),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_FANTASY_TOWN} column value.
     */
    FANTASY_TOWN(Location_TypeColumnValues.COLUMN_VALUE_TYPE_FANTASY_TOWN),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_GAME} column value.
     */
    GAME(Location_TypeColumnValues.COLUMN_VALUE_TYPE_GAME),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_HELL} column value.
     */
    HELL(Location_TypeColumnValues.COLUMN_VALUE_TYPE_HELL),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_HUMAN} column value.
     */
    HUMAN(Location_TypeColumnValues.COLUMN_VALUE_TYPE_HUMAN),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_LIQUID} column value.
     */
    LIQUID(Location_TypeColumnValues.COLUMN_VALUE_TYPE_LIQUID),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_MACHINE} column value.
     */
    MACHINE(Location_TypeColumnValues.COLUMN_VALUE_TYPE_MACHINE),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_MEMORY} column value.
     */
    MEMORY(Location_TypeColumnValues.COLUMN_VALUE_TYPE_MEMORY),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_MENAGERIE} column value.
     */
    MENAGERIE(Location_TypeColumnValues.COLUMN_VALUE_TYPE_MENAGERIE),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_MICROVERSE} column value.
     */
    MICROVERSE(Location_TypeColumnValues.COLUMN_VALUE_TYPE_MICROVERSE),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_MINIVERSE} column value.
     */
    MINIVERSE(Location_TypeColumnValues.COLUMN_VALUE_TYPE_MINIVERSE),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_MOUNT} column value.
     */
    MOUNT(Location_TypeColumnValues.COLUMN_VALUE_TYPE_MOUNT),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_NIGHTMARE} column value.
     */
    NIGHTMARE(Location_TypeColumnValues.COLUMN_VALUE_TYPE_NIGHTMARE),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_NON_DIEGETIC_ALTERNATIVE_REALITY} column
     * value.
     */
    NON_DIEGETIC_ALTERNATIVE_REALITY(Location_TypeColumnValues.COLUMN_VALUE_TYPE_NON_DIEGETIC_ALTERNATIVE_REALITY),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_PLANET} column value.
     */
    PLANET(Location_TypeColumnValues.COLUMN_VALUE_TYPE_PLANET),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_POLICE_DEPARTMENT} column value.
     */
    POLICE_DEPARTMENT(Location_TypeColumnValues.COLUMN_VALUE_TYPE_POLICE_DEPARTMENT),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_QUADRANT} column value.
     */
    QUADRANT(Location_TypeColumnValues.COLUMN_VALUE_TYPE_QUADRANT),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_QUASAR} column value.
     */
    QUASAR(Location_TypeColumnValues.COLUMN_VALUE_TYPE_QUASAR),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_REALITY} column value.
     */
    REALITY(Location_TypeColumnValues.COLUMN_VALUE_TYPE_REALITY),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_RESORT} column value.
     */
    RESORT(Location_TypeColumnValues.COLUMN_VALUE_TYPE_RESORT),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_SPA} column value.
     */
    SPA(Location_TypeColumnValues.COLUMN_VALUE_TYPE_SPA),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_SPACE} column value.
     */
    SPACE(Location_TypeColumnValues.COLUMN_VALUE_TYPE_SPACE),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_SPACE_STATION} column value.
     */
    SPACE_STATION(Location_TypeColumnValues.COLUMN_VALUE_TYPE_SPACE_STATION),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_SPACECRAFT} column value.
     */
    SPACECRAFT(Location_TypeColumnValues.COLUMN_VALUE_TYPE_SPACECRAFT),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_TV} column value.
     */
    TV(Location_TypeColumnValues.COLUMN_VALUE_TYPE_TV),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_TEENYVERSE} column value.
     */
    TEENYVERSE(Location_TypeColumnValues.COLUMN_VALUE_TYPE_TEENYVERSE),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_WOODS} column value.
     */
    WOODS(Location_TypeColumnValues.COLUMN_VALUE_TYPE_WOODS),

    /**
     * A constant for the {@value Location_TypeColumnValues#COLUMN_VALUE_TYPE_UNKNOWN} column value.
     */
    UNKNOWN(Location_TypeColumnValues.COLUMN_VALUE_TYPE_UNKNOWN);

    public static Location_Type valueOfColumnValue(final String columnValue) {
        for (final Location_Type type : values()) {
            if (type.columnValue().equals(columnValue)) {
                return type;
            }
        }
        throw new IllegalArgumentException("no value for column value: " + columnValue);
    }

    Location_Type(final String columnValue) {
        this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
    }

    public String columnValue() {
        return columnValue;
    }

    private final String columnValue;
}
