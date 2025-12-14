package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@IdClass(CharacterEpisodeId.class)
@Entity
@Table(name = CharacterEpisode.TABLE_NAME)
public class CharacterEpisode extends _BaseEntity {

    public static final String TABLE_NAME = "character_episode";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CHARACTER_ID = "character_id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_EPISODE_ID = "episode_id";

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected CharacterEpisode() {
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
        if (!(obj instanceof CharacterEpisode that)) {
            return false;
        }
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

    public Character getCharacter() {
        return character;
    }

    void setCharacter(final Character character) {
        this.character = character;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Integer getEpisodeId() {
        return episodeId;
    }

    void setEpisodeId(final Integer episodeId) {
        this.episodeId = episodeId;
    }

    public Episode getEpisode() {
        return episode;
    }

    void setEpisode(final Episode episode) {
        this.episode = episode;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHARACTER_ID, nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false)
    private Integer characterId;

    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CHARACTER_ID, nullable = false, insertable = false, updatable = false)
    private Character character;

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EPISODE_ID, nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false)
    private Integer episodeId;

    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_EPISODE_ID, nullable = false, insertable = false, updatable = false)
    private Episode episode;
}
