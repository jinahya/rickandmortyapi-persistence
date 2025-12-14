package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

/**
 * The primary key class for {@link LocationResident}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class LocationResidentId {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_LOCATION_ID = "location_id";

    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_RESIDENT_ID = "resident_id";

    public static final String ATTRIBUTE_NAME_RESIDENT_ID = "residentId";

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with specified values.
     *
     * @param locationId a value for the {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     * @param residentId a value for the {@value #ATTRIBUTE_NAME_RESIDENT_ID} attribute.
     * @return a new instance of {@code characterId} and {@code episodeId}
     */
    public static LocationResidentId of(final Integer locationId, final Integer residentId) {
        final var instance = new LocationResidentId();
        instance.setLocationId(locationId);
        instance.setResidentId(residentId);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected LocationResidentId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + locationId +
               ",name=" + residentId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof LocationResidentId that)) return false;
        return Objects.equals(locationId, that.locationId) &&
               Objects.equals(residentId, that.residentId);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(locationId, residentId);
    }

    // ------------------------------------------------------------------------------------------------------ locationId
    public Integer getLocationId() {
        return locationId;
    }

    void setLocationId(final Integer locationId) {
        this.locationId = locationId;
    }

    // ------------------------------------------------------------------------------------------------------ residentId
    public Integer getResidentId() {
        return residentId;
    }

    void setResidentId(final Integer residentId) {
        this.residentId = residentId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LOCATION_ID, nullable = false, insertable = false, updatable = false)
    private Integer locationId;

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RESIDENT_ID, nullable = false, insertable = false, updatable = false)
    private Integer residentId;
}
