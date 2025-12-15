package io.github.jinahya.rickmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class EpisodeCharacter_PersistenceIT extends _BaseEntity_PersistenceIT<EpisodeCharacter, EpisodeCharacterId> {

    // -----------------------------------------------------------------------------------------------------------------
    EpisodeCharacter_PersistenceIT() {
        super(EpisodeCharacter.class, EpisodeCharacterId.class);
    }
}
