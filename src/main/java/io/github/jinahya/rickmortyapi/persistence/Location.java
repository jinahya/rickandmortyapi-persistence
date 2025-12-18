package io.github.jinahya.rickmortyapi.persistence;

import io.github.jinahya.rickmortyapi.persistence.converter.InstantConverter;
import io.github.jinahya.rickmortyapi.persistence.converter.UrlConverter;
import io.github.jinahya.rickmortyapi.persistence.converter.UrlListConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * An entity class for mapping {@value Location#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = Location.TABLE_NAME)
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S116", // Field names should comply with a naming convention
        "java:S117"  // Local variable and method parameter names should comply with a naming convention
})
public class Location extends _BaseEntity {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "location";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Location_#ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ID = "id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAME = "name";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_TYPE = "type";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_DIMENSION = "dimension";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_RESIDENTS = "residents";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_URL = "url";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CREATED = "created";

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Location() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Obejct
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
               ",name=" + name +
               ",type=" + type +
               ",dimension=" + dimension +
               ",residents=" + residents +
               ",url=" + url +
               ",created=" + created +
               '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Location location)) {
            return false;
        }
        return Objects.equals(getId(), location.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    // -------------------------------------------------------------------------------------------------------------- id
    public Integer getId() {
        return id;
    }

    void setId(final Integer id) {
        this.id = id;
    }

    // ------------------------------------------------------------------------------------------------------------ name
    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    // ------------------------------------------------------------------------------------------------------------ type
    @Nullable
    public String getType() {
        return type;
    }

    void setType(@Nullable final String type) {
        this.type = type;
    }

    // ------------------------------------------------------------------------------------------------------- dimension
    @Nullable
    public String getDimension() {
        return dimension;
    }

    void setDimension(@Nullable final String dimension) {
        this.dimension = dimension;
    }

    // ------------------------------------------------------------------------------------------------------- residents
    @Nullable
    public List<URL> getResidents() {
        return residents;
    }

    void setResidents(@Nullable final List<URL> residents) {
        this.residents = residents;
    }

    // ------------------------------------------------------------------------------------------------------------- url
    public URL getUrl() {
        return url;
    }

    public void setUrl(final URL url) {
        this.url = url;
    }

    // --------------------------------------------------------------------------------------------------------- created
    public Instant getCreated() {
        return created;
    }

    void setCreated(final Instant created) {
        this.created = created;
    }

    // ------------------------------------------------------------------------------------------------------ residents_
    public List<Character> getResidents_() {
        return residents_;
    }

    void setResidents_(final List<Character> residents_) {
        this.residents_ = residents_;
    }

    // ----------------------------------------------------------------------------------------------- originCharacters_
    public List<Character> getOriginCharacters_() {
        return originCharacters_;
    }

    void setOriginCharacters_(final List<Character> originCharacters_) {
        this.originCharacters_ = originCharacters_;
    }

    // --------------------------------------------------------------------------------------------- locationCharacters_
    public List<Character> getLocationCharacters_() {
        return locationCharacters_;
    }

    void setLocationCharacters_(final List<Character> locationCharacters_) {
        this.locationCharacters_ = locationCharacters_;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Id
    @Column(name = COLUMN_NAME_ID, nullable = false,
//            insertable = false,
            insertable = true, // eclipselink
            updatable = false
    )
    private Integer id;

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private String name;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TYPE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private String type;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DIMENSION,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private String dimension;

    @Nullable
    @Convert(converter = UrlListConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RESIDENTS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private List<URL> residents;

    // -----------------------------------------------------------------------------------------------------------------
    @Convert(converter = UrlConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_URL,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private URL url;

    @Past
    @NotNull
    @Convert(converter = InstantConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CREATED,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Instant created;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    @JoinTable(name = LocationResident.TABLE_NAME,
               joinColumns = @JoinColumn(
                       name = LocationResident.COLUMN_NAME_LOCATION_ID,
                       nullable = false,
                       insertable = false,
                       updatable = false
               ),
               inverseJoinColumns = @JoinColumn(
                       name = LocationResident.COLUMN_NAME_RESIDENT_ID,
                       nullable = false,
                       insertable = false,
                       updatable = false
               )
    )
    private List<@Valid @NotNull Character> residents_; // List of character who have been last seen in the location

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = Character_.ORIGIN_,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Character> originCharacters_;

    @OneToMany(mappedBy = Character_.LOCATION_,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Character> locationCharacters_;
}
