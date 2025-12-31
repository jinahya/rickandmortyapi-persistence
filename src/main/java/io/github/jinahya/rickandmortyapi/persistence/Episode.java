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

import io.github.jinahya.rickandmortyapi.persistence.converter.InstantStringConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.LocalDateStringConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlListStringConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlStringConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
import java.util.Optional;

/**
 * An entity class for mapping {@value Episode#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see EpisodeCharacter
 */
@NamedQuery(name = "Episode.SelectList__OrderByAirDateIso_Asc",
            query = """
                    SELECT e
                    FROM Episode e
                    ORDER BY e.airDateIso_ ASC"""
)
@NamedQuery(name = "Episode.SelectList__OrderByEpisodeAsc",
            query = """
                    SELECT e
                    FROM Episode e
                    ORDER BY e.episode ASC"""
)
@NamedQuery(name = "Episode.SelectList__OrderByIdAsc",
            query = """
                    SELECT e
                    FROM Episode e
                    ORDER BY e.id ASC"""
)
@NamedQuery(name = "Episode.SelectSingle_WhereEpisodeEqual_",
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
public class Episode
        extends _BaseEntity<Integer> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "episode";

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_ID} attribute maps. The value is {@value}.
     *
     * @see Episode_#ID
     */
    public static final String COLUMN_NAME_ID = "id";

    /**
     * The name of the entity attribute from which the {@value COLUMN_NAME_ID} column maps. The value is {@value}.
     *
     * @see Episode_#ID
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * The name of the table column to which the {@value Episode_#NAME} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "name";

    // -------------------------------------------------------------------------------------------------------- air_date

    /**
     * The name of the table column to which the {@value Episode_#AIR_DATE} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_AIR_DATE = "air_date";

    // --------------------------------------------------------------------------------------------------------- episode

    /**
     * The name of the table column to which the {@value Episode_#EPISODE} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_EPISODE = "episode";

    static final String REGEXP_EPISODE_GROUP_NAME_SEASON_NUMBER = "seasonNumber";

    static final String REGEXP_EPISODE_GROUP_NAME_EPISODE_NUMBER = "episodeNumber";

    static final String REGEXP_EPISODE = "S(?<%s>\\d{2})E(?<%s>\\d{2})".formatted(
            REGEXP_EPISODE_GROUP_NAME_SEASON_NUMBER,
            REGEXP_EPISODE_GROUP_NAME_EPISODE_NUMBER
    );

    static final java.util.regex.Pattern PATTERN_EPISODE = java.util.regex.Pattern.compile(REGEXP_EPISODE);

    static final String FORMAT_EPISODE = "S%02dE%02d";

    private static final int MIN_VALUE_SEASON_NUMBER = 1;

    private static final int MAX_VALUE_SEASON_NUMBER = 99;

    private static final int MIN_VALUE_EPISODE_NUMBER = MIN_VALUE_SEASON_NUMBER;

    private static final int MAX_VALUE_EPISODE_NUMBER = MAX_VALUE_SEASON_NUMBER;

    /**
     * Returns a value for the {@value Episode_#EPISODE} attribute from the specified season and episode numbers.
     *
     * @param seasonNumber  the season number between {@value #MIN_VALUE_SEASON_NUMBER} and
     *                      {@value #MAX_VALUE_SEASON_NUMBER}.
     * @param episodeNumber the episode number between {@value #MIN_VALUE_EPISODE_NUMBER} and
     *                      {@value #MAX_VALUE_EPISODE_NUMBER}.
     * @return an episode string.
     */
    public static String episodeOf(final int seasonNumber, final int episodeNumber) {
        if (seasonNumber < MIN_VALUE_SEASON_NUMBER || seasonNumber > MAX_VALUE_SEASON_NUMBER) {
            throw new IllegalArgumentException("invalid seasonNumber: " + seasonNumber);
        }
        if (episodeNumber < MIN_VALUE_EPISODE_NUMBER || episodeNumber > MAX_VALUE_EPISODE_NUMBER) {
            throw new IllegalArgumentException("invalid episodeNumber: " + episodeNumber);
        }
        return String.format(FORMAT_EPISODE, seasonNumber, episodeNumber);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Episode_#CHARACTERS} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHARACTERS = "characters";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Episode_#URL} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_URL = "url";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Episode_#CREATED} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED = "created";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Episode_#AIR_DATE_ISO_} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_AIR_DATE_ISO_ = "air_date_iso_";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A comparator compares with {@link Episode_#ID} attributes.
     */
    public static final Comparator<Episode> COMPARING_ID = Comparator.comparing(Episode::getId);

    /**
     * A comparator compares with {@link Episode_#EPISODE} attributes.
     */
    public static final Comparator<Episode> COMPARING_EPISODE = Comparator.comparing(Episode::getEpisode);

    /**
     * A comparator compares with {@link Episode_#AIR_DATE_ISO_} attributes.
     */
    public static final Comparator<Episode> COMPARING_AIR_DATE_ISO_ = Comparator.comparing(Episode::getAirDateIso_);

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Episode() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
               ",name=" + name +
               ",airDate=" + airDate +
               ",episode=" + episode +
               ",characters=" + characters +
               ",url=" + url +
               ",created=" + created +
               ",airDateIso_=" + airDateIso_ +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Episode that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence
    @PrePersist
    private void doOnPrePersist() {
        resetEpisodeRelatedValues();
    }

    @PostPersist
    private void doOnPostPersist() {
    }

    @PostLoad
    private void doOnPostLoad() {
        resetEpisodeRelatedValues();
    }

    @PreUpdate
    private void doOnPreUpdate() {
        resetEpisodeRelatedValues();
    }

    @PostUpdate
    private void doOnPostUpdate() {
    }

    @PreRemove
    private void doOnPreRemove() {
    }

    @PostRemove
    private void doOnPostRemove() {
    }

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@value Episode_#ID} attribute.
     *
     * @return current value of the {@value Episode_#ID} attribute.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Replaces current value of {@value Episode_#ID} attribute with the specified value.
     *
     * @param id new value for the {@value Episode_#ID} attribute.
     */
    void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Replaces current value of {@value Episode_#ID} attribute with the specified value.
     *
     * @param id new value for the {@value Episode_#ID} attribute.
     * @return this instance.
     */
    Episode id(final Integer id) {
        setId(id);
        return this;
    }

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * Returns current value of {@value Episode_#NAME} attribute.
     *
     * @return current value of the {@value Episode_#NAME} attribute.
     */
    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    // --------------------------------------------------------------------------------------------------------- airDate

    /**
     * Returns current value of {@value Episode_#AIR_DATE} attribute.
     *
     * @return current value of the {@value Episode_#AIR_DATE} attribute.
     */
    public LocalDate getAirDate() {
        return airDate;
    }

    void setAirDate(final LocalDate airDate) {
        this.airDate = airDate;
    }

    // --------------------------------------------------------------------------------------------------------- episode

    /**
     * Returns current value of {@value Episode_#EPISODE} attribute.
     *
     * @return current value of the {@value Episode_#EPISODE} attribute.
     */
    public String getEpisode() {
        return episode;
    }

    void setEpisode(final String episode) {
        this.episode = episode;
        resetEpisodeRelatedValues();
    }

    private void resetEpisodeRelatedValues() {
        seasonNumber_ = null;
        episodeNumber_ = null;
    }

    /**
     * Returns the season number parsed from current value of {@value Episode_#EPISODE} attribute.
     *
     * @return the season number between {@value #MIN_VALUE_SEASON_NUMBER}, and {@value #MAX_VALUE_SEASON_NUMBER};
     *         {@code null} if the current value of the {@value Episode_#EPISODE} attribute is {@code null}.
     */
    @Transient
    public Integer getSeasonNumber_() {
        var result = seasonNumber_;
        if (result == null) {
            result = seasonNumber_ = Optional.ofNullable(getEpisode())
                    .map(v -> {
                        final var matcher = PATTERN_EPISODE.matcher(v);
                        if (!matcher.matches()) {
                            throw new IllegalStateException("invalid episode: " + v);
                        }
                        return matcher.group(REGEXP_EPISODE_GROUP_NAME_SEASON_NUMBER);
                    })
                    .map(Integer::valueOf)
                    .orElse(null);
        }
        return result;
    }

    /**
     * Returns an episode number parsed from current value of {@value Episode_#EPISODE} attribute.
     *
     * @return the episode number between {@value #MIN_VALUE_EPISODE_NUMBER}, and {@value #MAX_VALUE_EPISODE_NUMBER};
     *         {@code null} if the current value of the {@value Episode_#EPISODE} attribute is {@code null}.
     */
    @Transient
    public Integer getEpisodeNumber_() {
        var result = episodeNumber_;
        if (result == null) {
            result = episodeNumber_ = Optional.ofNullable(getEpisode())
                    .map(v -> {
                        final var matcher = PATTERN_EPISODE.matcher(v);
                        if (!matcher.matches()) {
                            throw new IllegalStateException("invalid episode: " + v);
                        }
                        return matcher.group(
                                REGEXP_EPISODE_GROUP_NAME_EPISODE_NUMBER);
                    })
                    .map(Integer::valueOf)
                    .orElse(null);
        }
        return result;
    }

    // ------------------------------------------------------------------------------------------------------ characters

    /**
     * Returns current value of {@value Episode_#CHARACTERS} attribute.
     *
     * @return current value of the {@value Episode_#CHARACTERS} attribute.
     */
    public List<URL> getCharacters() {
        return characters;
    }

    void setCharacters(final List<URL> characters) {
        this.characters = characters;
    }

    // ------------------------------------------------------------------------------------------------------------- url

    /**
     * Returns current value of {@value Episode_#URL} attribute.
     *
     * @return current value of the {@value Episode_#URL} attribute.
     */
    public URL getUrl() {
        return url;
    }

    void setUrl(final URL url) {
        this.url = url;
    }

    // --------------------------------------------------------------------------------------------------------- created

    /**
     * Returns current value of {@value Episode_#CREATED} attribute.
     *
     * @return current value of the {@value Episode_#CREATED} attribute.
     */
    public Instant getCreated() {
        return created;
    }

    void setCreated(final Instant created) {
        this.created = created;
    }

    // ----------------------------------------------------------------------------------------------------- airDateIso_

    /**
     * Returns current value of {@value Episode_#AIR_DATE_ISO_} attribute.
     *
     * @return current value of the {@value Episode_#AIR_DATE_ISO_} attribute.
     */
    public LocalDate getAirDateIso_() {
        return airDateIso_;
    }

    void setAirDateIso_(final LocalDate _airDateIso) {
        this.airDateIso_ = _airDateIso;
    }

    // ----------------------------------------------------------------------------------------------------- characters_

    /**
     * Returns current value of {@value Episode_#CHARACTERS_} attribute.
     *
     * @return current value of the {@value Episode_#CHARACTERS_} attribute.
     */
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
    @Column(name = COLUMN_NAME_ID,
            nullable = false,
            insertable = false,
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
    @Convert(converter = Episode_AirDateConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AIR_DATE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDate airDate;

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EPISODE,
            nullable = false,
            insertable = false,
            updatable = false,
            unique = true
    )
    @SuppressWarnings({
            "java:S1700" // A field should not duplicate the name of its containing class
    })
    private String episode;

    @Transient
    private volatile Integer episodeNumber_;

    @Transient
    private volatile Integer seasonNumber_;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = UrlListStringConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHARACTERS,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private List<@NotNull URL> characters;

    @NotNull
    @Convert(converter = UrlStringConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_URL,
            nullable = false,
            insertable = false,
            updatable = false,
            unique = true
    )
    private URL url;

    @Past
    @NotNull
    @Convert(converter = InstantStringConverter.class)
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
    @Convert(converter = LocalDateStringConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AIR_DATE_ISO_,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDate airDateIso_;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToMany(mappedBy = Character_.EPISODES_, // <<-- this side is an inverse(non-owning) side
                fetch = FetchType.LAZY,
                cascade = {
                }
    )
    private List<@Valid @NotNull Character> characters_;
}
