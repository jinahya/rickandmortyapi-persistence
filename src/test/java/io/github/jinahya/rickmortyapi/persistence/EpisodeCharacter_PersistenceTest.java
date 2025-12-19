package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
class EpisodeCharacter_PersistenceTest extends _BaseEntity_PersistenceTest<EpisodeCharacter, EpisodeCharacterId> {

    // -----------------------------------------------------------------------------------------------------------------
    EpisodeCharacter_PersistenceTest() {
        super(EpisodeCharacter.class, EpisodeCharacterId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    void selectAll__(final EntityManager entityManager, final List<EpisodeCharacter> entityList) {
        super.selectAll__(entityManager, entityList);
    }
}
