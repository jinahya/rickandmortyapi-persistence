package io.github.jinahya.rickmortyapi.persistence;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = Location.TABLE_NAME)
public class Location extends _BaseEntity {

    public static final String TABLE_NAME = "location";

    // -----------------------------------------------------------------------------------------------------------------
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

    // -----------------------------------------------------------------------------------------------------------------
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
//                 ",residents=" + residents +
               ",url=" + url +
               ",created=" + created +
               '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Location location)) {
            return false;
        }
        return Objects.equals(id, location.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // -------------------------------------------------------------------------------------------------------------- id
    public Integer getId() {
        return id;
    }

    void setId(final Integer id) {
        this.id = id;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    public String getType() {
        return type;
    }

    void setType(@Nullable final String type) {
        this.type = type;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    public String getDimension() {
        return dimension;
    }

    void setDimension(@Nullable final String dimension) {
        this.dimension = dimension;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    public List<URL> getResidents() {
        return residents;
    }

    void setResidents(@Nullable final List<URL> residents) {
        this.residents = residents;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public URL getUrl() {
        return url;
    }

    public void setUrl(final URL url) {
        this.url = url;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Instant getCreated() {
        return created;
    }

    void setCreated(final Instant created) {
        this.created = created;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public List<Character> getResidents_() {
        return residents_;
    }

    void setResidents_(final List<Character> residents_) {
        this.residents_ = residents_;
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
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = false, updatable = false)
    private String name;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TYPE, nullable = true, insertable = false, updatable = false)
    private String type;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DIMENSION, nullable = true, insertable = false, updatable = false)
    private String dimension;

    @Nullable
    @Convert(converter = _UrlListConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RESIDENTS, nullable = true, insertable = false, updatable = false)
    private List<URL> residents;

    // -----------------------------------------------------------------------------------------------------------------
    @Convert(converter = _UrlConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_URL, nullable = false, insertable = false, updatable = false)
    private URL url;

    @Past
    @NotNull
    @Convert(converter = _InstantConverter.class)
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
    private List<Character> residents_; // List of character who have been last seen in the location
}
