package io.github.jinahya.rickmortyapi.persistence;

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Episode_Test extends _BaseEntity_Test<Episode, Integer> {

    static final Episode RED = Episode.of(1);

    static final Episode BLUE = Episode.of(2);

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
}
