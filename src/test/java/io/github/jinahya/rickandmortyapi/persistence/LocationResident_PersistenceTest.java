package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class LocationResident_PersistenceTest extends _BaseEntity_PersistenceTest<LocationResident, LocationResidentId> {

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
