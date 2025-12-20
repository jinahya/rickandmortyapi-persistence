package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CharacterEpisodeId_Test extends __Base_Test<CharacterEpisodeId> {

    @Test
    void of__() {
        final var id = CharacterEpisodeId.of(1, 2);
        assertThat(id).isNotNull();
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