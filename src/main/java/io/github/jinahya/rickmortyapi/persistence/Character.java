package io.github.jinahya.rickmortyapi.persistence;

import io.github.jinahya.rickmortyapi.persistence.converter.InstantConverter;
import io.github.jinahya.rickmortyapi.persistence.converter.UrlConverter;
import io.github.jinahya.rickmortyapi.persistence.converter.UrlListConverter;
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

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Character_#ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ID = "id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAME = "name";

    // -----------------------------------------------------------------------------------------------------------------
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

        private final String columnValue;
    }

    @Converter
    public static class StatusConverter implements AttributeConverter<Status, String> {

        @Override
        public String convertToDatabaseColumn(final Status attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.columnValue;
        }

        @Override
        public Status convertToEntityAttribute(final String dbData) {
            if (dbData == null) {
                return null;
            }
            return Status.valueOfColumnValue(dbData);
        }
    }

    // ----------------------------------------------------------------------------------------------------------------- species

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

        private Species(String columnValue) {
            this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
        }

        private final String columnValue;
    }

    @Converter
    public static class SpeciesConverter implements AttributeConverter<Species, String> {

        @Override
        public String convertToDatabaseColumn(final Species attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.columnValue;
        }

        @Override
        public Species convertToEntityAttribute(final String dbData) {
            if (dbData == null) {
                return null;
            }
            return Species.valueOfColumnValue(dbData);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_TYPE = "type";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_GENDER = "gender";

    /**
     * A column value of {@value} for the {@link #COLUMN_NAME_GENDER} column.
     */
    public static final String COLUMN_VALUE_GENDER_FEMALE = "Female";

    public static final String COLUMN_VALUE_GENDER_GENDERLESS = "Genderless";

    public static final String COLUMN_VALUE_GENDER_MALE = "Male";

    public static final String COLUMN_VALUE_GENDER_UNKNOWN = "unknown";

    public enum Gender {

        GENDER_FEMALE(COLUMN_VALUE_GENDER_FEMALE),

        GENDER_GENDERLESS(COLUMN_VALUE_GENDER_GENDERLESS),

        GENDER_MALE(COLUMN_VALUE_GENDER_MALE),

        GENDER_UNKNOWN(COLUMN_VALUE_GENDER_UNKNOWN);

        public static Gender valueOfColumnValue(final String columnValue) {
            for (final var value : values()) {
                if (value.columnValue.equals(columnValue)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("no value for column value: " + columnValue);
        }

        private Gender(String columnValue) {
            this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
        }

        private final String columnValue;
    }

    @Converter
    public static class GenderConverter implements AttributeConverter<Gender, String> {

        @Override
        public String convertToDatabaseColumn(final Gender attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.columnValue;
        }

        @Override
        public Gender convertToEntityAttribute(final String dbData) {
            if (dbData == null) {
                return null;
            }
            return Gender.valueOfColumnValue(dbData);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static final String PROPERTY_PATH_ORIGIN_NAME = "$.origin.name";

    public static final String COLUMN_NAME_ORIGIN_NAME = "origin_name";

    // -----------------------------------------------------------------------------------------------------------------
    static final String PROPERTY_PATH_ORIGIN_URL = "$.origin.url";

    public static final String COLUMN_NAME_ORIGIN_URL = "origin_url";

    // -----------------------------------------------------------------------------------------------------------------
    static final String PROPERTY_PATH_LOCATION_NAME = "$.location.name";

    public static final String COLUMN_NAME_LOCATION_NAME = "location_name";

    // -----------------------------------------------------------------------------------------------------------------
    static final String PROPERTY_PATH_LOCATION_URL = "$.location.url";

    public static final String COLUMN_NAME_LOCATION_URL = "location_url";

    // -----------------------------------------------------------------------------------------------------------------
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

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    static Character of(final int id) {
        final var character = new Character();
        character.setId(id);
        return character;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Character() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
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
    public Species getSpecies() {
        return species;
    }

    void setSpecies(final Species species) {
        this.species = species;
    }

    // ------------------------------------------------------------------------------------------------------------ type
    @Nullable
    public String getType() {
        return type;
    }

    void setType(@Nullable final String type) {
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
    @Convert(converter = StatusConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Status status;

    @NotNull
    @Convert(converter = SpeciesConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SPECIES,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Species species;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TYPE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private String type;

    @NotNull
    @Convert(converter = GenderConverter.class)
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
