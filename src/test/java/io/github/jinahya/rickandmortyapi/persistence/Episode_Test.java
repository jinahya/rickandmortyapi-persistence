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

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class Episode_Test extends _BaseEntity_Test<Episode, Integer> {

    static final Episode RED = new Episode().id(1);

    static final Episode BLUE = new Episode().id(2);

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("episodeOf(seasonNumber, episodeNumber)")
    @Nested
    class EpisodeOf_Test {

        @DisplayName("seasonNumber or episodeNumber is wrong")
        @CsvSource({
                "0, 1",
                "0, 99",
                "1, 0",
                "1, 100",
                "0, 0",
                "100, 100"
        })
        @ParameterizedTest
        void __EitherSeasonNumberOfEpisodeNumberIsWrong(final int seasonNumber, final int episodeNumber) {
            assert (seasonNumber < 1 || seasonNumber > 99) || (episodeNumber < 1 || episodeNumber > 99);
            assertThatThrownBy(() -> {
                final var episode = Episode.episodeOf(seasonNumber, episodeNumber);
            })
                    .as("thrown by episodeOf(%d, %d)", seasonNumber, episodeNumber)
                    .isInstanceOf(IllegalArgumentException.class)
            ;
        }

        @Test
        void __() {
            final var seasonNumber = ThreadLocalRandom.current().nextInt(1, 100);
            final var episodeNumber = ThreadLocalRandom.current().nextInt(1, 100);
            assertThatCode(() -> {
                final var episode = Episode.episodeOf(seasonNumber, episodeNumber);
                assertThat(episode).isNotNull().matches(Episode.PATTERN_EPISODE);
            })
                    .as("episodeOf(%d, %d)", seasonNumber, episodeNumber)
                    .doesNotThrowAnyException()
            ;
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    Episode_Test() {
        super(Episode.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void __ResetParsingCaches_OnSetEpisode() {
        // ------------------------------------------------------------------------------------------------------- given
        final var episode = new Episode();
        episode.setEpisode("S01E01");
        assertThat(episode.getSeasonNumber_()).isEqualTo(1);
        assertThat(episode.getEpisodeNumber_()).isEqualTo(1);
        // -------------------------------------------------------------------------------------------------------- when
        episode.setEpisode("S02E03");
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(episode.getSeasonNumber_()).isEqualTo(2);
        assertThat(episode.getEpisodeNumber_()).isEqualTo(3);
    }

    @Test
    void __ResetParsingCaches_OnLifecycleMethods() throws Exception {
        // ------------------------------------------------------------------------------------------------------- given
        final var episode = new Episode();
        episode.setEpisode("S01E01");
        assertThat(episode.getSeasonNumber_()).isEqualTo(1);
        assertThat(episode.getEpisodeNumber_()).isEqualTo(1);

        // Manually changing the field via reflection to simulate database change (e.g. refresh)
        final var field = Episode.class.getDeclaredField("episode");
        field.setAccessible(true);
        field.set(episode, "S02E03");

        // The cache is still S01E01 because it hasn't been reset yet
        assertThat(episode.getSeasonNumber_()).isEqualTo(1);
        assertThat(episode.getEpisodeNumber_()).isEqualTo(1);

        // -------------------------------------------------------------------------------------------------------- when
        final var method = Episode.class.getDeclaredMethod("doOnPostLoad");
        method.setAccessible(true);
        method.invoke(episode);

        // -------------------------------------------------------------------------------------------------------- then
        assertThat(episode.getSeasonNumber_()).isEqualTo(2);
        assertThat(episode.getEpisodeNumber_()).isEqualTo(3);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    SingleTypeEqualsVerifierApi<Episode> configureEqualsVerifier(
            final SingleTypeEqualsVerifierApi<Episode> verifierApi) {
        return super.configureEqualsVerifier(verifierApi)
                .suppress(Warning.SURROGATE_KEY)
                .withPrefabValues(Character.class, Character_Test.RED, Character_Test.BLUE);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("getSeasonNumber()")
    @Nested
    class SeasonNumber_Test {

        private static Stream<Arguments> arguments() {
            return Stream.of(
                    Arguments.of("S01E01", 1),
                    Arguments.of("S02E01", 2),
                    Arguments.of("S98E01", 98),
                    Arguments.of("S99E01", 99)
            );
        }

        @DisplayName("getEpisode()null -> null")
        @Test
        void __() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = spy(newTypeInstance());
            given(instance.getEpisode()).willReturn(null);
            // ---------------------------------------------------------------------------------------------------- when
            final var seasonNumber = instance.getSeasonNumber_();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(seasonNumber).isNull();
            verify(instance, times(1)).getEpisode();
        }

        @MethodSource({"arguments"})
        @ParameterizedTest(name = "[{index}]: episode {0} -> seasonNumber: {1}")
        void __(final String episode, final int seasonNumber) {
            final var instance = spy(newTypeInstance());
            given(instance.getEpisode()).willReturn(episode);
            assertThat(instance.getSeasonNumber_()).isEqualTo(seasonNumber);
            verify(instance, times(1)).getEpisode();
        }
    }

    @DisplayName("getEpisodeNumber()")
    @Nested
    class EpisodeNumber_Test {

        private static Stream<Arguments> arguments() {
            return Stream.of(
                    Arguments.of("S01E01", 1),
                    Arguments.of("S01E02", 2),
                    Arguments.of("S01E98", 98),
                    Arguments.of("S99E99", 99)
            );
        }

        @DisplayName("getEpisode()null -> null")
        @Test
        void __() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = spy(newTypeInstance());
            given(instance.getEpisode()).willReturn(null);
            // ---------------------------------------------------------------------------------------------------- when
            final var episodeNumber = instance.getEpisodeNumber_();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(episodeNumber).isNull();
            verify(instance, times(1)).getEpisode();
        }

        @MethodSource({"arguments"})
        @ParameterizedTest(name = "[{index}]: episode {0} -> episodeNumber: {1}")
        void __(final String episode, final int episodeNumber) {
            final var instance = spy(newTypeInstance());
            given(instance.getEpisode()).willReturn(episode);
            assertThat(instance.getEpisodeNumber_()).isEqualTo(episodeNumber);
            verify(instance, times(1)).getEpisode();
        }
    }
}
