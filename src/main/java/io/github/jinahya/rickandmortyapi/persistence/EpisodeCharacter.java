package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping {@value EpisodeCharacter#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see EpisodeCharacterId
 */
@Entity
@Table(name = EpisodeCharacter.TABLE_NAME)
public class EpisodeCharacter
        extends _BaseEntity<EpisodeCharacterId> {

    /**
     * The name of the database table to which this entity is mapped. The value is {@value}.
     */
    public static final String TABLE_NAME = "episode_character";

    // ------------------------------------------------------------------------------------------------------ episode_id

    /**
     * The name of the column to which the {@value EpisodeCharacterId_#EPISODE_ID} attribute maps.
     */
    public static final String COLUMN_NAME_EPISODE_ID = "episode_id";

    // ---------------------------------------------------------------------------------------------------- character_id

    /**
     * The name of the column to which the {@value EpisodeCharacterId_#CHARACTER_ID} attribute maps.
     */
    public static final String COLUMN_NAME_CHARACTER_ID = "character_id";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
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
        if (!(obj instanceof EpisodeCharacter that)) {
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
     * Returns current value of {@value EpisodeCharacter_#ID} attribute.
     *
     * @return current value of the {@value EpisodeCharacter_#ID} attribute.
     */
    public EpisodeCharacterId getId() {
        return id;
    }

    void setId(final EpisodeCharacterId id) {
        this.id = id;
    }

    EpisodeCharacter id(final EpisodeCharacterId id) {
        setId(id);
        return this;
    }

    // --------------------------------------------------------------------------------------------------------- episode

    /**
     * Returns current value of {@value EpisodeCharacter_#EPISODE} attribute.
     *
     * @return current value of the {@value EpisodeCharacter_#EPISODE} attribute.
     */
    public Episode getEpisode() {
        return episode;
    }

    void setEpisode(final Episode episode) {
        this.episode = episode;
        Optional.ofNullable(getId())
                .orElseGet(() -> id(new EpisodeCharacterId()).getId())
                .setEpisodeId(
                        Optional.ofNullable(this.episode)
                                .map(Episode::getId)
                                .orElse(null)
                );
    }

    // ------------------------------------------------------------------------------------------------------- character

    /**
     * Returns current value of {@value EpisodeCharacter_#CHARACTER} attribute.
     *
     * @return current value of the {@value EpisodeCharacter_#CHARACTER} attribute.
     */
    public Character getCharacter() {
        return character;
    }

    void setCharacter(final Character character) {
        this.character = character;
        Optional.ofNullable(getId())
                .orElseGet(() -> id(new EpisodeCharacterId()).getId())
                .setCharacterId(
                        Optional.ofNullable(this.character)
                                .map(Character::getId)
                                .orElse(null)
                );
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
    @JoinColumn(name = COLUMN_NAME_EPISODE_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Episode episode;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CHARACTER_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Character character;
}
