package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@Embeddable
public class CharacterEpisodeId {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CHARACTER_ID = "character_id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_EPISODE_ID = "episode_id";

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static CharacterEpisodeId of(final Integer characterId, final Integer episodeId) {
        final var instance = new CharacterEpisodeId();
        instance.setCharacterId(characterId);
        instance.setEpisodeId(episodeId);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
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
        if (!(obj instanceof CharacterEpisodeId that)) return false;
        return Objects.equals(characterId, that.characterId) &&
               Objects.equals(episodeId, that.episodeId);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(characterId, episodeId);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Integer getCharacterId() {
        return characterId;
    }

    void setCharacterId(final Integer characterId) {
        this.characterId = characterId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Integer getEpisodeId() {
        return episodeId;
    }

    void setEpisodeId(final Integer episodeId) {
        this.episodeId = episodeId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHARACTER_ID, nullable = false, insertable = false, updatable = false)
    private Integer characterId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EPISODE_ID, nullable = false, insertable = false, updatable = false)
    private Integer episodeId;
}
