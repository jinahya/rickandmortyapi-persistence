package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
class CharacterEpisode_PersistenceTest extends _BaseEntity_PersistenceTest<CharacterEpisode, CharacterEpisodeId> {

    CharacterEpisode_PersistenceTest() {
        super(CharacterEpisode.class, CharacterEpisodeId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    void selectAll__(final Root<CharacterEpisode> root) {
        super.selectAll__(root);
        final Fetch<CharacterEpisode, Character> characterFetch = root.fetch(CharacterEpisode_.CHARACTER);
        final Fetch<CharacterEpisode, Episode> episodeFetch = root.fetch(CharacterEpisode_.EPISODE);
    }

    @Override
    void selectAll__(final EntityManager entityManager, final List<CharacterEpisode> characterEpisodes) {
        super.selectAll__(entityManager, characterEpisodes);
    }
}
