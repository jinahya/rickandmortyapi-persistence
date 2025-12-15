package io.github.jinahya.rickmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Character_PersistenceIT extends _BaseEntity_PersistenceIT<Character, Integer> {

    Character_PersistenceIT() {
        super(Character.class, Integer.class);
    }
}
