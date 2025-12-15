package io.github.jinahya.rickmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class CharacterEpisode_PersistenceIT extends _BaseEntity_PersistenceIT<CharacterEpisode, CharacterEpisodeId> {

    CharacterEpisode_PersistenceIT() {
        super(CharacterEpisode.class, CharacterEpisodeId.class);
    }
}
