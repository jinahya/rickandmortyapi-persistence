package io.github.jinahya.rickandmortyapi.persistence;

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

class CharacterEpisode_Test extends _BaseEntity_Test<CharacterEpisode, CharacterEpisodeId> {

    @Test
    void of__() {
        final var characterId = ThreadLocalRandom.current().nextInt();
        final var episodeId = ThreadLocalRandom.current().nextInt();
        final var id = CharacterEpisodeId.of(characterId, episodeId);
        assertThat(id).isNotNull().satisfies(v -> {
            assertThat(v.getCharacterId()).isEqualTo(characterId);
            assertThat(v.getEpisodeId()).isEqualTo(episodeId);
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    CharacterEpisode_Test() {
        super(CharacterEpisode.class, CharacterEpisodeId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    SingleTypeEqualsVerifierApi<CharacterEpisode> createEqualsVerifier() {
        return super.createEqualsVerifier();
    }

    @Override
    SingleTypeEqualsVerifierApi<CharacterEpisode> configureEqualsVerifier(
            final SingleTypeEqualsVerifierApi<CharacterEpisode> verifier) {
        return super.configureEqualsVerifier(verifier)
                .suppress(Warning.SURROGATE_KEY);
    }
}