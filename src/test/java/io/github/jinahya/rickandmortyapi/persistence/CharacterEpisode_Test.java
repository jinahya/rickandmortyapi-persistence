package io.github.jinahya.rickandmortyapi.persistence;

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class CharacterEpisode_Test extends _BaseEntity_Test<CharacterEpisode, CharacterEpisodeId> {

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
