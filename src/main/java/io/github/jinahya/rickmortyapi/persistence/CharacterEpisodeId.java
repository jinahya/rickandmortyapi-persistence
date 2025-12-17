package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

/**
 * The id class for {@link CharacterEpisode}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class CharacterEpisodeId {

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with specified values.
     *
     * @param characterId a value for the {@value CharacterEpisodeId_#CHARACTER_ID} attribute.
     * @param episodeId   a value for the {@value CharacterEpisodeId_#EPISODE_ID} attribute.
     * @return a new instance of {@code characterId} and {@code episodeId}
     */
    public static CharacterEpisodeId of(final Integer characterId, final Integer episodeId) {
        final var instance = new CharacterEpisodeId();
        instance.setCharacterId(characterId);
        instance.setEpisodeId(episodeId);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected CharacterEpisodeId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + characterId +
               ",name=" + episodeId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof CharacterEpisodeId that)) {
            return false;
        }
        return Objects.equals(characterId, that.characterId)
               && Objects.equals(episodeId, that.episodeId);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(characterId, episodeId);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns current value of {@value CharacterEpisodeId_#CHARACTER_ID} attribute.
     *
     * @return current value of the {@value CharacterEpisodeId_#CHARACTER_ID} attribute.
     */
    public Integer getCharacterId() {
        return characterId;
    }

    void setCharacterId(final Integer characterId) {
        this.characterId = characterId;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns current value of {@value CharacterEpisodeId_#EPISODE_ID} attribute.
     *
     * @return current value of the {@value CharacterEpisodeId_#EPISODE_ID} attribute.
     */
    public Integer getEpisodeId() {
        return episodeId;
    }

    void setEpisodeId(final Integer episodeId) {
        this.episodeId = episodeId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = CharacterEpisode.COLUMN_NAME_CHARACTER_ID, nullable = false, insertable = false, updatable = false)
    private Integer characterId;

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = CharacterEpisode.COLUMN_NAME_EPISODE_ID, nullable = false, insertable = false, updatable = false)
    private Integer episodeId;
}
