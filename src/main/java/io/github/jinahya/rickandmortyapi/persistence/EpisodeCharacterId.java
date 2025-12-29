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
 * The primary key class for {@link EpisodeCharacter} entity class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @deprecated use {@link CharacterEpisode} and {@link CharacterEpisodeId} instead
 */
@Deprecated
@Embeddable
public class EpisodeCharacterId
        extends __Base
        implements Serializable, // just for the Spring Data REST
                   Comparable<EpisodeCharacterId> {

    @Serial
    private static final long serialVersionUID = 6146625147536147511L;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A comparator compares with {@link EpisodeCharacterId_#EPISODE_ID} attribute.
     */
    public static final Comparator<EpisodeCharacterId> COMPARING_EPISODE_ID =
            Comparator.comparing(EpisodeCharacterId::getEpisodeId);

    /**
     * A comparator compares with {@link EpisodeCharacterId_#CHARACTER_ID} attribute.
     */
    public static final Comparator<EpisodeCharacterId> COMPARING_CHARACTER_ID =
            Comparator.comparing(EpisodeCharacterId::getCharacterId);

    private static final Comparator<EpisodeCharacterId> COMPARATOR =
            COMPARING_EPISODE_ID.thenComparing(COMPARING_CHARACTER_ID);

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with specified values.
     *
     * @param episodeId   a value for the {@value EpisodeCharacterId_#EPISODE_ID} attribute.
     * @param characterId a value for the {@value EpisodeCharacterId_#CHARACTER_ID} attribute.
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
               "episodeId=" + episodeId +
               ",characterId=" + characterId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof EpisodeCharacterId that)) {
            return false;
        }
        return Objects.equals(episodeId, that.episodeId) &&
               Objects.equals(characterId, that.characterId);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(episodeId, characterId);
    }

    // -------------------------------------------------------------------------------------------- java.lang.Comparable
    @Override
    public int compareTo(final EpisodeCharacterId o) {
        return COMPARATOR.compare(this, Objects.requireNonNull(o, "o is null"));
    }

    // ------------------------------------------------------------------------------------------------------- episodeId

    /**
     * Returns current value of {@value EpisodeCharacterId_#EPISODE_ID} attribute.
     *
     * @return current value of the {@value EpisodeCharacterId_#EPISODE_ID} attribute.
     */
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

    /**
     * Returns current value of {@value EpisodeCharacterId_#CHARACTER_ID} attribute.
     *
     * @return current value of the {@value EpisodeCharacterId_#CHARACTER_ID} attribute.
     */
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
    @Column(name = EpisodeCharacter.COLUMN_NAME_EPISODE_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer episodeId;

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = EpisodeCharacter.COLUMN_NAME_CHARACTER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer characterId;
}
