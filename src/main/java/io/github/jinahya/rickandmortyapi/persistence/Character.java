package io.github.jinahya.rickandmortyapi.persistence;

import io.github.jinahya.rickandmortyapi.persistence.converter.InstantConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlListConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
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
 * An entity class for mapping {@value Character#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see CharacterEpisode
 * @see Episode
 * @see Location
 */
@NamedQueries({
        @NamedQuery(name = "Character.selectList_NameEqual_",
                    query = """
                            SELECT c
                            FROM Character c
                            WHERE c.name = :name
                            ORDER BY c.id ASC"""),
        @NamedQuery(name = "Character.selectList__OrderByIdAsc",
                    query = """
                            SELECT c
                            FROM Character c
                            ORDER BY c.id ASC""")
})
@Entity
@Table(name = Character.TABLE_NAME)
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S115", // Constant names should comply with a naming convention
        "java:S116", // Field names should comply with a naming convention
        "java:S117"  // Local variable and method parameter names should comply with a naming convention
})
public class Character extends _BaseEntity {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "character";

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * The name of the table column to which the {@value Character_#ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ID = "id";

    // ------------------------------------------------------------------------------------------------------------ name
    public static final String COLUMN_NAME_NAME = "name";

    // ---------------------------------------------------------------------------------------------------------- status
    public static final String COLUMN_NAME_STATUS = "status";

    public static final String COLUMN_VALUE_STATUS_ALIVE = "Alive";

    public static final String COLUMN_VALUE_STATUS_DEAD = "Dead";

    public static final String COLUMN_VALUE_STATUS_UNKNOWN = "unknown";

    /**
     * Constants for {@value Character_#STATUS} attribute.
     */
    public enum Status {

        /**
         * A value for {@value #COLUMN_VALUE_STATUS_ALIVE} column value.
         */
        ALIVE(COLUMN_VALUE_STATUS_ALIVE),

        /**
         * A value for {@value #COLUMN_VALUE_STATUS_DEAD} column value.
         */
        DEAD(COLUMN_VALUE_STATUS_DEAD),

        /**
         * A value for {@value #COLUMN_VALUE_STATUS_UNKNOWN} column value.
         */
        UNKNOWN(COLUMN_VALUE_STATUS_UNKNOWN);

        /**
         * Returns the value for the specified column value.
         *
         * @param columnValue the column value.
         * @return the value for the {@code columnValue}.
         */
        public static Status valueOfColumnValue(final String columnValue) {
            for (final Status value : values()) {
                if (value.columnValue.equals(columnValue)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("no value for column value: " + columnValue);
        }

        Status(final String columnValue) {
            this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
        }

        public String columnValue() {
            return columnValue;
        }

        private final String columnValue;
    }

    @Converter(autoApply = true)
    public static class StatusConverter implements AttributeConverter<Status, String> {

        StatusConverter() {
            super();
        }

        @Override
        public String convertToDatabaseColumn(final Status attribute) {
            return Optional.ofNullable(attribute)
                    .map(Status::columnValue)
                    .orElse(null);
        }

        @Override
        public Status convertToEntityAttribute(final String dbData) {
            return Optional.ofNullable(dbData)
                    .map(Status::valueOfColumnValue)
                    .orElse(null);
        }
    }

    // --------------------------------------------------------------------------------------------------------- species

    /**
     * The name of the table column to which the {@value Character_#SPECIES} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_SPECIES = "species";

    /**
     * A column value of {@value} for the {@link #COLUMN_NAME_SPECIES} column.
     */
    public static final String COLUMN_VALUE_SPECIES_ALIEN = "Alien";

    public static final String COLUMN_VALUE_SPECIES_ANIMAL = "Animal";

    public static final String COLUMN_VALUE_SPECIES_CRONENBERG = "Cronenberg";

    public static final String COLUMN_VALUE_SPECIES_DISEASE = "Disease";

    public static final String COLUMN_VALUE_SPECIES_HUMAN = "Human";

    public static final String COLUMN_VALUE_SPECIES_HUMANOID = "Humanoid";

    public static final String COLUMN_VALUE_SPECIES_MYTHOLOGICAL_CREATURE = "Mythological Creature";

    public static final String COLUMN_VALUE_SPECIES_POOPYBUTTHOLE = "Poopybutthole";

    public static final String COLUMN_VALUE_SPECIES_ROBOT = "Robot";

    public static final String COLUMN_VALUE_SPECIES_UNKNOWN = "unknown";

    /**
     * Constants for {@value Character_#SPECIES} attribute.
     */
    public enum Species {

        /**
         * A constant for {@value #COLUMN_VALUE_SPECIES_ALIEN} column value.
         */
        ALIEN(COLUMN_VALUE_SPECIES_ALIEN),

        ANIMAL(COLUMN_VALUE_SPECIES_ANIMAL),

        CRONENBERG(COLUMN_VALUE_SPECIES_CRONENBERG),

        DISEASE(COLUMN_VALUE_SPECIES_DISEASE),

        HUMAN(COLUMN_VALUE_SPECIES_HUMAN),

        HUMANOID(COLUMN_VALUE_SPECIES_HUMANOID),

        MYTHOLOGICAL_CREATURE(COLUMN_VALUE_SPECIES_MYTHOLOGICAL_CREATURE),

        POOPYBUTTHOLE(COLUMN_VALUE_SPECIES_POOPYBUTTHOLE),

        ROBOT(COLUMN_VALUE_SPECIES_ROBOT),

        UNKNOWN(COLUMN_VALUE_SPECIES_UNKNOWN);

        public static Species valueOfColumnValue(final String columnValue) {
            for (final var value : values()) {
                if (value.columnValue.equals(columnValue)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("no value for column value: " + columnValue);
        }

        Species(final String columnValue) {
            this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
        }

        public String columnValue() {
            return columnValue;
        }

        private final String columnValue;
    }

    @Converter(autoApply = true)
    public static class SpeciesConverter implements AttributeConverter<Species, String> {

        SpeciesConverter() {
            super();
        }

        @Override
        public String convertToDatabaseColumn(final Species attribute) {
            return Optional.ofNullable(attribute)
                    .map(Species::columnValue)
                    .orElse(null);
        }

        @Override
        public Species convertToEntityAttribute(final String dbData) {
            return Optional.ofNullable(dbData)
                    .map(Species::valueOfColumnValue)
                    .orElse(null);
        }
    }

    // ------------------------------------------------------------------------------------------------------------ type
    public static final String COLUMN_NAME_TYPE = "type";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_ALLIGATOR_PERSON = "Alligator-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_ALPHABETRIAN = "Alphabetrian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_AMOEBA_PERSON = "Amoeba-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_ANIMAL = "Animal";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_ANIME = "Anime";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_ARTIFICIAL_INTELLIGENCE = "Artificial Intelligence";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_BEPISIAN = "Bepisian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_BIRD_PERSON = "Bird-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_BIRD_PERSON_HUMAN_MIX = "Bird-Person Human Mix";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_BLUE_APE_ALIEN = "Blue ape alien";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_BOOBLE_BUYER_REPTILIAN = "Boobie buyer reptilian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_BOOBLOOSIAN = "Boobloosian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_BREAD = "Bread";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CHUD = "CHUD";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CHUD_HUMAN_MIX = "CHUD Human Mix";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CAT = "Cat";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CAT_CONTROLLED_DEAD_LADY = "Cat controlled dead lady";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CAT_PERSON = "Cat-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CATERPILLAR = "Caterpillar";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CENTAUR = "Centaur";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CHAIR = "Chair";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CHANGEFORMER = "Changeformer";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CLAY_PERSON = "Clay-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CLONE = "Clone";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CONE_NIPPLED_ALIEN = "Cone-nippled alien";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CONJOINED_TWIN = "Conjoined twin";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_COOKIE = "Cookie";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CORN_PERSON = "Corn-person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CROMULON = "Cromulon";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CRONENBERG = "Cronenberg";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CROW = "Crow";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CROW_HORSE = "Crow Horse";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_CYBORG = "Cyborg";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_DECOY = "Decoy";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_DEMON = "Demon";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_DOG = "Dog";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_DOOPIDOO = "Doopidoo";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_DRAGON = "Dragon";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_DRUMBLOXIAN = "Drumbloxian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_DUMMY = "Dummy";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_EAT_SHITER_PERSON = "Eat shiter-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_EEL = "Eel";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_ELEPHANT_PERSON = "Elephant-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_FERKUSIAN = "Ferkusian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_FERRET_ROBOT = "Ferret Robot";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_FISH_PERSON = "Fish-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_FLANSIAN = "Flansian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_FLOOP_FLOOPIAN = "Floop Floopian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_FLY = "Fly";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GAME = "Game";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GARBLOVIAN = "Garblovian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GAZORPIAN = "Gazorpian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GAZORPIAN_REPRODUCTION_ROBOT = "Gazorpian reproduction robot";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GEAR_PERSON = "Gear-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GENETIC_EXPERIMENT = "Genetic experiment";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GIANT = "Giant";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GIANT_CAT_MONSTER = "Giant Cat Monster";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GIANT_INCEST_BABY = "Giant Incest Baby";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GLORZO = "Glorzo";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GOD = "God";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GODDESS = "Goddess";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GRAMUFLAKIAN = "Gramuflackian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GRANDMA = "Grandma";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GREEBYBOBE = "Greebybobe";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GROMFLOMITE = "Gromflomite";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_GUINEA_PIG_FOR_THE_POLIO_VACCINE = "Guinea Pig for the Polio Vaccine";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HAIRY_ALIEN = "Hairy alien";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HALF_SOULLESS_PUPPET = "Half Soulless Puppet";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HAMMERHEAD_PERSON = "Hammerhead-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HIVEMIND = "Hivemind";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HOLE = "Hole";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HOLOGRAM = "Hologram";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HUMAN_GAZORPIAN = "Human Gazorpian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HUMAN_WITH_A_FLOWER_IN_HIS_HEAD = "Human with a flower in his head";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HUMAN_WITH_ANTENNAE = "Human with antennae";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HUMAN_WITH_ANTS_IN_HIS_EYES = "Human with ants in his eyes";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HUMAN_WITH_BABY_LEGS = "Human with baby legs";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HUMAN_WITH_GIANT_HEAD = "Human with giant head";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HUMAN_WITH_TUSKS = "Human with tusks";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_HUMAN_SNAKE_HYBRID = "Human-Snake hybrid";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_INTERDIMENSIONAL_GASEOUS_BEING = "Interdimensional gaseous being";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_JELLYBEAN = "Jellybean";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_KORBLOCK = "Korblock";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_KROOTABULAN = "Krootabulan";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_LARVA_ALIEN = "Larva alien";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_LEPRECHAUN = "Leprechaun";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_LIGHT_BULB_ALIEN = "Light bulb-Alien";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_LITTLE_HUMAN = "Little Human";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_LIZARD = "Lizard";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_LIZARD_PERSON = "Lizard-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_LOBSTER_ALIEN = "Lobster-Alien";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MANNIE = "Mannie";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MASCOT = "Mascot";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MEESEEKS = "Meeseeks";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MEGA_GARGANTUAN = "Mega Gargantuan";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MEMORY = "Memory";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MEXICAN = "Mexican";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MICROVERSE_INHABITANT = "Microverse inhabitant";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MINIVERSE_INHABITANT = "Miniverse inhabitant";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MONOGATRON = "Monogatron";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MONSTER = "Monster";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MORGLUTZIAN = "Morglutzian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MORTYS_TOXIC_SIDE = "Morty's toxic side";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_MYTHOLOG = "Mytholog";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_NANO_ALIEN = "Nano Alien";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_NARNIAN = "Narnian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_NECROPHILIAC = "Necrophiliac";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_NORMAL_SIZE_BUG = "Normal Size Bug";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_NUMBERICON = "Numbericon";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_OCTOPUS_PERSON = "Octopus-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_OLD_AMAZONS = "Old Amazons";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_OMNISCIENT_BEING = "Omniscient being";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_ORGANIC_GUN = "Organic gun";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_PARASITE = "Parasite";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_PASSING_BUTTER_ROBOT = "Passing Butter Robot";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_PHONE = "Phone";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_PHONE_PERSON = "Phone-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_PICKLE = "Pickle";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_PIZZA = "Pizza";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_PLANET = "Planet";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_PLUTONIAN = "Plutonian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_PRIPUDLIAN = "Pripudlian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_RAT = "Rat";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_RICKS_TOXIC_SIDE = "Rick's toxic side";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_RING_NIPPLED_ALIEN = "Ring-nippled alien";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_ROBOT = "Robot";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_ROBOT_CROCODILE_HYBRID = "Robot-Crocodile hybrid";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SCARECROW = "Scarecrow";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SCROTIAN = "Scrotian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SELF_AWARE_ARM = "Self-aware arm";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SENTIENT_ANT_COLONY = "Sentient ant colony";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SEXY_AQUAMAN = "Sexy Aquaman";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SHAPESHIFTER = "Shapeshifter";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SHIMSHAMIAN = "Shimshamian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SHRIMP = "Shrimp";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SLARTIVARTIAN = "Slartivartian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SLUG = "Slug";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SNAIL_ALIEN = "Snail alien";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SNAKE = "Snake";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SOULLESS_PUPPET = "Soulless Puppet";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SQUID = "Squid";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_STAIR_GOBLIN = "Stair goblin";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_STARFISH = "Starfish";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SUMMON = "Summon";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SUPER_SPERM_MONSTER = "Super Sperm Monster";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SUPERHUMAN = "Superhuman";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_SUPERHUMAN_GHOST_TRAINS_SUMMONER = "Superhuman (Ghost trains summoner)";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TEDDY_BEAR = "Teddy Bear";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TEENYVERSE_INHABITANT = "Teenyverse inhabitant";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TENTACLE_ALIEN = "Tentacle alien";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_THE_DEVIL = "The Devil";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TIGER = "Tiger";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TIME_GOD = "Time God";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TINYMOUTH = "Tinymouth";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TOY = "Toy";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TRAFLORKIAN = "Traflorkian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TRUNK_PERSON = "Trunk-Person";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TUMBLORKIAN = "Tumblorkian";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TURKEY = "Turkey";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TURKEY_HUMAN_MIX = "Turkey Human Mix";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_TUSKFISH = "Tuskfish";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_UNKNOWN_NIPPLED_ALIEN = "Unknown-nippled alien";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_VAMPIRE = "Vampire";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_WASP = "Wasp";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_WEASEL = "Weasel";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_WHENWOLF = "Whenwolf";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_ZEUS = "Zeus";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_ZIGERION = "Zigerion";

    /**
     * A value of {@value} for the {@value #COLUMN_NAME_TYPE} column.
     */
    static final String COLUMN_VALUE_TYPE_ZOMBODIAN = "Zombodian";

    /**
     * Constants for the {@value Character_#TYPE} attribute.
     */
    public enum Type {

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_ALLIGATOR_PERSON} column value.
         */
        ALLIGATOR_PERSON(Character.COLUMN_VALUE_TYPE_ALLIGATOR_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_ALPHABETRIAN} column value.
         */
        ALPHABETRIAN(Character.COLUMN_VALUE_TYPE_ALPHABETRIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_AMOEBA_PERSON} column value.
         */
        AMOEBA_PERSON(Character.COLUMN_VALUE_TYPE_AMOEBA_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_ANIMAL} column value.
         */
        ANIMAL(Character.COLUMN_VALUE_TYPE_ANIMAL),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_ANIME} column value.
         */
        ANIME(Character.COLUMN_VALUE_TYPE_ANIME),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_ARTIFICIAL_INTELLIGENCE} column value.
         */
        ARTIFICIAL_INTELLIGENCE(Character.COLUMN_VALUE_TYPE_ARTIFICIAL_INTELLIGENCE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_BEPISIAN} column value.
         */
        BEPISIAN(Character.COLUMN_VALUE_TYPE_BEPISIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_BIRD_PERSON} column value.
         */
        BIRD_PERSON(Character.COLUMN_VALUE_TYPE_BIRD_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_BIRD_PERSON_HUMAN_MIX} column value.
         */
        BIRD_PERSON_HUMAN_MIX(Character.COLUMN_VALUE_TYPE_BIRD_PERSON_HUMAN_MIX),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_BLUE_APE_ALIEN} column value.
         */
        BLUE_APE_ALIEN(Character.COLUMN_VALUE_TYPE_BLUE_APE_ALIEN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_BOOBLE_BUYER_REPTILIAN} column value.
         */
        BOOBLE_BUYER_REPTILIAN(Character.COLUMN_VALUE_TYPE_BOOBLE_BUYER_REPTILIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_BOOBLOOSIAN} column value.
         */
        BOOBLOOSIAN(Character.COLUMN_VALUE_TYPE_BOOBLOOSIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_BREAD} column value.
         */
        BREAD(Character.COLUMN_VALUE_TYPE_BREAD),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CAT} column value.
         */
        CAT(Character.COLUMN_VALUE_TYPE_CAT),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CAT_CONTROLLED_DEAD_LADY} column value.
         */
        CAT_CONTROLLED_DEAD_LADY(Character.COLUMN_VALUE_TYPE_CAT_CONTROLLED_DEAD_LADY),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CAT_PERSON} column value.
         */
        CAT_PERSON(Character.COLUMN_VALUE_TYPE_CAT_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CATERPILLAR} column value.
         */
        CATERPILLAR(Character.COLUMN_VALUE_TYPE_CATERPILLAR),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CENTAUR} column value.
         */
        CENTAUR(Character.COLUMN_VALUE_TYPE_CENTAUR),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CHAIR} column value.
         */
        CHAIR(Character.COLUMN_VALUE_TYPE_CHAIR),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CHANGEFORMER} column value.
         */
        CHANGEFORMER(Character.COLUMN_VALUE_TYPE_CHANGEFORMER),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CHUD} column value.
         */
        CHUD(Character.COLUMN_VALUE_TYPE_CHUD),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CHUD_HUMAN_MIX} column value.
         */
        CHUD_HUMAN_MIX(Character.COLUMN_VALUE_TYPE_CHUD_HUMAN_MIX),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CLAY_PERSON} column value.
         */
        CLAY_PERSON(Character.COLUMN_VALUE_TYPE_CLAY_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CLONE} column value.
         */
        CLONE(Character.COLUMN_VALUE_TYPE_CLONE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CONE_NIPPLED_ALIEN} column value.
         */
        CONE_NIPPLED_ALIEN(Character.COLUMN_VALUE_TYPE_CONE_NIPPLED_ALIEN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CONJOINED_TWIN} column value.
         */
        CONJOINED_TWIN(Character.COLUMN_VALUE_TYPE_CONJOINED_TWIN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_COOKIE} column value.
         */
        COOKIE(Character.COLUMN_VALUE_TYPE_COOKIE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CORN_PERSON} column value.
         */
        CORN_PERSON(Character.COLUMN_VALUE_TYPE_CORN_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CROMULON} column value.
         */
        CROMULON(Character.COLUMN_VALUE_TYPE_CROMULON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CRONENBERG} column value.
         */
        CRONENBERG(Character.COLUMN_VALUE_TYPE_CRONENBERG),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CROW} column value.
         */
        CROW(Character.COLUMN_VALUE_TYPE_CROW),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CROW_HORSE} column value.
         */
        CROW_HORSE(Character.COLUMN_VALUE_TYPE_CROW_HORSE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_CYBORG} column value.
         */
        CYBORG(Character.COLUMN_VALUE_TYPE_CYBORG),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_DECOY} column value.
         */
        DECOY(Character.COLUMN_VALUE_TYPE_DECOY),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_DEMON} column value.
         */
        DEMON(Character.COLUMN_VALUE_TYPE_DEMON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_DOG} column value.
         */
        DOG(Character.COLUMN_VALUE_TYPE_DOG),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_DOOPIDOO} column value.
         */
        DOOPIDOO(Character.COLUMN_VALUE_TYPE_DOOPIDOO),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_DRAGON} column value.
         */
        DRAGON(Character.COLUMN_VALUE_TYPE_DRAGON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_DRUMBLOXIAN} column value.
         */
        DRUMBLOXIAN(Character.COLUMN_VALUE_TYPE_DRUMBLOXIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_DUMMY} column value.
         */
        DUMMY(Character.COLUMN_VALUE_TYPE_DUMMY),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_EAT_SHITER_PERSON} column value.
         */
        EAT_SHITER_PERSON(Character.COLUMN_VALUE_TYPE_EAT_SHITER_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_EEL} column value.
         */
        EEL(Character.COLUMN_VALUE_TYPE_EEL),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_ELEPHANT_PERSON} column value.
         */
        ELEPHANT_PERSON(Character.COLUMN_VALUE_TYPE_ELEPHANT_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_FERKUSIAN} column value.
         */
        FERKUSIAN(Character.COLUMN_VALUE_TYPE_FERKUSIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_FERRET_ROBOT} column value.
         */
        FERRET_ROBOT(Character.COLUMN_VALUE_TYPE_FERRET_ROBOT),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_FISH_PERSON} column value.
         */
        FISH_PERSON(Character.COLUMN_VALUE_TYPE_FISH_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_FLANSIAN} column value.
         */
        FLANSIAN(Character.COLUMN_VALUE_TYPE_FLANSIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_FLOOP_FLOOPIAN} column value.
         */
        FLOOP_FLOOPIAN(Character.COLUMN_VALUE_TYPE_FLOOP_FLOOPIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_FLY} column value.
         */
        FLY(Character.COLUMN_VALUE_TYPE_FLY),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GAME} column value.
         */
        GAME(Character.COLUMN_VALUE_TYPE_GAME),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GARBLOVIAN} column value.
         */
        GARBLOVIAN(Character.COLUMN_VALUE_TYPE_GARBLOVIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GAZORPIAN} column value.
         */
        GAZORPIAN(Character.COLUMN_VALUE_TYPE_GAZORPIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GAZORPIAN_REPRODUCTION_ROBOT} column value.
         */
        GAZORPIAN_REPRODUCTION_ROBOT(Character.COLUMN_VALUE_TYPE_GAZORPIAN_REPRODUCTION_ROBOT),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GEAR_PERSON} column value.
         */
        GEAR_PERSON(Character.COLUMN_VALUE_TYPE_GEAR_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GENETIC_EXPERIMENT} column value.
         */
        GENETIC_EXPERIMENT(Character.COLUMN_VALUE_TYPE_GENETIC_EXPERIMENT),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GIANT} column value.
         */
        GIANT(Character.COLUMN_VALUE_TYPE_GIANT),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GIANT_CAT_MONSTER} column value.
         */
        GIANT_CAT_MONSTER(Character.COLUMN_VALUE_TYPE_GIANT_CAT_MONSTER),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GIANT_INCEST_BABY} column value.
         */
        GIANT_INCEST_BABY(Character.COLUMN_VALUE_TYPE_GIANT_INCEST_BABY),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GLORZO} column value.
         */
        GLORZO(Character.COLUMN_VALUE_TYPE_GLORZO),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GOD} column value.
         */
        GOD(Character.COLUMN_VALUE_TYPE_GOD),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GODDESS} column value.
         */
        GODDESS(Character.COLUMN_VALUE_TYPE_GODDESS),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GRAMUFLAKIAN} column value.
         */
        GRAMUFLAKIAN(Character.COLUMN_VALUE_TYPE_GRAMUFLAKIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GRANDMA} column value.
         */
        GRANDMA(Character.COLUMN_VALUE_TYPE_GRANDMA),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GREEBYBOBE} column value.
         */
        GREEBYBOBE(Character.COLUMN_VALUE_TYPE_GREEBYBOBE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GROMFLOMITE} column value.
         */
        GROMFLOMITE(Character.COLUMN_VALUE_TYPE_GROMFLOMITE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_GUINEA_PIG_FOR_THE_POLIO_VACCINE} column value.
         */
        GUINEA_PIG_FOR_THE_POLIO_VACCINE(Character.COLUMN_VALUE_TYPE_GUINEA_PIG_FOR_THE_POLIO_VACCINE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HAIRY_ALIEN} column value.
         */
        HAIRY_ALIEN(Character.COLUMN_VALUE_TYPE_HAIRY_ALIEN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HALF_SOULLESS_PUPPET} column value.
         */
        HALF_SOULLESS_PUPPET(Character.COLUMN_VALUE_TYPE_HALF_SOULLESS_PUPPET),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HAMMERHEAD_PERSON} column value.
         */
        HAMMERHEAD_PERSON(Character.COLUMN_VALUE_TYPE_HAMMERHEAD_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HIVEMIND} column value.
         */
        HIVEMIND(Character.COLUMN_VALUE_TYPE_HIVEMIND),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HOLE} column value.
         */
        HOLE(Character.COLUMN_VALUE_TYPE_HOLE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HOLOGRAM} column value.
         */
        HOLOGRAM(Character.COLUMN_VALUE_TYPE_HOLOGRAM),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HUMAN_GAZORPIAN} column value.
         */
        HUMAN_GAZORPIAN(Character.COLUMN_VALUE_TYPE_HUMAN_GAZORPIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HUMAN_SNAKE_HYBRID} column value.
         */
        HUMAN_SNAKE_HYBRID(Character.COLUMN_VALUE_TYPE_HUMAN_SNAKE_HYBRID),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HUMAN_WITH_A_FLOWER_IN_HIS_HEAD} column value.
         */
        HUMAN_WITH_A_FLOWER_IN_HIS_HEAD(Character.COLUMN_VALUE_TYPE_HUMAN_WITH_A_FLOWER_IN_HIS_HEAD),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HUMAN_WITH_ANTENNAE} column value.
         */
        HUMAN_WITH_ANTENNAE(Character.COLUMN_VALUE_TYPE_HUMAN_WITH_ANTENNAE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HUMAN_WITH_ANTS_IN_HIS_EYES} column value.
         */
        HUMAN_WITH_ANTS_IN_HIS_EYES(Character.COLUMN_VALUE_TYPE_HUMAN_WITH_ANTS_IN_HIS_EYES),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HUMAN_WITH_BABY_LEGS} column value.
         */
        HUMAN_WITH_BABY_LEGS(Character.COLUMN_VALUE_TYPE_HUMAN_WITH_BABY_LEGS),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HUMAN_WITH_GIANT_HEAD} column value.
         */
        HUMAN_WITH_GIANT_HEAD(Character.COLUMN_VALUE_TYPE_HUMAN_WITH_GIANT_HEAD),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_HUMAN_WITH_TUSKS} column value.
         */
        HUMAN_WITH_TUSKS(Character.COLUMN_VALUE_TYPE_HUMAN_WITH_TUSKS),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_INTERDIMENSIONAL_GASEOUS_BEING} column value.
         */
        INTERDIMENSIONAL_GASEOUS_BEING(Character.COLUMN_VALUE_TYPE_INTERDIMENSIONAL_GASEOUS_BEING),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_JELLYBEAN} column value.
         */
        JELLYBEAN(Character.COLUMN_VALUE_TYPE_JELLYBEAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_KORBLOCK} column value.
         */
        KORBLOCK(Character.COLUMN_VALUE_TYPE_KORBLOCK),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_KROOTABULAN} column value.
         */
        KROOTABULAN(Character.COLUMN_VALUE_TYPE_KROOTABULAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_LARVA_ALIEN} column value.
         */
        LARVA_ALIEN(Character.COLUMN_VALUE_TYPE_LARVA_ALIEN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_LEPRECHAUN} column value.
         */
        LEPRECHAUN(Character.COLUMN_VALUE_TYPE_LEPRECHAUN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_LIGHT_BULB_ALIEN} column value.
         */
        LIGHT_BULB_ALIEN(Character.COLUMN_VALUE_TYPE_LIGHT_BULB_ALIEN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_LITTLE_HUMAN} column value.
         */
        LITTLE_HUMAN(Character.COLUMN_VALUE_TYPE_LITTLE_HUMAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_LIZARD} column value.
         */
        LIZARD(Character.COLUMN_VALUE_TYPE_LIZARD),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_LIZARD_PERSON} column value.
         */
        LIZARD_PERSON(Character.COLUMN_VALUE_TYPE_LIZARD_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_LOBSTER_ALIEN} column value.
         */
        LOBSTER_ALIEN(Character.COLUMN_VALUE_TYPE_LOBSTER_ALIEN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MANNIE} column value.
         */
        MANNIE(Character.COLUMN_VALUE_TYPE_MANNIE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MASCOT} column value.
         */
        MASCOT(Character.COLUMN_VALUE_TYPE_MASCOT),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MEESEEKS} column value.
         */
        MEESEEKS(Character.COLUMN_VALUE_TYPE_MEESEEKS),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MEGA_GARGANTUAN} column value.
         */
        MEGA_GARGANTUAN(Character.COLUMN_VALUE_TYPE_MEGA_GARGANTUAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MEMORY} column value.
         */
        MEMORY(Character.COLUMN_VALUE_TYPE_MEMORY),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MEXICAN} column value.
         */
        MEXICAN(Character.COLUMN_VALUE_TYPE_MEXICAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MICROVERSE_INHABITANT} column value.
         */
        MICROVERSE_INHABITANT(Character.COLUMN_VALUE_TYPE_MICROVERSE_INHABITANT),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MINIVERSE_INHABITANT} column value.
         */
        MINIVERSE_INHABITANT(Character.COLUMN_VALUE_TYPE_MINIVERSE_INHABITANT),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MONOGATRON} column value.
         */
        MONOGATRON(Character.COLUMN_VALUE_TYPE_MONOGATRON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MONSTER} column value.
         */
        MONSTER(Character.COLUMN_VALUE_TYPE_MONSTER),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MORGLUTZIAN} column value.
         */
        MORGLUTZIAN(Character.COLUMN_VALUE_TYPE_MORGLUTZIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MORTYS_TOXIC_SIDE} column value.
         */
        MORTYS_TOXIC_SIDE(Character.COLUMN_VALUE_TYPE_MORTYS_TOXIC_SIDE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_MYTHOLOG} column value.
         */
        MYTHOLOG(Character.COLUMN_VALUE_TYPE_MYTHOLOG),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_NANO_ALIEN} column value.
         */
        NANO_ALIEN(Character.COLUMN_VALUE_TYPE_NANO_ALIEN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_NARNIAN} column value.
         */
        NARNIAN(Character.COLUMN_VALUE_TYPE_NARNIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_NECROPHILIAC} column value.
         */
        NECROPHILIAC(Character.COLUMN_VALUE_TYPE_NECROPHILIAC),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_NORMAL_SIZE_BUG} column value.
         */
        NORMAL_SIZE_BUG(Character.COLUMN_VALUE_TYPE_NORMAL_SIZE_BUG),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_NUMBERICON} column value.
         */
        NUMBERICON(Character.COLUMN_VALUE_TYPE_NUMBERICON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_OCTOPUS_PERSON} column value.
         */
        OCTOPUS_PERSON(Character.COLUMN_VALUE_TYPE_OCTOPUS_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_OLD_AMAZONS} column value.
         */
        OLD_AMAZONS(Character.COLUMN_VALUE_TYPE_OLD_AMAZONS),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_OMNISCIENT_BEING} column value.
         */
        OMNISCIENT_BEING(Character.COLUMN_VALUE_TYPE_OMNISCIENT_BEING),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_ORGANIC_GUN} column value.
         */
        ORGANIC_GUN(Character.COLUMN_VALUE_TYPE_ORGANIC_GUN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_PARASITE} column value.
         */
        PARASITE(Character.COLUMN_VALUE_TYPE_PARASITE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_PASSING_BUTTER_ROBOT} column value.
         */
        PASSING_BUTTER_ROBOT(Character.COLUMN_VALUE_TYPE_PASSING_BUTTER_ROBOT),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_PHONE} column value.
         */
        PHONE(Character.COLUMN_VALUE_TYPE_PHONE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_PHONE_PERSON} column value.
         */
        PHONE_PERSON(Character.COLUMN_VALUE_TYPE_PHONE_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_PICKLE} column value.
         */
        PICKLE(Character.COLUMN_VALUE_TYPE_PICKLE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_PIZZA} column value.
         */
        PIZZA(Character.COLUMN_VALUE_TYPE_PIZZA),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_PLANET} column value.
         */
        PLANET(Character.COLUMN_VALUE_TYPE_PLANET),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_PLUTONIAN} column value.
         */
        PLUTONIAN(Character.COLUMN_VALUE_TYPE_PLUTONIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_PRIPUDLIAN} column value.
         */
        PRIPUDLIAN(Character.COLUMN_VALUE_TYPE_PRIPUDLIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_RAT} column value.
         */
        RAT(Character.COLUMN_VALUE_TYPE_RAT),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_RICKS_TOXIC_SIDE} column value.
         */
        RICKS_TOXIC_SIDE(Character.COLUMN_VALUE_TYPE_RICKS_TOXIC_SIDE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_RING_NIPPLED_ALIEN} column value.
         */
        RING_NIPPLED_ALIEN(Character.COLUMN_VALUE_TYPE_RING_NIPPLED_ALIEN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_ROBOT} column value.
         */
        ROBOT(Character.COLUMN_VALUE_TYPE_ROBOT),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_ROBOT_CROCODILE_HYBRID} column value.
         */
        ROBOT_CROCODILE_HYBRID(Character.COLUMN_VALUE_TYPE_ROBOT_CROCODILE_HYBRID),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SCARECROW} column value.
         */
        SCARECROW(Character.COLUMN_VALUE_TYPE_SCARECROW),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SCROTIAN} column value.
         */
        SCROTIAN(Character.COLUMN_VALUE_TYPE_SCROTIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SELF_AWARE_ARM} column value.
         */
        SELF_AWARE_ARM(Character.COLUMN_VALUE_TYPE_SELF_AWARE_ARM),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SENTIENT_ANT_COLONY} column value.
         */
        SENTIENT_ANT_COLONY(Character.COLUMN_VALUE_TYPE_SENTIENT_ANT_COLONY),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SEXY_AQUAMAN} column value.
         */
        SEXY_AQUAMAN(Character.COLUMN_VALUE_TYPE_SEXY_AQUAMAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SHAPESHIFTER} column value.
         */
        SHAPESHIFTER(Character.COLUMN_VALUE_TYPE_SHAPESHIFTER),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SHIMSHAMIAN} column value.
         */
        SHIMSHAMIAN(Character.COLUMN_VALUE_TYPE_SHIMSHAMIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SHRIMP} column value.
         */
        SHRIMP(Character.COLUMN_VALUE_TYPE_SHRIMP),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SLARTIVARTIAN} column value.
         */
        SLARTIVARTIAN(Character.COLUMN_VALUE_TYPE_SLARTIVARTIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SLUG} column value.
         */
        SLUG(Character.COLUMN_VALUE_TYPE_SLUG),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SNAIL_ALIEN} column value.
         */
        SNAIL_ALIEN(Character.COLUMN_VALUE_TYPE_SNAIL_ALIEN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SNAKE} column value.
         */
        SNAKE(Character.COLUMN_VALUE_TYPE_SNAKE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SOULLESS_PUPPET} column value.
         */
        SOULLESS_PUPPET(Character.COLUMN_VALUE_TYPE_SOULLESS_PUPPET),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SQUID} column value.
         */
        SQUID(Character.COLUMN_VALUE_TYPE_SQUID),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_STARFISH} column value.
         */
        STARFISH(Character.COLUMN_VALUE_TYPE_STARFISH),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_STAIR_GOBLIN} column value.
         */
        STAIR_GOBLIN(Character.COLUMN_VALUE_TYPE_STAIR_GOBLIN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SUMMON} column value.
         */
        SUMMON(Character.COLUMN_VALUE_TYPE_SUMMON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SUPER_SPERM_MONSTER} column value.
         */
        SUPER_SPERM_MONSTER(Character.COLUMN_VALUE_TYPE_SUPER_SPERM_MONSTER),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SUPERHUMAN} column value.
         */
        SUPERHUMAN(Character.COLUMN_VALUE_TYPE_SUPERHUMAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_SUPERHUMAN_GHOST_TRAINS_SUMMONER} column value.
         */
        SUPERHUMAN_GHOST_TRAINS_SUMMONER(Character.COLUMN_VALUE_TYPE_SUPERHUMAN_GHOST_TRAINS_SUMMONER),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TEDDY_BEAR} column value.
         */
        TEDDY_BEAR(Character.COLUMN_VALUE_TYPE_TEDDY_BEAR),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TEENYVERSE_INHABITANT} column value.
         */
        TEENYVERSE_INHABITANT(Character.COLUMN_VALUE_TYPE_TEENYVERSE_INHABITANT),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TENTACLE_ALIEN} column value.
         */
        TENTACLE_ALIEN(Character.COLUMN_VALUE_TYPE_TENTACLE_ALIEN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_THE_DEVIL} column value.
         */
        THE_DEVIL(Character.COLUMN_VALUE_TYPE_THE_DEVIL),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TIGER} column value.
         */
        TIGER(Character.COLUMN_VALUE_TYPE_TIGER),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TIME_GOD} column value.
         */
        TIME_GOD(Character.COLUMN_VALUE_TYPE_TIME_GOD),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TINYMOUTH} column value.
         */
        TINYMOUTH(Character.COLUMN_VALUE_TYPE_TINYMOUTH),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TOY} column value.
         */
        TOY(Character.COLUMN_VALUE_TYPE_TOY),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TRAFLORKIAN} column value.
         */
        TRAFLORKIAN(Character.COLUMN_VALUE_TYPE_TRAFLORKIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TRUNK_PERSON} column value.
         */
        TRUNK_PERSON(Character.COLUMN_VALUE_TYPE_TRUNK_PERSON),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TUMBLORKIAN} column value.
         */
        TUMBLORKIAN(Character.COLUMN_VALUE_TYPE_TUMBLORKIAN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TURKEY} column value.
         */
        TURKEY(Character.COLUMN_VALUE_TYPE_TURKEY),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TURKEY_HUMAN_MIX} column value.
         */
        TURKEY_HUMAN_MIX(Character.COLUMN_VALUE_TYPE_TURKEY_HUMAN_MIX),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_TUSKFISH} column value.
         */
        TUSKFISH(Character.COLUMN_VALUE_TYPE_TUSKFISH),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_UNKNOWN_NIPPLED_ALIEN} column value.
         */
        UNKNOWN_NIPPLED_ALIEN(Character.COLUMN_VALUE_TYPE_UNKNOWN_NIPPLED_ALIEN),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_VAMPIRE} column value.
         */
        VAMPIRE(Character.COLUMN_VALUE_TYPE_VAMPIRE),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_WASP} column value.
         */
        WASP(Character.COLUMN_VALUE_TYPE_WASP),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_WEASEL} column value.
         */
        WEASEL(Character.COLUMN_VALUE_TYPE_WEASEL),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_WHENWOLF} column value.
         */
        WHENWOLF(Character.COLUMN_VALUE_TYPE_WHENWOLF),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_ZEUS} column value.
         */
        ZEUS(Character.COLUMN_VALUE_TYPE_ZEUS),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_ZIGERION} column value.
         */
        ZIGERION(Character.COLUMN_VALUE_TYPE_ZIGERION),

        /**
         * A constant for the {@value COLUMN_VALUE_TYPE_ZOMBODIAN} column value.
         */
        ZOMBODIAN(Character.COLUMN_VALUE_TYPE_ZOMBODIAN);

        // -------------------------------------------------------------------------------------------------------------
        public static Type valueOfColumnValue(final String columnValue) {
            Objects.requireNonNull(columnValue, "columnValue is null");
            for (final var value : values()) {
                if (value.columnValue.equals(columnValue)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("no value for column value: " + columnValue);
        }

        // -------------------------------------------------------------------------------------------------------------
        Type(final String columnValue) {
            this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
        }

        // -------------------------------------------------------------------------------------------------------------
        public String columnValue() {
            return columnValue;
        }

        // -------------------------------------------------------------------------------------------------------------
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

    // ---------------------------------------------------------------------------------------------------------- gender

    /**
     * The name of the table column to which the {@value Character_#GENDER} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_GENDER = "gender";

    /**
     * A column value of {@value} for the {@value  #COLUMN_NAME_GENDER} column.
     */
    public static final String COLUMN_VALUE_GENDER_FEMALE = "Female";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_GENDER} column.
     */
    public static final String COLUMN_VALUE_GENDER_GENDERLESS = "Genderless";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_GENDER} column.
     */
    public static final String COLUMN_VALUE_GENDER_MALE = "Male";

    /**
     * A column value of {@value} for the {@value #COLUMN_NAME_GENDER} column.
     */
    public static final String COLUMN_VALUE_GENDER_UNKNOWN = "unknown";

    public enum Gender {

        /**
         * A constant for the {@value #COLUMN_VALUE_GENDER_FEMALE} column value.
         */
        GENDER_FEMALE(COLUMN_VALUE_GENDER_FEMALE),

        /**
         * A constant for the {@value #COLUMN_VALUE_GENDER_GENDERLESS} column value.
         */
        GENDER_GENDERLESS(COLUMN_VALUE_GENDER_GENDERLESS),

        /**
         * A constant for the {@value #COLUMN_VALUE_GENDER_MALE} column value.
         */
        GENDER_MALE(COLUMN_VALUE_GENDER_MALE),

        /**
         * A constant for the {@value #COLUMN_VALUE_GENDER_UNKNOWN} column value.
         */
        GENDER_UNKNOWN(COLUMN_VALUE_GENDER_UNKNOWN);

        /**
         * Returns the constants whose {@link #columnValue() columnValue} matches the specified {@code columnValue}.
         *
         * @param columnValue the value for the {@link #columnValue() columnValue} to match.
         * @return the constants whose {@link #columnValue()} matches the specified {@code columnValue}.
         */
        public static Gender valueOfColumnValue(final String columnValue) {
            for (final var value : values()) {
                if (value.columnValue.equals(columnValue)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("no value for column value: " + columnValue);
        }

        Gender(final String columnValue) {
            this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
        }

        /**
         * Returns the column value of this constant.
         *
         * @return the column value of this constant.
         */
        public String columnValue() {
            return columnValue;
        }

        private final String columnValue;
    }

    @Converter(autoApply = true)
    public static class GenderConverter implements AttributeConverter<Gender, String> {

        GenderConverter() {
            super();
        }

        @Override
        public String convertToDatabaseColumn(final Gender attribute) {
            return Optional.ofNullable(attribute)
                    .map(Gender::columnValue)
                    .orElse(null);
        }

        @Override
        public Gender convertToEntityAttribute(final String dbData) {
            return Optional.ofNullable(dbData)
                    .map(Gender::valueOfColumnValue)
                    .orElse(null);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static final String PROPERTY_PATH_ORIGIN_NAME = "$.origin.name";

    /**
     * The name of the table column to which the {@value Character_#ORIGIN}.{@value NameAndUrl_#NAME} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_ORIGIN_NAME = "origin_name";

    // -----------------------------------------------------------------------------------------------------------------
    static final String PROPERTY_PATH_ORIGIN_URL = "$.origin.url";

    /**
     * The name of the table column to which the {@value Character_#ORIGIN}.{@value NameAndUrl_#URL} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_ORIGIN_URL = "origin_url";

    // -----------------------------------------------------------------------------------------------------------------
    static final String PROPERTY_PATH_LOCATION_NAME = "$.location.name";

    /**
     * The name of the table column to which the {@value Character_#LOCATION}.{@value NameAndUrl_#NAME} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_NAME = "location_name";

    // -----------------------------------------------------------------------------------------------------------------
    static final String PROPERTY_PATH_LOCATION_URL = "$.location.url";

    /**
     * The name of the table column to which the {@value Character_#LOCATION}.{@value NameAndUrl_#URL} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_URL = "location_url";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Character_#IMAGE} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_IMAGE = "image";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_EPISODE = "episode";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_URL = "url";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CREATED = "created";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_ORIGIN_ID_ = "origin_id_";

    public static final String ATTRIBUTE_NAME_ORIGIN_ = "origin_";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_LOCATION_ID_ = "location_id_";

    public static final String ATTRIBUTE_NAME_LOCATION_ = "location_";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    static Character of(final int id) {
        return new Character().id(id);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Character() {
        super();
    }

    // ------------------------------------------------------------------------------------------------------- java.lang
    // .Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
               ",name=" + name +
               ",status=" + status +
               ",species=" + species +
               ",type=" + type +
               ",gender=" + gender +
               ",origin=" + origin +
               ",location=" + location +
               ",image=" + image +
               ",episode=" + episode +
               ",url=" + url +
               ",created=" + created +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Character that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@value Character_#ID} attribute.
     *
     * @return current value of the {@value Character_#ID} attribute.
     */
    public Integer getId() {
        return id;
    }

    void setId(final Integer id) {
        this.id = id;
    }

    Character id(final Integer id) {
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

    // ---------------------------------------------------------------------------------------------------------- status
    public Status getStatus() {
        return status;
    }

    void setStatus(final Status status) {
        this.status = status;
    }

    // --------------------------------------------------------------------------------------------------------- species

    /**
     * Returns current value of {@value Character_#SPECIES} attribute.
     *
     * @return current value of the {@value Character_#SPECIES} attribute.
     */
    public Species getSpecies() {
        return species;
    }

    void setSpecies(final Species species) {
        this.species = species;
    }

    // ------------------------------------------------------------------------------------------------------------ type
    @Nullable
    public Type getType() {
        return type;
    }

    void setType(@Nullable final Type type) {
        this.type = type;
    }

    // ---------------------------------------------------------------------------------------------------------- gender
    public Gender getGender() {
        return gender;
    }

    void setGender(final Gender gender) {
        this.gender = gender;
    }

    // ---------------------------------------------------------------------------------------------------------- origin

    /**
     * Returns the name and url of this character's origin location.
     *
     * @return the name and url of this character's origin location
     * @see #getOrigin_()
     */
    public NameAndUrl getOrigin() {
        return origin;
    }

    void setOrigin(final NameAndUrl origin) {
        this.origin = origin;
    }

    // -------------------------------------------------------------------------------------------------------- location

    /**
     * Returns the name and url of this character's last known location.
     *
     * @return the name and url of this character's last known location.
     * @see #getLocation_()
     */
    public NameAndUrl getLocation() {
        return location;
    }

    void setLocation(final NameAndUrl location) {
        this.location = location;
    }

    // ----------------------------------------------------------------------------------------------------------- image
    public String getImage() {
        return image;
    }

    void setImage(final String image) {
        this.image = image;
    }

    // --------------------------------------------------------------------------------------------------------- episode

    /**
     * Returns a list of episodes' links in which this character appeared.
     *
     * @return a list of episodes' links in which this character appeared.
     * @see #getEpisodes_()
     */
    public List<URL> getEpisode() {
        return episode;
    }

    void setEpisode(final List<URL> episode) {
        this.episode = episode;
    }

    // ------------------------------------------------------------------------------------------------------------- url
    public URL getUrl() {
        return url;
    }

    void setUrl(final URL url) {
        this.url = url;
    }

    // --------------------------------------------------------------------------------------------------------- created
    public Instant getCreated() {
        return created;
    }

    void setCreated(final Instant created) {
        this.created = created;
    }

    // --------------------------------------------------------------------------------------------------------- origin_

    /**
     * Returns this character's origin location.
     *
     * @return this character's origin location; may be {@code null}.
     * @see #getOrigin()
     */
    @Nullable
    public Location getOrigin_() {
        return origin_;
    }

    void setOrigin_(@Nullable final Location originLocation_) {
        this.origin_ = originLocation_;
    }

    // ------------------------------------------------------------------------------------------------------- location_

    /**
     * Returns this character's last known location.
     *
     * @return this character's last known location; may be {@code null}.
     * @see #getLocation()
     */
    @Nullable
    public Location getLocation_() {
        return location_;
    }

    void setLocation_(@Nullable final Location locationLocation_) {
        this.location_ = locationLocation_;
    }

    // ------------------------------------------------------------------------------------------------------- episodes_

    /**
     * Returns a list of mapped episodes in which this character appeared.
     *
     * @return a list of mapped episodes in which this character appeared.
     * @see #getEpisode()
     */
    public List<Episode> getEpisodes_() {
        return episodes_;
    }

    void setEpisodes_(final List<Episode> episodes_) {
        this.episodes_ = episodes_;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ID,
            nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false)
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

    @NotNull
//    @Convert(converter = StatusConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Status status;

    @NotNull
//    @Convert(converter = SpeciesConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SPECIES,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Species species;

    @Nullable
//    @Convert(converter = TypeConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TYPE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Type type;

    @NotNull
//    @Convert(converter = GenderConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_GENDER,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Gender gender;

    @Valid
    @NotNull
    @Embedded
    @AttributeOverride(name = NameAndUrl.ATTRIBUTE_NAME_NAME,
                       column = @Column(name = COLUMN_NAME_ORIGIN_NAME,
                                        nullable = false,
                                        insertable = false,
                                        updatable = false
                       )
    )
    @AttributeOverride(name = NameAndUrl.ATTRIBUTE_NAME_URL,
                       column = @Column(name = COLUMN_NAME_ORIGIN_URL,
                                        nullable = false,
                                        insertable = false,
                                        updatable = false
                       )
    )
    private NameAndUrl origin;

    @Valid
    @NotNull
    @Embedded
    @AttributeOverride(name = NameAndUrl.ATTRIBUTE_NAME_NAME,
                       column = @Column(name = COLUMN_NAME_LOCATION_NAME,
                                        nullable = false,
                                        insertable = false,
                                        updatable = false
                       )
    )
    @AttributeOverride(name = NameAndUrl.ATTRIBUTE_NAME_URL,
                       column = @Column(name = COLUMN_NAME_LOCATION_URL,
                                        nullable = false,
                                        insertable = false,
                                        updatable = false
                       )
    )
    private NameAndUrl location;

    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IMAGE, nullable = false, insertable = false, updatable = false)
    private String image;

    @NotNull
    @Convert(converter = UrlListConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EPISODE, nullable = false, insertable = false, updatable = false)
    private List<@NotNull URL> episode;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = UrlConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_URL, nullable = false, insertable = false, updatable = false)
    private URL url;

    @Past
    @NotNull
    @Convert(converter = InstantConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CREATED, nullable = false, insertable = false, updatable = false)
    private Instant created;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ORIGIN_ID_,
                nullable = true,
                insertable = false,
                updatable = false
    )
    private Location origin_;

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID_,
                nullable = true,
                insertable = false,
                updatable = false
    )
    private Location location_;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {
                }
    )
    @JoinTable(name = CharacterEpisode.TABLE_NAME,
               joinColumns = {
                       @JoinColumn(name = CharacterEpisode.COLUMN_NAME_CHARACTER_ID)
               },
               inverseJoinColumns = {
                       @JoinColumn(name = CharacterEpisode.COLUMN_NAME_EPISODE_ID)
               }
    )
    private List<@Valid @NotNull Episode> episodes_;
}
