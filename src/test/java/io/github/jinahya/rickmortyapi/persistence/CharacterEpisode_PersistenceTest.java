package io.github.jinahya.rickmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class CharacterEpisode_PersistenceTest extends _BaseEntity_PersistenceTest<CharacterEpisode, CharacterEpisodeId> {

    CharacterEpisode_PersistenceTest() {
        super(CharacterEpisode.class, CharacterEpisodeId.class);
    }
}
