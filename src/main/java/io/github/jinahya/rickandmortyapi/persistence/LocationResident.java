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

@Entity
@Table(name = LocationResident.TABLE_NAME)
public class LocationResident extends _BaseEntity<LocationResidentId> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "location_resident";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database column to which {@link LocationResident#location} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_ID = "location_id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database column to which {@link LocationResident#resident} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_RESIDENT_ID = "resident_id";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected LocationResident() {
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
        if (!(obj instanceof LocationResident that)) {
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
     * Returns current value of {@value LocationResident_#ID} attribute.
     *
     * @return current value of the {@value LocationResident_#ID} attribute.
     */
    public LocationResidentId getId() {
        return id;
    }

    void setId(final LocationResidentId id) {
        this.id = id;
    }

    LocationResident id(final LocationResidentId id) {
        setId(id);
        return this;
    }

    // -------------------------------------------------------------------------------------------------------- location

    /**
     * Returns current value of {@value LocationResident_#LOCATION} attribute.
     *
     * @return current value of the {@value LocationResident_#LOCATION} attribute.
     */
    public Location getLocation() {
        return location;
    }

    void setLocation(final Location location) {
        this.location = location;
        Optional.ofNullable(getId())
                .orElseGet(() -> id(new LocationResidentId()).getId())
                .setLocationId(
                        Optional.ofNullable(this.location)
                                .map(Location::getId)
                                .orElse(null)
                )
        ;
    }

    // -------------------------------------------------------------------------------------------------------- resident

    /**
     * Returns current value of {@value LocationResident_#RESIDENT} attribute.
     *
     * @return current value of the {@value LocationResident_#RESIDENT} attribute.
     */
    public Character getResident() {
        return resident;
    }

    void setResident(final Character resident) {
        this.resident = resident;
        Optional.ofNullable(getId())
                .orElseGet(() -> id(new LocationResidentId()).getId())
                .setResidentId(
                        Optional.ofNullable(this.resident)
                                .map(Character::getId)
                                .orElse(null)
                )
        ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private LocationResidentId id;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID,
                referencedColumnName = Location.COLUMN_NAME_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Location location;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_RESIDENT_ID,
                referencedColumnName = Character.COLUMN_NAME_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Character resident;
}
