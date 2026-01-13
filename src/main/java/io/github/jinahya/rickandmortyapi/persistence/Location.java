package io.github.jinahya.rickandmortyapi.persistence;

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

import io.github.jinahya.rickandmortyapi.persistence.mapped.MappedLocation;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * An entity class for mapping {@value Location#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "Location.SelectList__OrderByTypeAscNullsLastIdAsc",
            query = """
                    SELECT l
                    FROM Location l
                    ORDER BY l.type ASC NULLS LAST,
                             l.id ASC"""
)
@NamedQuery(name = "Location.SelectList__OrderByTypeAscNullsFirstIdAsc",
            query = """
                    SELECT l
                    FROM Location l
                    ORDER BY l.type ASC NULLS FIRST,
                             l.id ASC"""
)
@NamedQuery(name = "Location.SelectList__OrderByIdAsc",
            query = """
                    SELECT l
                    FROM Location l
                    ORDER BY l.type ASC NULLS FIRST,
                             l.id ASC"""
)
@Entity
@Table(name = MappedLocation.TABLE_NAME)
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S116", // Field names should comply with a naming convention
        "java:S117"  // Local variable and method parameter names should comply with a naming convention
})
public class Location
        extends MappedLocation {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Location() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------ residents_

    /**
     * Returns current value of {@value Location_#RESIDENTS_} attribute.
     *
     * @return current value of the {@value Location_#RESIDENTS_} attribute.
     * @deprecated use {@link #getLocationCharacters_()} instead.
     */
    @Deprecated(forRemoval = true)
    public List<Character> getResidents_() {
        return residents_;
    }

    @Deprecated(forRemoval = true)
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
    @Deprecated(forRemoval = true) // exactly the same content with locationCharacters_
    @OrderBy(Character.ATTRIBUTE_NAME_ID + " ASC")
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
    @OrderBy(Character.ATTRIBUTE_NAME_ID + " ASC")
    @OneToMany(mappedBy = Character_.ORIGIN_,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Character> originCharacters_;

    @OrderBy(Character.ATTRIBUTE_NAME_ID + " ASC")
    @OneToMany(mappedBy = Character_.LOCATION_,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Character> locationCharacters_;
}
