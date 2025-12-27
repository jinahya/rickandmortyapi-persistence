package io.github.jinahya.rickandmortyapi.persistence;

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Location_Test extends _BaseEntity_Test<Location, Integer> {

    static final Location RED = new Location().id(1);

    static final Location BLUE = new Location().id(2);

    // -----------------------------------------------------------------------------------------------------------------
    Location_Test() {
        super(Location.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    SingleTypeEqualsVerifierApi<Location> configureEqualsVerifier(
            final SingleTypeEqualsVerifierApi<Location> verifierApi) {
        return super.configureEqualsVerifier(verifierApi)
                .suppress(Warning.SURROGATE_KEY)
                .withPrefabValues(Character.class, Character_Test.RED, Character_Test.BLUE)
                ;
    }
}
