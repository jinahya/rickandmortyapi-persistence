package io.github.jinahya.rickmortyapi.persistence;

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

@Entity
@Table(name = Character.TABLE_NAME)
public class Character extends _BaseEntity {

    public static final String TABLE_NAME = "character";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_ID = "id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAME = "name";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_STATUS = "status";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_SPECIES = "species";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_TYPE = "type";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_GENDER = "gender";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_ORIGIN_NAME = "origin_name";

    public static final String COLUMN_NAME_ORIGIN_URL = "origin_url";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_LOCATION_NAME = "location_name";

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

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_LOCATION_ID_ = "location_id_";

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
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
        if (!(obj instanceof Character character)) {
            return false;
        }
        return Objects.equals(getId(), character.getId());
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

    // ------------------------------------------------------------------------------------------------------------ name
    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    // ---------------------------------------------------------------------------------------------------------- status
    public String getStatus() {
        return status;
    }

    void setStatus(final String status) {
        this.status = status;
    }

    // --------------------------------------------------------------------------------------------------------- species
    public String getSpecies() {
        return species;
    }

    void setSpecies(final String species) {
        this.species = species;
    }

    // ------------------------------------------------------------------------------------------------------------ type
    @Nullable
    public String getType() {
        return type;
    }

    void setType(@Nullable String type) {
        this.type = type;
    }

    // ---------------------------------------------------------------------------------------------------------- gender
    public String getGender() {
        return gender;
    }

    void setGender(final String gender) {
        this.gender = gender;
    }

    // ---------------------------------------------------------------------------------------------------------- origin
    public NameAndUrl getOrigin() {
        return origin;
    }

    void setOrigin(final NameAndUrl origin) {
        this.origin = origin;
    }

    // -------------------------------------------------------------------------------------------------------- location
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
    @Nullable
    public Location getOrigin_() {
        return origin_;
    }

    void setOrigin_(@Nullable final Location originLocation_) {
        this.origin_ = originLocation_;
    }

    // ------------------------------------------------------------------------------------------------------- location_
    @Nullable
    public Location getLocation_() {
        return location_;
    }

    void setLocation_(@Nullable final Location locationLocation_) {
        this.location_ = locationLocation_;
    }

    // -------------------------------------------------------------------------------------------------------- episodes
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
    @Column(name = COLUMN_NAME_ID, nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false)
    private Integer id;

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = false, updatable = false)
    private String name;

    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS, nullable = false, insertable = false, updatable = false)
    private String status;

    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SPECIES, nullable = false, insertable = false, updatable = false)
    private String species;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TYPE, nullable = true, insertable = false, updatable = false)
    private String type;

    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_GENDER, nullable = false, insertable = false, updatable = false)
    private String gender;

    @Valid
    @NotNull
    @Embedded
    @AttributeOverride(
            name = NameAndUrl.ATTRIBUTE_NAME_NAME,
            column = @Column(name = COLUMN_NAME_ORIGIN_NAME,
                             nullable = false,
                             insertable = false,
                             updatable = false
            )
    )
    @AttributeOverride(
            name = NameAndUrl.ATTRIBUTE_NAME_URL,
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
    @AttributeOverride(
            name = NameAndUrl.ATTRIBUTE_NAME_NAME,
            column = @Column(name = COLUMN_NAME_LOCATION_NAME,
                             nullable = false,
                             insertable = false,
                             updatable = false
            )
    )
    @AttributeOverride(
            name = NameAndUrl.ATTRIBUTE_NAME_URL,
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
    @Convert(converter = _UrlListConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EPISODE, nullable = false, insertable = false, updatable = false)
    private List<@NotNull URL> episode;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = _UrlConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_URL, nullable = false, insertable = false, updatable = false)
    private URL url;

    @Past
    @NotNull
    @Convert(converter = _InstantConverter.class)
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
