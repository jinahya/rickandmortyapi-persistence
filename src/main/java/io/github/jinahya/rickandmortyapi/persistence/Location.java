package io.github.jinahya.rickandmortyapi.persistence;

import io.github.jinahya.rickandmortyapi.persistence.converter.InstantStringConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlListStringConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlStringConverter;
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
import org.jspecify.annotations.Nullable;

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
public class Location
        extends _BaseEntity<Integer> {

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

    /**
     * The name of the table column to which the {@link Location_#NAME} attributes maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "name";

    // ------------------------------------------------------------------------------------------------------------ type

    /**
     * The name of the table column to which the {@link Location_#TYPE} attributes maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TYPE = "type";

    // ------------------------------------------------------------------------------------------------------- dimension

    /**
     * The name of the table column to which the {@link Location_#NAME} attributes maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_DIMENSION = "dimension";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@link Location_#RESIDENTS} attributes maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_RESIDENTS = "residents";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@link Location_#URL} attributes maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_URL = "url";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@link Location_#CREATED} attributes maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED = "created";

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
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Location that)) {
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
     * Returns current value of {@value Location_#ID} attribute.
     *
     * @return current value of the {@value Location_#ID} attribute.
     */
    public Integer getId() {
        return id;
    }

    void setId(final Integer id) {
        this.id = id;
    }

    Location id(final Integer id) {
        setId(id);
        return this;
    }

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * Returns current value of {@value Location_#NAME} attribute.
     *
     * @return current value of the {@value Location_#NAME} attribute.
     */
    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    // ------------------------------------------------------------------------------------------------------------ type

    /**
     * Returns current value of {@value Location_#TYPE} attribute.
     *
     * @return current value of the {@value Location_#TYPE} attribute.
     */
    @Nullable
    public Location_Type getType() {
        return type;
    }

    void setType(@Nullable final Location_Type type) {
        this.type = type;
    }

    // ------------------------------------------------------------------------------------------------------- dimension

    /**
     * Returns current value of {@value Location_#DIMENSION} attribute.
     *
     * @return current value of the {@value Location_#DIMENSION} attribute.
     */
    @Nullable
    public Location_Dimension getDimension() {
        return dimension;
    }

    void setDimension(@Nullable final Location_Dimension dimension) {
        this.dimension = dimension;
    }

    // ------------------------------------------------------------------------------------------------------- residents

    /**
     * Returns current value of {@value Location_#RESIDENTS} attribute.
     *
     * @return current value of the {@value Location_#RESIDENTS} attribute.
     */
    @Nullable
    public List<URL> getResidents() {
        return residents;
    }

    void setResidents(@Nullable final List<URL> residents) {
        this.residents = residents;
    }

    // ------------------------------------------------------------------------------------------------------------- url

    /**
     * Returns current value of {@value Location_#URL} attribute.
     *
     * @return current value of the {@value Location_#URL} attribute.
     */
    public URL getUrl() {
        return url;
    }

    /**
     * Replaces current value of {@value Location_#URL} attribute with the specified value.
     *
     * <p>This method allows updating the URL for a location entity. Note that this is one
     * of the few mutable attributes in the {@link Location} entity.
     *
     * @param url the new value for {@value Location_#URL} attribute.
     */
    public void setUrl(final URL url) {
        this.url = url;
    }

    // --------------------------------------------------------------------------------------------------------- created

    /**
     * Returns current value of {@value Location_#CREATED} attribute.
     *
     * @return returns current value of the {@value Location_#CREATED} attribute.
     */
    public Instant getCreated() {
        return created;
    }

    void setCreated(final Instant created) {
        this.created = created;
    }

    // ------------------------------------------------------------------------------------------------------ residents_

    /**
     * Returns current value of {@value Location_#RESIDENTS_} attribute.
     *
     * @return current value of the {@value Location_#RESIDENTS_} attribute.
     */
    public List<Character> getResidents_() {
        return residents_;
    }

    void setResidents_(final List<Character> residents_) {
        this.residents_ = residents_;
    }

    // ----------------------------------------------------------------------------------------------- originCharacters_

    /**
     * Returns current value of {@value Location_#ORIGIN_CHARACTERS_} attribute.
     *
     * @return current value of the {@value Location_#ORIGIN_CHARACTERS_} attribute.
     */
    public List<Character> getOriginCharacters_() {
        return originCharacters_;
    }

    void setOriginCharacters_(final List<Character> originCharacters_) {
        this.originCharacters_ = originCharacters_;
    }

    // --------------------------------------------------------------------------------------------- locationCharacters_

    /**
     * Returns current value of {@value Location_#LOCATION_CHARACTERS_} attribute.
     *
     * @return current value of the {@value Location_#LOCATION_CHARACTERS_} attribute.
     */
    public List<Character> getLocationCharacters_() {
        return locationCharacters_;
    }

    void setLocationCharacters_(final List<Character> locationCharacters_) {
        this.locationCharacters_ = locationCharacters_;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ID, nullable = false,
            insertable = false,
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
    private Location_Type type;

    /**
     * An attribute maps to the {@value #COLUMN_NAME_DIMENSION} column.
     *
     * @see Location_DimensionConverter
     */
    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DIMENSION,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Location_Dimension dimension;

    @Nullable
    @Convert(converter = UrlListStringConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RESIDENTS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private List<@NotNull URL> residents;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = UrlStringConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_URL,
            nullable = false,
            insertable = false,
            updatable = false,
            unique = true
    )
    private URL url;

    @Past
    @NotNull
    @Convert(converter = InstantStringConverter.class)
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
    private List<@Valid @NotNull Character> residents_;

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
