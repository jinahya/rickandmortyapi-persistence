package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.EntityManager;
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
    void selectAll__(final EntityManager entityManager, final List<LocationResident> entityList) {
        super.selectAll__(entityManager, entityList);
        entityList.forEach(lr -> {
            final var resident = lr.getResident();
            log.debug("resident: {}", resident);
            assertThat(resident.getLocation_()).isEqualTo(lr.getLocation());
        });
    }
}
