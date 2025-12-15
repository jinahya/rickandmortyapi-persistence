package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = EpisodeCharacter.TABLE_NAME)
public class EpisodeCharacter extends _BaseEntity {

    public static final String TABLE_NAME = "episode_character";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_EPISODE_ID = "episode_id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CHARACTER_ID = "character_id";

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected EpisodeCharacter() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EpisodeCharacter that = (EpisodeCharacter) obj;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // -------------------------------------------------------------------------------------------------------------- id
    public EpisodeCharacterId getId() {
        return id;
    }

    void setId(final EpisodeCharacterId id) {
        this.id = id;
    }

    // --------------------------------------------------------------------------------------------------------- episode
    public Episode getEpisode() {
        return episode;
    }

    void setEpisode(final Episode episode) {
        this.episode = episode;
    }

    // ------------------------------------------------------------------------------------------------------- character
    public Character getCharacter() {
        return character;
    }

    void setCharacter(final Character character) {
        this.character = character;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private EpisodeCharacterId id;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_EPISODE_ID, nullable = false, insertable = false, updatable = false)
    private Episode episode;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CHARACTER_ID, nullable = false, insertable = false, updatable = false)
    private Character character;
}
