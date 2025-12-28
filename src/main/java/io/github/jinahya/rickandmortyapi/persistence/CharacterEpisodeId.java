package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * The id class for the {@link CharacterEpisode} entity.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class CharacterEpisodeId
        extends __Base
        implements Serializable, // just for the Spring Data REST
                   Comparable<CharacterEpisodeId> {

    @Serial
    private static final long serialVersionUID = -8363987227830143686L;

    // ----------------------------------------------------------------------------------------------------- COMPARATORS

    /**
     * A comparator compares with {@link CharacterEpisodeId_#CHARACTER_ID} attribute.
     */
    public static final Comparator<CharacterEpisodeId> COMPARING_CHARACTER_ID =
            Comparator.comparing(CharacterEpisodeId::getCharacterId);

    /**
     * A comparator compares with {@link CharacterEpisodeId_#EPISODE_ID} attribute.
     */
    public static final Comparator<CharacterEpisodeId> COMPARING_EPISODE_ID =
            Comparator.comparing(CharacterEpisodeId::getEpisodeId);

    private static final Comparator<CharacterEpisodeId> COMPARATOR =
            COMPARING_CHARACTER_ID.thenComparing(COMPARING_EPISODE_ID);

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with specified values.
     *
     * @param characterId a value for the {@value CharacterEpisodeId_#CHARACTER_ID} attribute.
     * @param episodeId   a value for the {@value CharacterEpisodeId_#EPISODE_ID} attribute.
     * @return a new instance of {@code characterId} and {@code episodeId}
     */
    public static CharacterEpisodeId of(final Integer characterId, final Integer episodeId) {
        return new CharacterEpisodeId()
                .characterId(characterId)
                .episodeId(episodeId);
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
               "characterId=" + characterId +
               ",episodeId=" + episodeId +
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

    // -------------------------------------------------------------------------------------------- java.lang.Comparable
    @Override
    public int compareTo(final CharacterEpisodeId o) {
        return COMPARATOR.compare(
                this,
                Objects.requireNonNull(o, "o is null")
        );
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

    CharacterEpisodeId characterId(final Integer characterId) {
        setCharacterId(characterId);
        return this;
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

    CharacterEpisodeId episodeId(final Integer episodeId) {
        setEpisodeId(episodeId);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = CharacterEpisode.COLUMN_NAME_CHARACTER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer characterId;

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = CharacterEpisode.COLUMN_NAME_EPISODE_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer episodeId;
}
