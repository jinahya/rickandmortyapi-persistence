package io.github.jinahya.rickandmortyapi.persistence.mapped;

/*-
 * #%L
 * rickandmortyapi-persistence
 * %%
 * Copyright (C) 2025 - 2026 Jinahya
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import io.github.jinahya.rickandmortyapi.persistence.Location_;
import io.github.jinahya.rickandmortyapi.persistence.Location_Dimension;
import io.github.jinahya.rickandmortyapi.persistence.Location_DimensionConverter;
import io.github.jinahya.rickandmortyapi.persistence.Location_Type;
import io.github.jinahya.rickandmortyapi.persistence._BaseEntity;
import io.github.jinahya.rickandmortyapi.persistence.converter.InstantStringConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlListStringConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlStringConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import org.jspecify.annotations.Nullable;

import java.net.URL;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@MappedSuperclass
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S116", // Field names should comply with a naming convention
        "java:S117"  // Local variable and method parameter names should comply with a naming convention
})
public abstract class MappedLocation
        extends _BaseEntity<Integer> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "location";

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * The name of the table column to which the {@value Location_#ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ID = "id";

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * The name of the table column to which the {@link Location_#NAME} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "name";

    // ------------------------------------------------------------------------------------------------------------ type

    /**
     * The name of the table column to which the {@link Location_#TYPE} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TYPE = "type";

    // ------------------------------------------------------------------------------------------------------- dimension

    /**
     * The name of the table column to which the {@link Location_#DIMENSION} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_DIMENSION = "dimension";

    // ------------------------------------------------------------------------------------------------------- residents

    /**
     * The name of the table column to which the {@link Location_#RESIDENTS} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_RESIDENTS = "residents";

    // ------------------------------------------------------------------------------------------------------------- url

    /**
     * The name of the table column to which the {@link Location_#URL} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_URL = "url";

    // --------------------------------------------------------------------------------------------------------- created

    /**
     * The name of the table column to which the {@link Location_#CREATED} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED = "created";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A comparator compares with {@value Location_#ID} attribute.
     */
    public static final Comparator<MappedLocation> COMPARING_ID = Comparator.comparing(MappedLocation::getId);

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedLocation() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
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
        if (!(obj instanceof MappedLocation that)) {
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

    protected void setId(final Integer id) {
        this.id = id;
    }

    protected MappedLocation id(final Integer id) {
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

    protected void setName(final String name) {
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

    protected void setType(@Nullable final Location_Type type) {
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

    protected void setDimension(@Nullable final Location_Dimension dimension) {
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

    protected void setResidents(@Nullable final List<URL> residents) {
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
     * of the few mutable attributes in the {@link MappedLocation} entity.
     *
     * @param url the new value for {@value Location_#URL} attribute.
     */
    protected void setUrl(final URL url) {
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

    protected void setCreated(final Instant created) {
        this.created = created;
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
}
