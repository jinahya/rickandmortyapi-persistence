package io.github.jinahya.rickandmortyapi.persistence;

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

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class LocationResident_PersistenceTest
        extends _BaseEntity_PersistenceTest<LocationResident, LocationResidentId> {

    LocationResident_PersistenceTest() {
        super(LocationResident.class, LocationResidentId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    void selectAll__(final Root<LocationResident> root) {
        super.selectAll__(root);
        final var locationFetch = root.fetch(LocationResident_.location);
        final var residentFetch = root.fetch(LocationResident_.resident);
        final var residentLocation_Fetch = residentFetch.fetch(Character_.location_);
    }

    @Override
    void selectAll__(final EntityManager entityManager, final List<LocationResident> entityList) {
        super.selectAll__(entityManager, entityList);
        entityList.forEach(lr -> {
            final var location = lr.getLocation();
            final var resident = lr.getResident();
            assertThat(resident.getLocation_())
                    .as("resident.location_")
                    .isEqualTo(location);
        });
    }
}
