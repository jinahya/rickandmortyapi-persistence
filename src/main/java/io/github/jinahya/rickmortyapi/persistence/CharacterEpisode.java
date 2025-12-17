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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;
import java.util.Optional;

import static jakarta.persistence.FetchType.LAZY;

/**
 * An entity class for mapping {@value CharacterEpisode#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@IdClass(CharacterEpisodeId.class)
@Entity
@Table(name = CharacterEpisode.TABLE_NAME)
public class CharacterEpisode extends __BaseEntity {

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

    // -------------------------------------------------------------------------------------------------------- BUILDERS

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

    // ----------------------------------------------------------------------------------------------------- characterId

    /**
     * Returns current value of {@value CharacterEpisode_#CHARACTER_ID} attribute.
     *
     * @return current value of the {@value CharacterEpisode_#CHARACTER_ID} attribute.
     */
    public Integer getCharacterId() {
        return characterId;
    }

    void setCharacterId(final Integer characterId) {
        this.characterId = characterId;
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
        setCharacterId(
                Optional.ofNullable(this.character)
                        .map(Character::getId)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------- episodeId
    public Integer getEpisodeId() {
        return episodeId;
    }

    void setEpisodeId(final Integer episodeId) {
        this.episodeId = episodeId;
    }

    // --------------------------------------------------------------------------------------------------------- episode
    public Episode getEpisode() {
        return episode;
    }

    void setEpisode(final Episode episode) {
        this.episode = episode;
        setEpisodeId(
                Optional.ofNullable(this.episode)
                        .map(Episode::getId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHARACTER_ID,
            nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false
    )
    private Integer characterId;

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
    @Positive
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EPISODE_ID,
            nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false
    )
    private Integer episodeId;

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
