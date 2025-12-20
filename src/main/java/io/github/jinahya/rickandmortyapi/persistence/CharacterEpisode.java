package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;

import static jakarta.persistence.FetchType.LAZY;

/**
 * An entity class for mapping {@value CharacterEpisode#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = CharacterEpisode.TABLE_NAME)
public class CharacterEpisode
        extends _BaseEntity<CharacterEpisodeId> {

    /**
     * The name of the database table to which this entity is mapped. The value is {@value}.
     */
    public static final String TABLE_NAME = "character_episode";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value CharacterEpisode_#CHARACTER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CHARACTER_ID = "character_id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_EPISODE_ID = "episode_id";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected CharacterEpisode() {
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
    public boolean equals(final Object obj) {
        if (!(obj instanceof CharacterEpisode that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    // -------------------------------------------------------------------------------------------------------------- id
    public CharacterEpisodeId getId() {
        return id;
    }

    void setId(final CharacterEpisodeId id) {
        this.id = id;
    }

    CharacterEpisode id(final CharacterEpisodeId id) {
        setId(id);
        return this;
    }

    // ------------------------------------------------------------------------------------------------------- character

    /**
     * Returns current value of {@value CharacterEpisode_#CHARACTER} attribute.
     *
     * @return current value of the {@value CharacterEpisode_#CHARACTER} attribute.
     */
    public Character getCharacter() {
        return character;
    }

    void setCharacter(final Character character) {
        this.character = character;
        Optional.ofNullable(getId())
                .orElseGet(() -> id(new CharacterEpisodeId()).getId())
                .setCharacterId(
                        Optional.ofNullable(this.character)
                                .map(Character::getId)
                                .orElse(null)
                );
    }

    // --------------------------------------------------------------------------------------------------------- episode
    public Episode getEpisode() {
        return episode;
    }

    void setEpisode(final Episode episode) {
        this.episode = episode;
        Optional.ofNullable(getId())
                .orElseGet(() -> id(new CharacterEpisodeId()).getId())
                .setEpisodeId(
                        Optional.ofNullable(this.episode)
                                .map(Episode::getId)
                                .orElse(null)
                );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private CharacterEpisodeId id;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = COLUMN_NAME_CHARACTER_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Character character;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = COLUMN_NAME_EPISODE_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Episode episode;
}
