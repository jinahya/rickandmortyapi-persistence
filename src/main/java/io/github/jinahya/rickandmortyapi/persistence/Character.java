package io.github.jinahya.rickandmortyapi.persistence;

import io.github.jinahya.rickandmortyapi.persistence.converter.InstantConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlListConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@NamedQuery(name = "Character.selectList_NameEqual_",
            query = """
                    SELECT c
                    FROM Character c
                    WHERE c.name = :name
                    ORDER BY c.id ASC""")
@NamedQuery(name = "Character.selectList__OrderByIdAsc",
            query = """
                    SELECT c
                    FROM Character c
                    ORDER BY c.id ASC""")
@Entity
@Table(name = Character.TABLE_NAME)
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S115", // Constant names should comply with a naming convention
        "java:S116", // Field names should comply with a naming convention
        "java:S117"  // Local variable and method parameter names should comply with a naming convention
})
public class Character extends _BaseEntity<Integer> {

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

    /**
     * The name of the table column to which the {@value Character_#STATUS} attribute maps. The value is {@value}.
     *
     * @see Character_StatusColumnValues
     * @see Character_Status
     */
    public static final String COLUMN_NAME_STATUS = "status";

    // --------------------------------------------------------------------------------------------------------- species

    /**
     * The name of the table column to which the {@value Character_#SPECIES} attribute maps. The value is {@value}.
     *
     * @see Character_SpeciesColumnValues
     * @see Character_Species
     */
    public static final String COLUMN_NAME_SPECIES = "species";

    // ------------------------------------------------------------------------------------------------------------ type

    /**
     * The name of the table column to which the {@value Character_#TYPE} attribute maps. The value is {@value}.
     *
     * @see Character_TypeColumnValues
     * @see Character_Type
     */
    public static final String COLUMN_NAME_TYPE = "type";

    // ---------------------------------------------------------------------------------------------------------- gender

    /**
     * The name of the table column to which the {@value Character_#GENDER} attribute maps. The value is {@value}.
     *
     * @see Character_GenderColumnValues
     * @see Character_Gender
     */
    public static final String COLUMN_NAME_GENDER = "gender";

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
    public Character_Status getStatus() {
        return status;
    }

    void setStatus(final Character_Status status) {
        this.status = status;
    }

    // --------------------------------------------------------------------------------------------------------- species

    /**
     * Returns current value of {@value Character_#SPECIES} attribute.
     *
     * @return current value of the {@value Character_#SPECIES} attribute.
     */
    public Character_Species getSpecies() {
        return species;
    }

    void setSpecies(final Character_Species species) {
        this.species = species;
    }

    // ------------------------------------------------------------------------------------------------------------ type
    @Nullable
    public Character_Type getType() {
        return type;
    }

    void setType(@Nullable final Character_Type type) {
        this.type = type;
    }

    // ---------------------------------------------------------------------------------------------------------- gender
    public Character_Gender getGender() {
        return gender;
    }

    void setGender(final Character_Gender gender) {
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

    /**
     * Returns current value of {@value Character_#IMAGE} attribute.
     *
     * @return current value of the {@value Character_#IMAGE} attribute.
     */
    public URL getImage() {
        return image;
    }

    void setImage(final URL image) {
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
    private Character_Status status;

    @NotNull
//    @Convert(converter = SpeciesConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SPECIES,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Character_Species species;

    @Nullable
//    @Convert(converter = TypeConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TYPE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Character_Type type;

    @NotNull
//    @Convert(converter = GenderConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_GENDER,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Character_Gender gender;

    @Nullable
    @Valid
    @Embedded
    @AttributeOverride(name = NameAndUrl.ATTRIBUTE_NAME_NAME,
                       column = @Column(name = COLUMN_NAME_ORIGIN_NAME,
                                        nullable = true,
                                        insertable = false,
                                        updatable = false
                       )
    )
    @AttributeOverride(name = NameAndUrl.ATTRIBUTE_NAME_URL,
                       column = @Column(name = COLUMN_NAME_ORIGIN_URL,
                                        nullable = true,
                                        insertable = false,
                                        updatable = false
                       )
    )
    private NameAndUrl origin;

    @Nullable
    @Valid
    @Embedded
    @AttributeOverride(name = NameAndUrl.ATTRIBUTE_NAME_NAME,
                       column = @Column(name = COLUMN_NAME_LOCATION_NAME,
                                        nullable = true,
                                        insertable = false,
                                        updatable = false
                       )
    )
    @AttributeOverride(name = NameAndUrl.ATTRIBUTE_NAME_URL,
                       column = @Column(name = COLUMN_NAME_LOCATION_URL,
                                        nullable = true,
                                        insertable = false,
                                        updatable = false
                       )
    )
    private NameAndUrl location;

    @NotNull
    @Convert(converter = UrlConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IMAGE, nullable = false, insertable = false, updatable = false, unique = true)
    private URL image;

    @NotNull
    @Convert(converter = UrlListConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EPISODE, nullable = false, insertable = false, updatable = false)
    private List<@NotNull URL> episode;

    @NotNull
    @Convert(converter = UrlConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_URL, nullable = false, insertable = false, updatable = false, unique = true)
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
                referencedColumnName = Location.COLUMN_NAME_ID,
                nullable = true,
                insertable = false,
                updatable = false
    )
    private Location origin_;

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID_,
                referencedColumnName = Location.COLUMN_NAME_ID,
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
