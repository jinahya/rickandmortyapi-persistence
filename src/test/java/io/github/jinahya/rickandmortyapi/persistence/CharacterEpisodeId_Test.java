package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.withSettings;

class CharacterEpisodeId_Test extends __Base_Test<CharacterEpisodeId> {

    @Nested
    class Of_Test {

        @CsvSource({
                "1,1"
        })
        @ParameterizedTest
        void of__(final int characterId, final int episodeId) {
            final var id = CharacterEpisodeId.of(characterId, episodeId);
            assertThat(id).isNotNull().satisfies(v -> {
                assertThat(v.getCharacterId()).isEqualTo(characterId);
                assertThat(v.getEpisodeId()).isEqualTo(episodeId);
            });
        }

        @Test
        void of__() {
            final var characterId = ThreadLocalRandom.current().nextInt();
            final var episodeId = ThreadLocalRandom.current().nextInt();
            try (var mocked = mockConstruction(CharacterEpisodeId.class,
                                               withSettings().defaultAnswer(CALLS_REAL_METHODS))) {
                final var instance = CharacterEpisodeId.of(characterId, episodeId);
                assertThat(instance).isNotNull().satisfies(v -> {
                    assertThat(v.getCharacterId()).isEqualTo(characterId);
                    assertThat(v.getEpisodeId()).isEqualTo(episodeId);
                });
                assertThat(mocked.constructed().size()).isOne();
                assertThat(mocked.constructed().getFirst()).satisfies(v -> {
                    verify(v, times(1)).setCharacterId(characterId);
                    verify(v, times(1)).setEpisodeId(episodeId);
                });
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    CharacterEpisodeId_Test() {
        super(CharacterEpisodeId.class);
    }

    @Nested
    class CompareTo_Test {

        @Test
        void _Equal_SmeeValue() {
            final var instance = CharacterEpisodeId.of(1, 1);
            final var o = CharacterEpisodeId.of(instance.getCharacterId(), instance.getEpisodeId());
            assertThat(instance).isEqualByComparingTo(o);
        }

        @Test
        void _LessThan_Plus() {
            final var instance = CharacterEpisodeId.of(1, 1);
            {
                final var o = CharacterEpisodeId.of(instance.getCharacterId(), instance.getEpisodeId() + 1);
                assertThat(instance).isLessThan(o);
            }
            {
                final var o = CharacterEpisodeId.of(instance.getCharacterId() + 1, instance.getEpisodeId());
                assertThat(instance).isLessThan(o);
            }
        }

        @Test
        void _GreaterThat_Plus() {
            final var instance = CharacterEpisodeId.of(1, 1);
            {
                final var o = CharacterEpisodeId.of(instance.getCharacterId(), instance.getEpisodeId() - 1);
                assertThat(instance).isGreaterThan(o);
            }
            {
                final var o = CharacterEpisodeId.of(instance.getCharacterId() - 1, instance.getEpisodeId());
                assertThat(instance).isGreaterThan(o);
            }
        }
    }
}