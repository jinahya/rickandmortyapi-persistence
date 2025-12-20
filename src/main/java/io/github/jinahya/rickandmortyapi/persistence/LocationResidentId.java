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
import java.util.Optional;

/**
 * The primary key class for {@link LocationResident}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class LocationResidentId
        extends __Base
        // just for the Spring Data REST
        implements Serializable,
                   Comparable<LocationResidentId> {

    @Serial
    private static final long serialVersionUID = 6274001621614009840L;

    // -----------------------------------------------------------------------------------------------------------------
    private static final Comparator<LocationResidentId> COMPARATOR =
            Comparator.comparing(LocationResidentId::getLocationId)
                    .thenComparing(LocationResidentId::getResidentId);

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with specified values.
     *
     * @param locationId a value for the {@value LocationResidentId_#LOCATION_ID} attribute.
     * @param residentId a value for the {@value LocationResidentId_#RESIDENT_ID} attribute.
     * @return a new instance of {@code characterId} and {@code episodeId}
     */
    public static LocationResidentId of(final Integer locationId, final Integer residentId) {
        return new LocationResidentId()
                .locationId(locationId)
                .residentId(residentId)
                ;
    }

    /**
     * Creates a new instance with specified values' identifiers.
     *
     * @param location a location for the {@value LocationResidentId_#LOCATION_ID} attribute.
     * @param resident a resident for the {@value LocationResidentId_#RESIDENT_ID} attribute.
     * @return a new instance of {@code character.id} and {@code episode.id}
     * @see #of(Integer, Integer)
     */
    public static LocationResidentId of(final Location location, final Character resident) {
        return of(
                Optional.ofNullable(location)
                        .map(Location::getId)
                        .orElse(null),
                Optional.ofNullable(resident)
                        .map(Character::getId)
                        .orElse(null)
        );
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     *
     * @see #of(Integer, Integer)
     */
    protected LocationResidentId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "locationId=" + locationId +
               ",residentId=" + residentId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof LocationResidentId that)) {
            return false;
        }
        return Objects.equals(locationId, that.locationId)
               && Objects.equals(residentId, that.residentId);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(locationId, residentId);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public int compareTo(final LocationResidentId o) {
        return COMPARATOR.compare(this, o);
    }

    // ------------------------------------------------------------------------------------------------------ locationId

    /**
     * Returns current value of {@value LocationResidentId_#LOCATION_ID} attribute.
     *
     * @return current value of the {@value LocationResidentId_#LOCATION_ID} attribute.
     */
    public Integer getLocationId() {
        return locationId;
    }

    void setLocationId(final Integer locationId) {
        this.locationId = locationId;
    }

    LocationResidentId locationId(final Integer locationId) {
        setLocationId(locationId);
        return this;
    }

//    LocationResidentId location(final Location location) {
//        return locationId(
//                Optional.ofNullable(location)
//                        .map(Location::getId)
//                        .orElse(null)
//        );
//    }

    // ------------------------------------------------------------------------------------------------------ residentId

    /**
     * Returns current value of {@value LocationResidentId_#RESIDENT_ID} attribute.
     *
     * @return current value of the {@value LocationResidentId_#RESIDENT_ID} attribute.
     */
    public Integer getResidentId() {
        return residentId;
    }

    void setResidentId(final Integer residentId) {
        this.residentId = residentId;
    }

    LocationResidentId residentId(final Integer residentId) {
        setResidentId(residentId);
        return this;
    }

//    LocationResidentId resident(final Character resident) {
//        return residentId(
//                Optional.ofNullable(resident)
//                        .map(Character::getId)
//                        .orElse(null)
//        );
//    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = LocationResident.COLUMN_NAME_LOCATION_ID, nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false
    )
    private Integer locationId;

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = LocationResident.COLUMN_NAME_RESIDENT_ID,
            nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false
    )
    private Integer residentId;
}
