package io.github.jinahya.rickandmortyapi.persistence;

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Character_Test extends _BaseEntity_Test<Character, Integer> {

    static final Character RED = new Character().id(1);

    static final Character BLUE = new Character().id(2);

    // -----------------------------------------------------------------------------------------------------------------
    Character_Test() {
        super(Character.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    SingleTypeEqualsVerifierApi<Character> configureEqualsVerifier(
            final SingleTypeEqualsVerifierApi<Character> verifier) {
        return super.configureEqualsVerifier(verifier)
                    .suppress(Warning.SURROGATE_KEY)
                    .withPrefabValues(Episode.class, Episode_Test.RED, Episode_Test.BLUE)
                    .withPrefabValues(Location.class, Location_Test.RED, Location_Test.BLUE);
    }
}
