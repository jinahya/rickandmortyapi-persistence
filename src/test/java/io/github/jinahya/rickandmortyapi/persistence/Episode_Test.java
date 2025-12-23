package io.github.jinahya.rickandmortyapi.persistence;

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
    @Override
    SingleTypeEqualsVerifierApi<Episode> configureEqualsVerifier(
            final SingleTypeEqualsVerifierApi<Episode> verifierApi) {
        return super.configureEqualsVerifier(verifierApi)
                .suppress(Warning.SURROGATE_KEY)
                .withPrefabValues(Character.class, Character_Test.RED, Character_Test.BLUE);
    }

    // -----------------------------------------------------------------------------------------------------------------
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
            final var seasonNumber = instance.getSeasonNumber();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(seasonNumber).isNull();
            verify(instance, times(1)).getEpisode();
        }

        @MethodSource({"arguments"})
        @ParameterizedTest(name = "[{index}]: episode {0} -> seasonNumber: {1}")
        void __(final String episode, final int seasonNumber) {
            final var instance = spy(newTypeInstance());
            given(instance.getEpisode()).willReturn(episode);
            assertThat(instance.getSeasonNumber()).isEqualTo(seasonNumber);
            verify(instance, times(1)).getEpisode();
        }
    }

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
            final var episodeNumber = instance.getEpisodeNumber();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(episodeNumber).isNull();
            verify(instance, times(1)).getEpisode();
        }

        @MethodSource({"arguments"})
        @ParameterizedTest(name = "[{index}]: episode {0} -> episodeNumber: {1}")
        void __(final String episode, final int episodeNumber) {
            final var instance = spy(newTypeInstance());
            given(instance.getEpisode()).willReturn(episode);
            assertThat(instance.getEpisodeNumber()).isEqualTo(episodeNumber);
            verify(instance, times(1)).getEpisode();
        }
    }
}
