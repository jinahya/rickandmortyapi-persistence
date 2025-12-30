package io.github.jinahya.rickandmortyapi.persistence;

/*-
 * #%L
 * rickandmortyapi-persistence
 * %%
 * Copyright (C) 2025 GitHub, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
