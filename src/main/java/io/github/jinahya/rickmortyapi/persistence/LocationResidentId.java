package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@Embeddable
public class LocationResidentId {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_LOCATION_ID = "location_id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_RESIDENT_ID = "resident_id";

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static LocationResidentId of(final Integer locationId, final Integer residentId) {
        final var instance = new LocationResidentId();
        instance.setLocationId(locationId);
        instance.setResidentId(residentId);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
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

    // -----------------------------------------------------------------------------------------------------------------
    public Integer getLocationId() {
        return locationId;
    }

    void setLocationId(final Integer locationId) {
        this.locationId = locationId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Integer getResidentId() {
        return residentId;
    }

    void setResidentId(final Integer residentId) {
        this.residentId = residentId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LOCATION_ID, nullable = false, insertable = false, updatable = false)
    private Integer locationId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RESIDENT_ID, nullable = false, insertable = false, updatable = false)
    private Integer residentId;
}
