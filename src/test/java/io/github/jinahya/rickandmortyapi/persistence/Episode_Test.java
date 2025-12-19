package io.github.jinahya.rickandmortyapi.persistence;

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Episode_Test extends _BaseEntity_Test<Episode, Integer> {

    static final Episode RED = Episode.of(1);

    static final Episode BLUE = Episode.of(2);

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
                    Arguments.of("S01E01", 1, 1),
                    Arguments.of("S02E01", 2, 1),
                    Arguments.of("S03E01", 3, 1),
                    Arguments.of("S99E99", 99, 99)
            );
        }

        @MethodSource({"arguments"})
        @ParameterizedTest
        void __(final String episode, final int seasonNumber, final int episodeNumber) {
            final var instance = Mockito.spy(newTypeInstance());
            BDDMockito.given(instance.getEpisode()).willReturn(episode);
            assertThat(instance.getSeasonNumber()).isEqualTo(seasonNumber);
            assertThat(instance.getEpisodeNumber()).isEqualTo(episodeNumber);
        }
    }
}
