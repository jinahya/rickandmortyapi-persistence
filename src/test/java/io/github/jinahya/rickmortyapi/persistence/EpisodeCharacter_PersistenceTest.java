package io.github.jinahya.rickmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class EpisodeCharacter_PersistenceTest extends _BaseEntity_PersistenceTest<EpisodeCharacter, EpisodeCharacterId> {

    // -----------------------------------------------------------------------------------------------------------------
    EpisodeCharacter_PersistenceTest() {
        super(EpisodeCharacter.class, EpisodeCharacterId.class);
    }
}
