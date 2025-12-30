package io.github.jinahya.rickandmortyapi.persistence;

/*-
 * #%L
 * rickandmortyapi-persistence
 * %%
 * Copyright (C) 2025 GitHub, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.Objects;

/**
 * Constants forthe  {@value Location#COLUMN_NAME_TYPE} column of the {@value Location#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum Location_Type
        implements _StringColumnEnum<Location_Type> {

    /**
     * A constant for the {@code "Acid Plant"} column value.
     */
    ACID_PLANT("Acid Plant"),

    /**
     * A constant for the {@code "Arcade"} column value.
     */
    ARCADE("Arcade"),

    /**
     * A constant for the {@code "Artificially generated world"} column value.
     */
    ARTIFICIALLY_GENERATED_WORLD("Artificially generated world"),

    /**
     * A constant for the {@code "Asteroid"} column value.
     */
    ASTEROID("Asteroid"),

    /**
     * A constant for the {@code "Base"} column value.
     */
    BASE("Base"),

    /**
     * A constant for the {@code "Box"} column value.
     */
    BOX("Box"),

    /**
     * A constant for the {@code "Cluster"} column value.
     */
    CLUSTER("Cluster"),

    /**
     * A constant for the {@code "Consciousness"} column value.
     */
    CONSCIOUSNESS("Consciousness"),

    /**
     * A constant for the {@code "Convention"} column value.
     */
    CONVENTION("Convention"),

    /**
     * A constant for the {@code "Country"} column value.
     */
    COUNTRY("Country"),

    /**
     * A constant for the {@code "Customs"} column value.
     */
    CUSTOMS("Customs"),

    /**
     * A constant for the {@code "Daycare"} column value.
     */
    DAYCARE("Daycare"),

    /**
     * A constant for the {@code "Death Star"} column value.
     */
    DEATH_STAR("Death Star"),

    /**
     * A constant for the {@code "Diegesis"} column value.
     */
    DIEGESIS("Diegesis"),

    /**
     * A constant for the {@code "Dimension"} column value.
     */
    DIMENSION("Dimension"),

    /**
     * A constant for the {@code "Dream"} column value.
     */
    DREAM("Dream"),

    /**
     * A constant for the {@code "Dwarf planet (Celestial Dwarf)"} column value.
     */
    DWARF_PLANET_CELESTIAL_DWARF("Dwarf planet (Celestial Dwarf)"),

    /**
     * A constant for the {@code "Elemental Rings"} column value.
     */
    ELEMENTAL_RINGS("Elemental Rings"),

    /**
     * A constant for the {@code "Fantasy town"} column value.
     */
    FANTASY_TOWN("Fantasy town"),

    /**
     * A constant for the {@code "Game"} column value.
     */
    GAME("Game"),

    /**
     * A constant for the {@code "Hell"} column value.
     */
    HELL("Hell"),

    /**
     * A constant for the {@code "Human"} column value.
     */
    HUMAN("Human"),

    /**
     * A constant for the {@code "Liquid"} column value.
     */
    LIQUID("Liquid"),

    /**
     * A constant for the {@code "Machine"} column value.
     */
    MACHINE("Machine"),

    /**
     * A constant for the {@code "Memory"} column value.
     */
    MEMORY("Memory"),

    /**
     * A constant for the {@code "Menagerie"} column value.
     */
    MENAGERIE("Menagerie"),

    /**
     * A constant for the {@code "Microverse"} column value.
     */
    MICROVERSE("Microverse"),

    /**
     * A constant for the {@code "Miniverse"} column value.
     */
    MINIVERSE("Miniverse"),

    /**
     * A constant for the {@code "Mount"} column value.
     */
    MOUNT("Mount"),

    /**
     * A constant for the {@code "Nightmare"} column value.
     */
    NIGHTMARE("Nightmare"),

    /**
     * A constant for the {@code "Non-Diegetic Alternative Reality"} column value.
     */
    NON_DIEGETIC_ALTERNATIVE_REALITY("Non-Diegetic Alternative Reality"),

    /**
     * A constant for the {@code "Planet"} column value.
     */
    PLANET("Planet"),

    /**
     * A constant for the {@code "Police Department"} column value.
     */
    POLICE_DEPARTMENT("Police Department"),

    /**
     * A constant for the {@code "Quadrant"} column value.
     */
    QUADRANT("Quadrant"),

    /**
     * A constant for the {@code "Quasar"} column value.
     */
    QUASAR("Quasar"),

    /**
     * A constant for the {@code "Reality"} column value.
     */
    REALITY("Reality"),

    /**
     * A constant for the {@code "Resort"} column value.
     */
    RESORT("Resort"),

    /**
     * A constant for the {@code "Spa"} column value.
     */
    SPA("Spa"),

    /**
     * A constant for the {@code "Space"} column value.
     */
    SPACE("Space"),

    /**
     * A constant for the {@code "Space station"} column value.
     */
    SPACE_STATION("Space station"),

    /**
     * A constant for the {@code "Spacecraft"} column value.
     */
    SPACECRAFT("Spacecraft"),

    /**
     * A constant for the {@code "TV"} column value.
     */
    TV("TV"),

    /**
     * A constant for the {@code "Teenyverse"} column value.
     */
    TEENYVERSE("Teenyverse"),

    /**
     * A constant for the {@code "Woods"} column value.
     */
    WOODS("Woods"),

    /**
     * A constant for the {@code "unknown"} column value.
     */
    UNKNOWN("unknown");

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the value whose {@link #columnValue() columnValue} property matches to the specified value.
     *
     * @param columnValue the value for the {@link #columnValue() columnValue} to match.
     * @return the matching value
     */
    public static Location_Type valueOfColumnValue(final String columnValue) {
        return _StringColumnEnumUtils.valueOfColumnValue(Location_Type.class, columnValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    Location_Type(final String columnValue) {
        this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String columnValue() {
        return columnValue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final String columnValue;
}
