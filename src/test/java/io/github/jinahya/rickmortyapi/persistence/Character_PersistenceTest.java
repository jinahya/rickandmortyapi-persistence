package io.github.jinahya.rickmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Character_PersistenceTest extends _BaseEntity_PersistenceTest<Character, Integer> {

    Character_PersistenceTest() {
        super(Character.class, Integer.class);
    }
}
