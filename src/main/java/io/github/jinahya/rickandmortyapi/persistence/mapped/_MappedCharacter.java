package io.github.jinahya.rickandmortyapi.persistence.mapped;

/*-
 * #%L
 * rickandmortyapi-persistence
 * %%
 * Copyright (C) 2025 GitHub, Inc.
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

import io.github.jinahya.rickandmortyapi.persistence.Character_;
import io.github.jinahya.rickandmortyapi.persistence.Character_Gender;
import io.github.jinahya.rickandmortyapi.persistence.Character_GenderConverter;
import io.github.jinahya.rickandmortyapi.persistence.Character_NameAndUrl;
import io.github.jinahya.rickandmortyapi.persistence.Character_Species;
import io.github.jinahya.rickandmortyapi.persistence.Character_SpeciesConverter;
import io.github.jinahya.rickandmortyapi.persistence.Character_Status;
import io.github.jinahya.rickandmortyapi.persistence.Character_StatusConverter;
import io.github.jinahya.rickandmortyapi.persistence.Character_Type;
import io.github.jinahya.rickandmortyapi.persistence.Character_TypeConverter;
import io.github.jinahya.rickandmortyapi.persistence.Location;
import io.github.jinahya.rickandmortyapi.persistence.converter.InstantStringConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlListStringConverter;
import io.github.jinahya.rickandmortyapi.persistence.converter.UrlStringConverter;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
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
        "java:S115", // Constant names should comply with a naming convention
        "java:S116", // Field names should comply with a naming convention
        "java:S117"  // Local variable and method parameter names should comply with a naming convention
})
public abstract class _MappedCharacter
        extends __MappedEntity<Integer> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "character";

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * The name of the table column to which the {@value Character_#ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ID = "id";

    public static final String ATTRIBUTE_NAME_ID = "id";

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * The name of the table column to which the {@value Character_#NAME} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "name";

    // ---------------------------------------------------------------------------------------------------------- status

    /**
     * The name of the table column to which the {@value Character_#STATUS} attribute maps. The value is {@value}.
     *
     * @see Character_Status
     */
    public static final String COLUMN_NAME_STATUS = "status";

    // --------------------------------------------------------------------------------------------------------- species

    /**
     * The name of the table column to which the {@value Character_#SPECIES} attribute maps. The value is {@value}.
     *
     * @see Character_Species
     */
    public static final String COLUMN_NAME_SPECIES = "species";

    // ------------------------------------------------------------------------------------------------------------ type

    /**
     * The name of the table column to which the {@value Character_#TYPE} attribute maps. The value is {@value}.
     *
     * @see Character_Type
     */
    public static final String COLUMN_NAME_TYPE = "type";

    // ---------------------------------------------------------------------------------------------------------- gender

    /**
     * The name of the table column to which the {@value Character_#GENDER} attribute maps. The value is {@value}.
     *
     * @see Character_Gender
     */
    public static final String COLUMN_NAME_GENDER = "gender";

    // -----------------------------------------------------------------------------------------------------------------
    static final String PROPERTY_PATH_ORIGIN_NAME = "$.origin.name";

    /**
     * The name of the table column to which the {@value Character_#ORIGIN}.{@value Character_NameAndUrl_#NAME}
     * attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORIGIN_NAME = "origin_name";

    // -----------------------------------------------------------------------------------------------------------------
    static final String PROPERTY_PATH_ORIGIN_URL = "$.origin.url";

    /**
     * The name of the table column to which the {@value Character_#ORIGIN}.{@value Character_NameAndUrl_#URL} attribute
     * maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORIGIN_URL = "origin_url";

    // -----------------------------------------------------------------------------------------------------------------
    static final String PROPERTY_PATH_LOCATION_NAME = "$.location.name";

    /**
     * The name of the table column to which the {@value Character_#LOCATION}.{@value Character_NameAndUrl_#NAME}
     * attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_NAME = "location_name";

    // -----------------------------------------------------------------------------------------------------------------
    static final String PROPERTY_PATH_LOCATION_URL = "$.location.url";

    /**
     * The name of the table column to which the {@value Character_#LOCATION}.{@value Character_NameAndUrl_#URL}
     * attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_URL = "location_url";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Character_#IMAGE} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_IMAGE = "image";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Character_#EPISODE} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_EPISODE = "episode";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Character_#URL} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_URL = "url";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Character_#CREATED} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED = "created";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Character_#ORIGIN_} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORIGIN_ID_ = "origin_id_";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the table column to which the {@value Character_#LOCATION_} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_ID_ = "location_id_";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A comparator comparing {@link _MappedCharacter} instances by their {@link Character_#ID} attribute.
     */
    public static final Comparator<_MappedCharacter> COMPARING_ID = Comparator.comparingInt(
            _MappedCharacter::getId);

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedCharacter() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
               ",name=" + name +
               ",status=" + status +
               ",species=" + species +
               ",type=" + type +
               ",gender=" + gender +
               ",origin=" + origin +
               ",location=" + location +
               ",image=" + image +
               ",url=" + url +
               ",created=" + created +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof _MappedCharacter that)) {
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
     * Returns current value of {@value Character_#ID} attribute.
     *
     * @return current value of the {@value Character_#ID} attribute.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Replaces current value of {@value Character_#ID} attribute with the specified value.
     *
     * @param id the new value for {@value Character_#ID} attribute.
     */
    void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Replaces current value of {@value Character_#ID} attribute with the specified value, and returns {@code this}.
     *
     * @param id the new value for {@value Character_#ID} attribute.
     * @return {@code this} for method chaining.
     */
    _MappedCharacter id(final Integer id) {
        setId(id);
        return this;
    }

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * Returns current value of {@value Character_#NAME} attribute.
     *
     * @return current value of the {@value Character_#NAME} attribute.
     */
    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    // ---------------------------------------------------------------------------------------------------------- status

    /**
     * Returns current value of {@value Character_#STATUS} attribute.
     *
     * @return current value of the {@value Character_#STATUS} attribute.
     */
    public Character_Status getStatus() {
        return status;
    }

    void setStatus(final Character_Status status) {
        this.status = status;
    }

    // --------------------------------------------------------------------------------------------------------- species

    /**
     * Returns current value of {@value Character_#SPECIES} attribute.
     *
     * @return current value of the {@value Character_#SPECIES} attribute.
     */
    public Character_Species getSpecies() {
        return species;
    }

    void setSpecies(final Character_Species species) {
        this.species = species;
    }

    // ------------------------------------------------------------------------------------------------------------ type

    /**
     * Returns current value of {@value Character_#TYPE} attribute.
     *
     * @return current value of the {@value Character_#TYPE} attribute.
     */
    @Nullable
    public Character_Type getType() {
        return type;
    }

    void setType(@Nullable final Character_Type type) {
        this.type = type;
    }

    // ---------------------------------------------------------------------------------------------------------- gender

    /**
     * Returns current value of {@value Character_#GENDER} attribute.
     *
     * @return current value of the {@value Character_#GENDER} attribute.
     */
    public Character_Gender getGender() {
        return gender;
    }

    void setGender(final Character_Gender gender) {
        this.gender = gender;
    }

    // ---------------------------------------------------------------------------------------------------------- origin

    /**
     * Returns current value of {@value Character_#ORIGIN} attribute.
     *
     * @return current value of the {@value Character_#ORIGIN} attribute.
     * @see #getOrigin_()
     */
    @Nullable
    public Character_NameAndUrl getOrigin() {
        return origin;
    }

    void setOrigin(@Nullable final Character_NameAndUrl origin) {
        this.origin = origin;
    }

    // -------------------------------------------------------------------------------------------------------- location

    /**
     * Returns current value of {@value Character_#LOCATION} attribute.
     *
     * @return current value of the {@value Character_#LOCATION} attribute.
     * @see #getLocation_()
     */
    @Nullable
    public Character_NameAndUrl getLocation() {
        return location;
    }

    void setLocation(@Nullable final Character_NameAndUrl location) {
        this.location = location;
    }

    // ----------------------------------------------------------------------------------------------------------- image

    /**
     * Returns current value of {@value Character_#IMAGE} attribute.
     *
     * @return current value of the {@value Character_#IMAGE} attribute.
     */
    public URL getImage() {
        return image;
    }

    void setImage(final URL image) {
        this.image = image;
    }

    // --------------------------------------------------------------------------------------------------------- episode

    /**
     * Returns current value of {@value Character_#EPISODE} attribute.
     *
     * @return current value of the {@value Character_#EPISODE} attribute.
     */
    public List<URL> getEpisode() {
        return episode;
    }

    void setEpisode(final List<URL> episode) {
        this.episode = episode;
    }

    // ------------------------------------------------------------------------------------------------------------- url

    /**
     * Returns current value of {@value Character_#URL} attribute.
     *
     * @return current value of the {@value Character_#URL} attribute.
     */
    public URL getUrl() {
        return url;
    }

    void setUrl(final URL url) {
        this.url = url;
    }

    // --------------------------------------------------------------------------------------------------------- created

    /**
     * Returns current value of {@value Character_#CREATED} attribute.
     *
     * @return current value of the {@value Character_#CREATED} attribute.
     */
    public Instant getCreated() {
        return created;
    }

    void setCreated(final Instant created) {
        this.created = created;
    }

    // --------------------------------------------------------------------------------------------------------- origin_

    /**
     * Returns current value of {@value Character_#ORIGIN_} attribute.
     *
     * @return current value of the {@value Character_#ORIGIN_} attribute; may be {@code null}.
     * @see #getOrigin()
     */
    @Nullable
    public Location getOrigin_() {
        return origin_;
    }

    void setOrigin_(@Nullable final Location originLocation_) {
        this.origin_ = originLocation_;
    }

    // ------------------------------------------------------------------------------------------------------- location_

    /**
     * Returns current value of {@value Character_#LOCATION_} attribute.
     *
     * @return current value of the {@value Character_#LOCATION_} attribute; may be {@code null}.
     * @see #getLocation()
     */
    @Nullable
    public Location getLocation_() {
        return location_;
    }

    void setLocation_(@Nullable final Location locationLocation_) {
        this.location_ = locationLocation_;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ID,
            nullable = false,
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

    /**
     * An attribute for the {@value #COLUMN_NAME_STATUS} column.
     *
     * @see Character_StatusConverter
     */
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Character_Status status;

    /**
     * An attribute for the {@value #COLUMN_NAME_SPECIES} column.
     *
     * @see Character_SpeciesConverter
     */
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SPECIES,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Character_Species species;

    /**
     * An attribute for the {@value #COLUMN_NAME_TYPE} column.
     *
     * @see Character_TypeConverter
     */
    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TYPE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Character_Type type;

    /**
     * An attribute for the {@value #COLUMN_NAME_GENDER} column.
     *
     * @see Character_GenderConverter
     */
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_GENDER,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Character_Gender gender;

    @Nullable
    @Valid
    @Embedded
    @AttributeOverride(name = Character_NameAndUrl.ATTRIBUTE_NAME_NAME,
                       column = @Column(name = COLUMN_NAME_ORIGIN_NAME,
                                        nullable = true,
                                        insertable = false,
                                        updatable = false
                       )
    )
    @AttributeOverride(name = Character_NameAndUrl.ATTRIBUTE_NAME_URL,
                       column = @Column(name = COLUMN_NAME_ORIGIN_URL,
                                        nullable = true,
                                        insertable = false,
                                        updatable = false
                       )
    )
    private Character_NameAndUrl origin;

    @Nullable
    @Valid
    @Embedded
    @AttributeOverride(name = Character_NameAndUrl.ATTRIBUTE_NAME_NAME,
                       column = @Column(name = COLUMN_NAME_LOCATION_NAME,
                                        nullable = true,
                                        insertable = false,
                                        updatable = false
                       )
    )
    @AttributeOverride(name = Character_NameAndUrl.ATTRIBUTE_NAME_URL,
                       column = @Column(name = COLUMN_NAME_LOCATION_URL,
                                        nullable = true,
                                        insertable = false,
                                        updatable = false
                       )
    )
    private Character_NameAndUrl location;

    @NotNull
    @Convert(converter = UrlStringConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IMAGE,
            nullable = false,
            insertable = false,
            updatable = false,
            unique = true
    )
    private URL image;

    @NotNull
    @Convert(converter = UrlListStringConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EPISODE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private List<@NotNull URL> episode;

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
    @Nullable
    @Valid
    @ManyToOne(optional = true,
               fetch = FetchType.LAZY,
               cascade = {
               }
    )
    @JoinColumn(name = COLUMN_NAME_ORIGIN_ID_,
                referencedColumnName = Location.COLUMN_NAME_ID,
                nullable = true,
                insertable = false,
                updatable = false
    )
    private Location origin_;

    @Nullable
    @Valid
    @ManyToOne(optional = true,
               fetch = FetchType.LAZY,
               cascade = {
               }
    )
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID_,
                referencedColumnName = Location.COLUMN_NAME_ID,
                nullable = true,
                insertable = false,
                updatable = false
    )
    private Location location_;
}
