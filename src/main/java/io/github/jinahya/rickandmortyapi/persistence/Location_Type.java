package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;

import java.util.Objects;

/**
 * Constants forthe  {@value Location#COLUMN_NAME_TYPE} column of the {@value Location#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Location_TypeColumnValues
 */
public enum Location_Type implements _StringColumnEnum<Location_Type> {

    /**
     * A constant for the {@value Location_TypeColumnValues#ACID_PLANT} column value.
     */
    ACID_PLANT(Location_TypeColumnValues.ACID_PLANT),

    /**
     * A constant for the {@value Location_TypeColumnValues#ARCADE} column value.
     */
    ARCADE(Location_TypeColumnValues.ARCADE),

    /**
     * A constant for the {@value Location_TypeColumnValues#ARTIFICIALLY_GENERATED_WORLD} column value.
     */
    ARTIFICIALLY_GENERATED_WORLD(Location_TypeColumnValues.ARTIFICIALLY_GENERATED_WORLD),

    /**
     * A constant for the {@value Location_TypeColumnValues#ASTEROID} column value.
     */
    ASTEROID(Location_TypeColumnValues.ASTEROID),

    /**
     * A constant for the {@value Location_TypeColumnValues#BASE} column value.
     */
    BASE(Location_TypeColumnValues.BASE),

    /**
     * A constant for the {@value Location_TypeColumnValues#BOX} column value.
     */
    BOX(Location_TypeColumnValues.BOX),

    /**
     * A constant for the {@value Location_TypeColumnValues#CLUSTER} column value.
     */
    CLUSTER(Location_TypeColumnValues.CLUSTER),

    /**
     * A constant for the {@value Location_TypeColumnValues#CONSCIOUSNESS} column value.
     */
    CONSCIOUSNESS(Location_TypeColumnValues.CONSCIOUSNESS),

    /**
     * A constant for the {@value Location_TypeColumnValues#CONVENTION} column value.
     */
    CONVENTION(Location_TypeColumnValues.CONVENTION),

    /**
     * A constant for the {@value Location_TypeColumnValues#COUNTRY} column value.
     */
    COUNTRY(Location_TypeColumnValues.COUNTRY),

    /**
     * A constant for the {@value Location_TypeColumnValues#CUSTOMS} column value.
     */
    CUSTOMS(Location_TypeColumnValues.CUSTOMS),

    /**
     * A constant for the {@value Location_TypeColumnValues#DAYCARE} column value.
     */
    DAYCARE(Location_TypeColumnValues.DAYCARE),

    /**
     * A constant for the {@value Location_TypeColumnValues#DEATH_STAR} column value.
     */
    DEATH_STAR(Location_TypeColumnValues.DEATH_STAR),

    /**
     * A constant for the {@value Location_TypeColumnValues#DIEGESIS} column value.
     */
    DIEGESIS(Location_TypeColumnValues.DIEGESIS),

    /**
     * A constant for the {@value Location_TypeColumnValues#DIMENSION} column value.
     */
    DIMENSION(Location_TypeColumnValues.DIMENSION),

    /**
     * A constant for the {@value Location_TypeColumnValues#DREAM} column value.
     */
    DREAM(Location_TypeColumnValues.DREAM),

    /**
     * A constant for the {@value Location_TypeColumnValues#DWARF_PLANET_CELESTIAL_DWARF} column value.
     */
    DWARF_PLANET_CELESTIAL_DWARF(Location_TypeColumnValues.DWARF_PLANET_CELESTIAL_DWARF),

    /**
     * A constant for the {@value Location_TypeColumnValues#ELEMENTAL_RINGS} column value.
     */
    ELEMENTAL_RINGS(Location_TypeColumnValues.ELEMENTAL_RINGS),

    /**
     * A constant for the {@value Location_TypeColumnValues#FANTASY_TOWN} column value.
     */
    FANTASY_TOWN(Location_TypeColumnValues.FANTASY_TOWN),

    /**
     * A constant for the {@value Location_TypeColumnValues#GAME} column value.
     */
    GAME(Location_TypeColumnValues.GAME),

    /**
     * A constant for the {@value Location_TypeColumnValues#HELL} column value.
     */
    HELL(Location_TypeColumnValues.HELL),

    /**
     * A constant for the {@value Location_TypeColumnValues#HUMAN} column value.
     */
    HUMAN(Location_TypeColumnValues.HUMAN),

    /**
     * A constant for the {@value Location_TypeColumnValues#LIQUID} column value.
     */
    LIQUID(Location_TypeColumnValues.LIQUID),

    /**
     * A constant for the {@value Location_TypeColumnValues#MACHINE} column value.
     */
    MACHINE(Location_TypeColumnValues.MACHINE),

    /**
     * A constant for the {@value Location_TypeColumnValues#MEMORY} column value.
     */
    MEMORY(Location_TypeColumnValues.MEMORY),

    /**
     * A constant for the {@value Location_TypeColumnValues#MENAGERIE} column value.
     */
    MENAGERIE(Location_TypeColumnValues.MENAGERIE),

    /**
     * A constant for the {@value Location_TypeColumnValues#MICROVERSE} column value.
     */
    MICROVERSE(Location_TypeColumnValues.MICROVERSE),

    /**
     * A constant for the {@value Location_TypeColumnValues#MINIVERSE} column value.
     */
    MINIVERSE(Location_TypeColumnValues.MINIVERSE),

    /**
     * A constant for the {@value Location_TypeColumnValues#MOUNT} column value.
     */
    MOUNT(Location_TypeColumnValues.MOUNT),

    /**
     * A constant for the {@value Location_TypeColumnValues#NIGHTMARE} column value.
     */
    NIGHTMARE(Location_TypeColumnValues.NIGHTMARE),

    /**
     * A constant for the {@value Location_TypeColumnValues#NON_DIEGETIC_ALTERNATIVE_REALITY} column value.
     */
    NON_DIEGETIC_ALTERNATIVE_REALITY(Location_TypeColumnValues.NON_DIEGETIC_ALTERNATIVE_REALITY),

    /**
     * A constant for the {@value Location_TypeColumnValues#PLANET} column value.
     */
    PLANET(Location_TypeColumnValues.PLANET),

    /**
     * A constant for the {@value Location_TypeColumnValues#POLICE_DEPARTMENT} column value.
     */
    POLICE_DEPARTMENT(Location_TypeColumnValues.POLICE_DEPARTMENT),

    /**
     * A constant for the {@value Location_TypeColumnValues#QUADRANT} column value.
     */
    QUADRANT(Location_TypeColumnValues.QUADRANT),

    /**
     * A constant for the {@value Location_TypeColumnValues#QUASAR} column value.
     */
    QUASAR(Location_TypeColumnValues.QUASAR),

    /**
     * A constant for the {@value Location_TypeColumnValues#REALITY} column value.
     */
    REALITY(Location_TypeColumnValues.REALITY),

    /**
     * A constant for the {@value Location_TypeColumnValues#RESORT} column value.
     */
    RESORT(Location_TypeColumnValues.RESORT),

    /**
     * A constant for the {@value Location_TypeColumnValues#SPA} column value.
     */
    SPA(Location_TypeColumnValues.SPA),

    /**
     * A constant for the {@value Location_TypeColumnValues#SPACE} column value.
     */
    SPACE(Location_TypeColumnValues.SPACE),

    /**
     * A constant for the {@value Location_TypeColumnValues#SPACE_STATION} column value.
     */
    SPACE_STATION(Location_TypeColumnValues.SPACE_STATION),

    /**
     * A constant for the {@value Location_TypeColumnValues#SPACECRAFT} column value.
     */
    SPACECRAFT(Location_TypeColumnValues.SPACECRAFT),

    /**
     * A constant for the {@value Location_TypeColumnValues#TV} column value.
     */
    TV(Location_TypeColumnValues.TV),

    /**
     * A constant for the {@value Location_TypeColumnValues#TEENYVERSE} column value.
     */
    TEENYVERSE(Location_TypeColumnValues.TEENYVERSE),

    /**
     * A constant for the {@value Location_TypeColumnValues#WOODS} column value.
     */
    WOODS(Location_TypeColumnValues.WOODS),

    /**
     * A constant for the {@value Location_TypeColumnValues#UNKNOWN} column value.
     */
    UNKNOWN(Location_TypeColumnValues.UNKNOWN);

    // -----------------------------------------------------------------------------------------------------------------
    public static Location_Type valueOfColumnValue(final String columnValue) {
        return _StringColumnEnumUtils.valueOfColumnValue(Location_Type.class, columnValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    Location_Type(final String columnValue) {
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
