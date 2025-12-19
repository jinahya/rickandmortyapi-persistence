package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

/**
 * The primary key class for {@link EpisodeCharacter} entity class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class EpisodeCharacterId {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_EPISODE_ID = "episode_id";

    public static final String ATTRIBUTE_NAME_EPISODE_ID = "episodeId";

    public static final String ATTRIBUTE_NAME_EPISODE = "episode";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CHARACTER_ID = "character_id";

    public static final String ATTRIBUTE_NAME_CHARACTER_ID = "characterId";

    public static final String ATTRIBUTE_NAME_CHARACTER = "character";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with specified values.
     *
     * @param episodeId   a value for the {@value #ATTRIBUTE_NAME_EPISODE_ID} attribute.
     * @param characterId a value for the {@value #ATTRIBUTE_NAME_CHARACTER_ID} attribute.
     * @return a new instance of {@code characterId} and {@code episodeId}
     */
    public static EpisodeCharacterId of(final Integer episodeId, final Integer characterId) {
        return new EpisodeCharacterId()
                .episodeId(episodeId)
                .characterId(characterId)
                ;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected EpisodeCharacterId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + episodeId +
               ",name=" + characterId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof EpisodeCharacterId that)) return false;
        return Objects.equals(episodeId, that.episodeId) &&
               Objects.equals(characterId, that.characterId);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(episodeId, characterId);
    }

    // ------------------------------------------------------------------------------------------------------- episodeId
    public Integer getEpisodeId() {
        return episodeId;
    }

    void setEpisodeId(final Integer episodeId) {
        this.episodeId = episodeId;
    }

    EpisodeCharacterId episodeId(final Integer episodeId) {
        setEpisodeId(episodeId);
        return this;
    }

    // ----------------------------------------------------------------------------------------------------- characterId
    public Integer getCharacterId() {
        return characterId;
    }

    void setCharacterId(final Integer characterId) {
        this.characterId = characterId;
    }

    EpisodeCharacterId characterId(final Integer characterId) {
        setCharacterId(characterId);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EPISODE_ID, nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false
    )
    private Integer episodeId;

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHARACTER_ID,
            nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false
    )
    private Integer characterId;
}
