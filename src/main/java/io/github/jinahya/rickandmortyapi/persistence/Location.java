package io.github.jinahya.rickandmortyapi.persistence;

import io.github.jinahya.rickandmortyapi.persistence.converter.InstantConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlListConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping {@value Location#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = Location.TABLE_NAME)
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S116", // Field names should comply with a naming convention
        "java:S117"  // Local variable and method parameter names should comply with a naming convention
})
public class Location extends _BaseEntity {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "location";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Location_#ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ID = "id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAME = "name";

    // ------------------------------------------------------------------------------------------------------------ type
    public static final String COLUMN_NAME_TYPE = "type";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_ACID_PLANT = "Acid Plant";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_ARCADE = "Arcade";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_ARTIFICIALLY_GENERATED_WORLD = "Artificially generated world";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_ASTEROID = "Asteroid";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_BASE = "Base";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_BOX = "Box";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_CLUSTER = "Cluster";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_CONSCIOUSNESS = "Consciousness";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_CONVENTION = "Convention";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_COUNTRY = "Country";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_CUSTOMS = "Customs";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_DAYCARE = "Daycare";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_DEATH_STAR = "Death Star";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_DIEGESIS = "Diegesis";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_DIMENSION = "Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_DREAM = "Dream";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_DWARF_PLANET_CELESTIAL_DWARF = "Dwarf planet (Celestial Dwarf)";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_ELEMENTAL_RINGS = "Elemental Rings";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_FANTASY_TOWN = "Fantasy town";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_GAME = "Game";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_HELL = "Hell";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_HUMAN = "Human";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_LIQUID = "Liquid";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_MACHINE = "Machine";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_MEMORY = "Memory";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_MENAGERIE = "Menagerie";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_MICROVERSE = "Microverse";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_MINIVERSE = "Miniverse";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_MOUNT = "Mount";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_NIGHTMARE = "Nightmare";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_NON_DIEGETIC_ALTERNATIVE_REALITY = "Non-Diegetic Alternative Reality";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_PLANET = "Planet";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_POLICE_DEPARTMENT = "Police Department";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_QUADRANT = "Quadrant";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_QUASAR = "Quasar";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_REALITY = "Reality";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_RESORT = "Resort";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_SPA = "Spa";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_SPACE = "Space";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_SPACE_STATION = "Space station";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_SPACECRAFT = "Spacecraft";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_TV = "TV";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_TEENYVERSE = "Teenyverse";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_WOODS = "Woods";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    public static final String COLUMN_VALUE_TYPE_UNKNOWN = "unknown";

    /**
     * Constants for {@value #COLUMN_NAME_TYPE} column.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public enum Type {

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_ACID_PLANT} column value.
         */
        ACID_PLANT(COLUMN_VALUE_TYPE_ACID_PLANT),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_ARCADE} column value.
         */
        ARCADE(COLUMN_VALUE_TYPE_ARCADE),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_ARTIFICIALLY_GENERATED_WORLD} column value.
         */
        ARTIFICIALLY_GENERATED_WORLD(COLUMN_VALUE_TYPE_ARTIFICIALLY_GENERATED_WORLD),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_ASTEROID} column value.
         */
        ASTEROID(COLUMN_VALUE_TYPE_ASTEROID),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_BASE} column value.
         */
        BASE(COLUMN_VALUE_TYPE_BASE),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_BOX} column value.
         */
        BOX(COLUMN_VALUE_TYPE_BOX),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_CLUSTER} column value.
         */
        CLUSTER(COLUMN_VALUE_TYPE_CLUSTER),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_CONSCIOUSNESS} column value.
         */
        CONSCIOUSNESS(COLUMN_VALUE_TYPE_CONSCIOUSNESS),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_CONVENTION} column value.
         */
        CONVENTION(COLUMN_VALUE_TYPE_CONVENTION),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_COUNTRY} column value.
         */
        COUNTRY(COLUMN_VALUE_TYPE_COUNTRY),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_CUSTOMS} column value.
         */
        CUSTOMS(COLUMN_VALUE_TYPE_CUSTOMS),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_DAYCARE} column value.
         */
        DAYCARE(COLUMN_VALUE_TYPE_DAYCARE),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_DEATH_STAR} column value.
         */
        DEATH_STAR(COLUMN_VALUE_TYPE_DEATH_STAR),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_DIEGESIS} column value.
         */
        DIEGESIS(COLUMN_VALUE_TYPE_DIEGESIS),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_DIMENSION} column value.
         */
        DIMENSION(COLUMN_VALUE_TYPE_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_DREAM} column value.
         */
        DREAM(COLUMN_VALUE_TYPE_DREAM),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_DWARF_PLANET_CELESTIAL_DWARF} column value.
         */
        DWARF_PLANET_CELESTIAL_DWARF(COLUMN_VALUE_TYPE_DWARF_PLANET_CELESTIAL_DWARF),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_ELEMENTAL_RINGS} column value.
         */
        ELEMENTAL_RINGS(COLUMN_VALUE_TYPE_ELEMENTAL_RINGS),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_FANTASY_TOWN} column value.
         */
        FANTASY_TOWN(COLUMN_VALUE_TYPE_FANTASY_TOWN),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_GAME} column value.
         */
        GAME(COLUMN_VALUE_TYPE_GAME),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_HELL} column value.
         */
        HELL(COLUMN_VALUE_TYPE_HELL),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_HUMAN} column value.
         */
        HUMAN(COLUMN_VALUE_TYPE_HUMAN),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_LIQUID} column value.
         */
        LIQUID(COLUMN_VALUE_TYPE_LIQUID),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_MACHINE} column value.
         */
        MACHINE(COLUMN_VALUE_TYPE_MACHINE),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_MEMORY} column value.
         */
        MEMORY(COLUMN_VALUE_TYPE_MEMORY),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_MENAGERIE} column value.
         */
        MENAGERIE(COLUMN_VALUE_TYPE_MENAGERIE),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_MICROVERSE} column value.
         */
        MICROVERSE(COLUMN_VALUE_TYPE_MICROVERSE),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_MINIVERSE} column value.
         */
        MINIVERSE(COLUMN_VALUE_TYPE_MINIVERSE),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_MOUNT} column value.
         */
        MOUNT(COLUMN_VALUE_TYPE_MOUNT),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_NIGHTMARE} column value.
         */
        NIGHTMARE(COLUMN_VALUE_TYPE_NIGHTMARE),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_NON_DIEGETIC_ALTERNATIVE_REALITY} column value.
         */
        NON_DIEGETIC_ALTERNATIVE_REALITY(COLUMN_VALUE_TYPE_NON_DIEGETIC_ALTERNATIVE_REALITY),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_PLANET} column value.
         */
        PLANET(COLUMN_VALUE_TYPE_PLANET),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_POLICE_DEPARTMENT} column value.
         */
        POLICE_DEPARTMENT(COLUMN_VALUE_TYPE_POLICE_DEPARTMENT),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_QUADRANT} column value.
         */
        QUADRANT(COLUMN_VALUE_TYPE_QUADRANT),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_QUASAR} column value.
         */
        QUASAR(COLUMN_VALUE_TYPE_QUASAR),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_REALITY} column value.
         */
        REALITY(COLUMN_VALUE_TYPE_REALITY),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_RESORT} column value.
         */
        RESORT(COLUMN_VALUE_TYPE_RESORT),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_SPA} column value.
         */
        SPA(COLUMN_VALUE_TYPE_SPA),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_SPACE} column value.
         */
        SPACE(COLUMN_VALUE_TYPE_SPACE),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_SPACE_STATION} column value.
         */
        SPACE_STATION(COLUMN_VALUE_TYPE_SPACE_STATION),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_SPACECRAFT} column value.
         */
        SPACECRAFT(COLUMN_VALUE_TYPE_SPACECRAFT),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_TV} column value.
         */
        TV(COLUMN_VALUE_TYPE_TV),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_TEENYVERSE} column value.
         */
        TEENYVERSE(COLUMN_VALUE_TYPE_TEENYVERSE),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_WOODS} column value.
         */
        WOODS(COLUMN_VALUE_TYPE_WOODS),

        /**
         * A constant for the {@value #COLUMN_VALUE_TYPE_UNKNOWN} column value.
         */
        UNKNOWN(COLUMN_VALUE_TYPE_UNKNOWN);

        public static Type valueOfColumnValue(final String columnValue) {
            for (final Type type : values()) {
                if (type.columnValue().equals(columnValue)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("no value for column value: " + columnValue);
        }

        Type(final String columnValue) {
            this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
        }

        public String columnValue() {
            return columnValue;
        }

        private final String columnValue;
    }

    @Converter(autoApply = true)
    public static class TypeConverter implements AttributeConverter<Type, String> {

        TypeConverter() {
            super();
        }

        @Override
        public String convertToDatabaseColumn(final Type attribute) {
            return Optional.ofNullable(attribute)
                    .map(Type::columnValue)
                    .orElse(null);
        }

        @Override
        public Type convertToEntityAttribute(final String dbData) {
            return Optional.ofNullable(dbData)
                    .map(Type::valueOfColumnValue)
                    .orElse(null);
        }
    }

    // ------------------------------------------------------------------------------------------------------- dimension

    /**
     * The name of the table column to which the {@link Location_#NAME} attributes maps.
     */
    public static final String COLUMN_NAME_DIMENSION = "dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_CHAIR_DIMENSION = "Chair Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_CROMULON_DIMENSION = "Cromulon Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_CRONENBERG_DIMENSION = "Cronenberg Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_DIMENSION_5_126 = "Dimension 5-126";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_DIMENSION_C_137 = "Dimension C-137";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_DIMENSION_C_35 = "Dimension C-35";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_DIMENSION_C_500A = "Dimension C-500A";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_DIMENSION_D_99 = "Dimension D-99";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_DIMENSION_D716 = "Dimension D716";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_DIMENSION_D716_B = "Dimension D716-B";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_DIMENSION_D716_C = "Dimension D716-C";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_DIMENSION_J_22 = "Dimension J-22";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_DIMENSION_J19_ZETA_7 = "Dimension J19ζ7";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_DIMENSION_K_22 = "Dimension K-22";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_DIMENSION_K_83 = "Dimension K-83";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_ERIC_STOLTZ_MASK_DIMENSION = "Eric Stoltz Mask Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_EVIL_RICKS_TARGET_DIMENSION = "Evil Rick's Target Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_FANTASY_DIMENSION = "Fantasy Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_FASCIST_DIMENSION = "Fascist Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_FASCIST_SHRIMP_DIMENSION = "Fascist Shrimp Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_FASCIST_TEDDY_BEAR_DIMENSION = "Fascist Teddy Bear Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_GIANT_TELEPATHIC_SPIDERS_DIMENSION =
            "Giant Telepathic Spiders Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_MAGIC_DIMENSION = "Magic Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_MERGED_DIMENSION = "Merged Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_PHONE_DIMENSION = "Phone Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_PIZZA_DIMENSION = "Pizza Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_POST_APOCALYPTIC_DIMENSION = "Post-Apocalyptic Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_REPLACEMENT_DIMENSION = "Replacement Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_TESTICLE_MONSTER_DIMENSION = "Testicle Monster Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_TUSK_DIMENSION = "Tusk Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_UNKNOWN_DIMENSION = "Unknown dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_WASP_DIMENSION = "Wasp Dimension";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public static final String COLUMN_VALUE_DIMENSION_UNKNOWN = "unknown";

    /**
     * Constants for the {@value #COLUMN_NAME_DIMENSION} column.
     */
    public enum Dimension {

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_CHAIR_DIMENSION} column value.
         */
        CHAIR_DIMENSION(COLUMN_VALUE_DIMENSION_CHAIR_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_CROMULON_DIMENSION} column value.
         */
        CROMULON_DIMENSION(COLUMN_VALUE_DIMENSION_CROMULON_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_CRONENBERG_DIMENSION} column value.
         */
        CRONENBERG_DIMENSION(COLUMN_VALUE_DIMENSION_CRONENBERG_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_DIMENSION_5_126} column value.
         */
        DIMENSION_5_126(COLUMN_VALUE_DIMENSION_DIMENSION_5_126),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_DIMENSION_C_137} column value.
         */
        DIMENSION_C_137(COLUMN_VALUE_DIMENSION_DIMENSION_C_137),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_DIMENSION_C_35} column value.
         */
        DIMENSION_C_35(COLUMN_VALUE_DIMENSION_DIMENSION_C_35),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_DIMENSION_C_500A} column value.
         */
        DIMENSION_C_500A(COLUMN_VALUE_DIMENSION_DIMENSION_C_500A),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_DIMENSION_D_99} column value.
         */
        DIMENSION_D_99(COLUMN_VALUE_DIMENSION_DIMENSION_D_99),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_DIMENSION_D716} column value.
         */
        DIMENSION_D716(COLUMN_VALUE_DIMENSION_DIMENSION_D716),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_DIMENSION_D716_B} column value.
         */
        DIMENSION_D716_B(COLUMN_VALUE_DIMENSION_DIMENSION_D716_B),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_DIMENSION_D716_C} column value.
         */
        DIMENSION_D716_C(COLUMN_VALUE_DIMENSION_DIMENSION_D716_C),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_DIMENSION_J_22} column value.
         */
        DIMENSION_J_22(COLUMN_VALUE_DIMENSION_DIMENSION_J_22),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_DIMENSION_J19_ZETA_7} column value.
         */
        DIMENSION_J19_7(COLUMN_VALUE_DIMENSION_DIMENSION_J19_ZETA_7),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_DIMENSION_K_22} column value.
         */
        DIMENSION_K_22(COLUMN_VALUE_DIMENSION_DIMENSION_K_22),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_DIMENSION_K_83} column value.
         */
        DIMENSION_K_83(COLUMN_VALUE_DIMENSION_DIMENSION_K_83),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_ERIC_STOLTZ_MASK_DIMENSION} column value.
         */
        ERIC_STOLTZ_MASK_DIMENSION(COLUMN_VALUE_DIMENSION_ERIC_STOLTZ_MASK_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_EVIL_RICKS_TARGET_DIMENSION} column value.
         */
        EVIL_RICKS_TARGET_DIMENSION(COLUMN_VALUE_DIMENSION_EVIL_RICKS_TARGET_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_FANTASY_DIMENSION} column value.
         */
        FANTASY_DIMENSION(COLUMN_VALUE_DIMENSION_FANTASY_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_FASCIST_DIMENSION} column value.
         */
        FASCIST_DIMENSION(COLUMN_VALUE_DIMENSION_FASCIST_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_FASCIST_SHRIMP_DIMENSION} column value.
         */
        FASCIST_SHRIMP_DIMENSION(COLUMN_VALUE_DIMENSION_FASCIST_SHRIMP_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_FASCIST_TEDDY_BEAR_DIMENSION} column value.
         */
        FASCIST_TEDDY_BEAR_DIMENSION(COLUMN_VALUE_DIMENSION_FASCIST_TEDDY_BEAR_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_GIANT_TELEPATHIC_SPIDERS_DIMENSION} column value.
         */
        GIANT_TELEPATHIC_SPIDERS_DIMENSION(COLUMN_VALUE_DIMENSION_GIANT_TELEPATHIC_SPIDERS_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_MAGIC_DIMENSION} column value.
         */
        MAGIC_DIMENSION(COLUMN_VALUE_DIMENSION_MAGIC_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_MERGED_DIMENSION} column value.
         */
        MERGED_DIMENSION(COLUMN_VALUE_DIMENSION_MERGED_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_PHONE_DIMENSION} column value.
         */
        PHONE_DIMENSION(COLUMN_VALUE_DIMENSION_PHONE_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_PIZZA_DIMENSION} column value.
         */
        PIZZA_DIMENSION(COLUMN_VALUE_DIMENSION_PIZZA_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_POST_APOCALYPTIC_DIMENSION} column value.
         */
        POST_APOCALYPTIC_DIMENSION(COLUMN_VALUE_DIMENSION_POST_APOCALYPTIC_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_REPLACEMENT_DIMENSION} column value.
         */
        REPLACEMENT_DIMENSION(COLUMN_VALUE_DIMENSION_REPLACEMENT_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_TESTICLE_MONSTER_DIMENSION} column value.
         */
        TESTICLE_MONSTER_DIMENSION(COLUMN_VALUE_DIMENSION_TESTICLE_MONSTER_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_TUSK_DIMENSION} column value.
         */
        TUSK_DIMENSION(COLUMN_VALUE_DIMENSION_TUSK_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_UNKNOWN_DIMENSION} column value.
         */
        UNKNOWN_DIMENSION(COLUMN_VALUE_DIMENSION_UNKNOWN_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_WASP_DIMENSION} column value.
         */
        WASP_DIMENSION(COLUMN_VALUE_DIMENSION_WASP_DIMENSION),

        /**
         * A constant for the {@value #COLUMN_VALUE_DIMENSION_UNKNOWN} column value.
         */
        UNKNOWN(COLUMN_VALUE_DIMENSION_UNKNOWN);

        public static Dimension valueOfColumnValue(final String columnValue) {
            for (final Dimension value : values()) {
                if (value.columnValue().equals(columnValue)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("no value for column value: " + columnValue);
        }

        Dimension(final String columnValue) {
            this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
        }

        public String columnValue() {
            return columnValue;
        }

        private final String columnValue;
    }

    @Converter(autoApply = true)
    public static class DimensionConverter implements AttributeConverter<Dimension, String> {

        DimensionConverter() {
            super();
        }

        @Override
        public String convertToDatabaseColumn(final Dimension attribute) {
            return Optional.ofNullable(attribute)
                    .map(Dimension::columnValue)
                    .orElse(null);
        }

        @Override
        public Dimension convertToEntityAttribute(final String dbData) {
            return Optional.ofNullable(dbData)
                    .map(Dimension::valueOfColumnValue)
                    .orElse(null);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_RESIDENTS = "residents";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_URL = "url";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CREATED = "created";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Location() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Obejct
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
               ",name=" + name +
               ",type=" + type +
               ",dimension=" + dimension +
               ",residents=" + residents +
               ",url=" + url +
               ",created=" + created +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Location that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // -------------------------------------------------------------------------------------------------------------- id
    public Integer getId() {
        return id;
    }

    void setId(final Integer id) {
        this.id = id;
    }

    Location id(final Integer id) {
        setId(id);
        return this;
    }

    // ------------------------------------------------------------------------------------------------------------ name
    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    // ------------------------------------------------------------------------------------------------------------ type
    @Nullable
    public Type getType() {
        return type;
    }

    void setType(@Nullable final Type type) {
        this.type = type;
    }

    // ------------------------------------------------------------------------------------------------------- dimension
    @Nullable
    public Dimension getDimension() {
        return dimension;
    }

    void setDimension(@Nullable final Dimension dimension) {
        this.dimension = dimension;
    }

    // ------------------------------------------------------------------------------------------------------- residents
    @Nullable
    public List<URL> getResidents() {
        return residents;
    }

    void setResidents(@Nullable final List<URL> residents) {
        this.residents = residents;
    }

    // ------------------------------------------------------------------------------------------------------------- url
    public URL getUrl() {
        return url;
    }

    public void setUrl(final URL url) {
        this.url = url;
    }

    // --------------------------------------------------------------------------------------------------------- created
    public Instant getCreated() {
        return created;
    }

    void setCreated(final Instant created) {
        this.created = created;
    }

    // ------------------------------------------------------------------------------------------------------ residents_
    public List<Character> getResidents_() {
        return residents_;
    }

    void setResidents_(final List<Character> residents_) {
        this.residents_ = residents_;
    }

    // ----------------------------------------------------------------------------------------------- originCharacters_
    public List<Character> getOriginCharacters_() {
        return originCharacters_;
    }

    void setOriginCharacters_(final List<Character> originCharacters_) {
        this.originCharacters_ = originCharacters_;
    }

    // --------------------------------------------------------------------------------------------- locationCharacters_
    public List<Character> getLocationCharacters_() {
        return locationCharacters_;
    }

    void setLocationCharacters_(final List<Character> locationCharacters_) {
        this.locationCharacters_ = locationCharacters_;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Id
    @Column(name = COLUMN_NAME_ID, nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false
    )
    private Integer id;

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private String name;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TYPE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Type type;

    @Nullable
//    @Convert(converter = DimensionConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DIMENSION,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Dimension dimension;

    @Nullable
    @Convert(converter = UrlListConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RESIDENTS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private List<URL> residents;

    // -----------------------------------------------------------------------------------------------------------------
    @Convert(converter = UrlConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_URL,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private URL url;

    @Past
    @NotNull
    @Convert(converter = InstantConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CREATED,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Instant created;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    @JoinTable(name = LocationResident.TABLE_NAME,
               joinColumns = @JoinColumn(
                       name = LocationResident.COLUMN_NAME_LOCATION_ID,
                       nullable = false,
                       insertable = false,
                       updatable = false
               ),
               inverseJoinColumns = @JoinColumn(
                       name = LocationResident.COLUMN_NAME_RESIDENT_ID,
                       nullable = false,
                       insertable = false,
                       updatable = false
               )
    )
    private List<@Valid @NotNull Character> residents_;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = Character_.ORIGIN_,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Character> originCharacters_;

    @OneToMany(mappedBy = Character_.LOCATION_,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Character> locationCharacters_;
}
