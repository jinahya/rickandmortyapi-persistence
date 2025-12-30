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

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@Deprecated
class EpisodeCharacterId_Test extends __Base_Test<EpisodeCharacterId> {

    @Test
    void of__() {
        final var episodeId = ThreadLocalRandom.current().nextInt();
        final var characterId = ThreadLocalRandom.current().nextInt();
        final var episodeCharacterId = EpisodeCharacterId.of(episodeId, characterId);
        assertThat(episodeCharacterId).isNotNull().satisfies(v -> {
            assertThat(v.getEpisodeId()).isEqualTo(episodeId);
            assertThat(v.getCharacterId()).isEqualTo(characterId);
        });
    }

    EpisodeCharacterId_Test() {
        super(EpisodeCharacterId.class);
    }

    @Nested
    class CompareTo_Test {

        @Test
        void _Equal_SmeeValue() {
            final var instance = EpisodeCharacterId.of(1, 1);
            final var o = EpisodeCharacterId.of(instance.getEpisodeId(), instance.getCharacterId());
            assertThat(instance).isEqualByComparingTo(o);
        }

        @Test
        void _LessThan_Plus() {
            final var instance = EpisodeCharacterId.of(1, 1);
            {
                final var o = EpisodeCharacterId.of(instance.getEpisodeId(), instance.getCharacterId() + 1);
                assertThat(instance).isLessThan(o);
            }
            {
                final var o = EpisodeCharacterId.of(instance.getEpisodeId() + 1, instance.getCharacterId());
                assertThat(instance).isLessThan(o);
            }
        }

        @Test
        void _GreaterThat_Plus() {
            final var instance = EpisodeCharacterId.of(1, 1);
            {
                final var o = EpisodeCharacterId.of(instance.getEpisodeId(), instance.getCharacterId() - 1);
                assertThat(instance).isGreaterThan(o);
            }
            {
                final var o = EpisodeCharacterId.of(instance.getEpisodeId() - 1, instance.getCharacterId());
                assertThat(instance).isGreaterThan(o);
            }
        }
    }
}
