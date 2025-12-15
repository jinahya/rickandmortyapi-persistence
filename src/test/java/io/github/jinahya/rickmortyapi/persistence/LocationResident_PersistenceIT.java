package io.github.jinahya.rickmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class LocationResident_PersistenceIT extends _BaseEntity_PersistenceIT<LocationResident, LocationResidentId> {

    LocationResident_PersistenceIT() {
        super(LocationResident.class, LocationResidentId.class);
    }
}
