package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@IdClass(LocationResidentId.class)
@Entity
@Table(name = LocationResident.TABLE_NAME)
public class LocationResident extends _BaseEntity {

    public static final String TABLE_NAME = "location_resident";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_LOCATION_ID = "location_id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_RESIDENT_ID = "resident_id";

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected LocationResident() {
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
        if (!(obj instanceof LocationResident that)) {
            return false;
        }
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

    public Location getLocation() {
        return location;
    }

    void setLocation(final Location location) {
        this.location = location;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Integer getResidentId() {
        return residentId;
    }

    void setResidentId(final Integer residentId) {
        this.residentId = residentId;
    }

    public Character getResident() {
        return resident;
    }

    void setResident(final Character resident) {
        this.resident = resident;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LOCATION_ID, nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false)
    private Integer locationId;

    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID, nullable = false, insertable = false, updatable = false)
    private Location location;

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RESIDENT_ID, nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false)
    private Integer residentId;

    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_RESIDENT_ID, nullable = false, insertable = false, updatable = false)
    private Character resident;
}
