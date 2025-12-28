package io.github.jinahya.rickandmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

@Deprecated
@Slf4j
class EpisodeCharacter_Test extends _BaseEntity_Test<EpisodeCharacter, EpisodeCharacterId> {

    // -----------------------------------------------------------------------------------------------------------------
    EpisodeCharacter_Test() {
        super(EpisodeCharacter.class, EpisodeCharacterId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    SingleTypeEqualsVerifierApi<EpisodeCharacter> configureEqualsVerifier(
            final SingleTypeEqualsVerifierApi<EpisodeCharacter> verifier) {
        return super.configureEqualsVerifier(verifier)
                .suppress(Warning.SURROGATE_KEY)
                .withPrefabValues(Character.class, Character_Test.RED, Character_Test.BLUE)
                .withPrefabValues(Location.class, Location_Test.RED, Location_Test.BLUE)
                ;
    }
}
