package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@NamedQuery(name = "Episode.SelectList__OrderByAirDateIso_Asc",
            query = """
                    SELECT e
                    FROM Episode e
                    ORDER BY e.airDateIso_ ASC"""
)
@NamedQuery(name = "Episode.SelectList__OrderByIdAsc",
            query = """
                    SELECT e
                    FROM Episode e
                    ORDER BY e.id ASC"""
)
@NamedQuery(name = "Episode.SelectOne_WhereEpisodeEqual_",
            query = """
                    SELECT e
                    FROM Episode e
                    WHERE e.episode = :episode"""
)
@Entity
@Table(name = Episode.TABLE_NAME)
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S115", // Constant names should comply with a naming convention
        "java:S116", // Field names should comply with a naming convention
        "java:S117"  // Local variable and method parameter names should comply with a naming convention
})
public class Episode extends _BaseEntity {

    public static final String TABLE_NAME = "episode";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_ID = "id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAME = "name";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_AIR_DATE = "air_date";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_EPISODE = "episode";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CHARACTERS = "characters";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_URL = "url";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CREATED = "created";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_AIR_DATE_ISO_ = "air_date_iso_";

    // -----------------------------------------------------------------------------------------------------------------
    public static final Comparator<Episode> COMPARING_ID = Comparator.comparing(Episode::getId);

    public static final Comparator<Episode> COMPARING_AIR_DATE_ISO_ = Comparator.comparing(Episode::getAirDateIso_);

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Episode() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
               ",name=" + name +
               ",status=" + airDate +
               ",species=" + episode +
               ",characters=" + characters +
               ",url=" + url +
               ",created=" + created +
               ",airDateIso_=" + airDateIso_ +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Episode character)) {
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

    // --------------------------------------------------------------------------------------------------------- airDate
    public LocalDate getAirDate() {
        return airDate;
    }

    void setAirDate(final LocalDate status) {
        this.airDate = status;
    }

    // --------------------------------------------------------------------------------------------------------- episode
    public String getEpisode() {
        return episode;
    }

    void setEpisode(final String episode) {
        this.episode = episode;
    }

    // ------------------------------------------------------------------------------------------------------ characters
    public List<URL> getCharacters() {
        return characters;
    }

    void setCharacters(final List<URL> characters) {
        this.characters = characters;
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

    // ----------------------------------------------------------------------------------------------------- airDateIso_
    public LocalDate getAirDateIso_() {
        return airDateIso_;
    }

    void setAirDateIso_(final LocalDate _airDateIso) {
        this.airDateIso_ = _airDateIso;
    }

    // ----------------------------------------------------------------------------------------------------- characters_
    public List<Character> getCharacters_() {
        return characters_;
    }

    void setCharacters_(final List<Character> characters_) {
        this.characters_ = characters_;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Id
    @Basic(optional = false)
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

    @Past
    @NotNull
    @Convert(converter = _DateConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AIR_DATE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDate airDate;

    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EPISODE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    @SuppressWarnings({
            "java:S1700" // A field should not duplicate the name of its containing class
    })
    private String episode;

    @NotNull
    @Convert(converter = _UrlListConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHARACTERS,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private List<@NotNull URL> characters;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = _UrlConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_URL,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private URL url;

    @Past
    @NotNull
    @Convert(converter = _InstantConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CREATED,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Instant created;

    // -----------------------------------------------------------------------------------------------------------------
    @Past
    @NotNull
    @Convert(converter = _LocalDateConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AIR_DATE_ISO_,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDate airDateIso_;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToMany(mappedBy = Character_.EPISODES_,
                fetch = FetchType.LAZY
    )
    private List<@Valid @NotNull Character> characters_; // List of characters who have been seen in the episode.
}
