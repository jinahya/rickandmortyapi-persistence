package io.github.jinahya.rickmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Location_PersistenceIT extends _BaseEntity_PersistenceIT<Location, Integer> {

    Location_PersistenceIT() {
        super(Location.class, Integer.class);
    }
}
