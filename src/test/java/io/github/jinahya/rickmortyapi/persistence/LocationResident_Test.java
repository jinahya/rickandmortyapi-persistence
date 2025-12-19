package io.github.jinahya.rickmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

@Slf4j
class LocationResident_Test extends _BaseEntity_Test<LocationResident, LocationResidentId> {

    LocationResident_Test() {
        super(LocationResident.class, LocationResidentId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    SingleTypeEqualsVerifierApi<LocationResident> configureEqualsVerifier(
            SingleTypeEqualsVerifierApi<LocationResident> verifier) {
        return super.configureEqualsVerifier(verifier)
                .suppress(Warning.SURROGATE_KEY)
                .withPrefabValues(Character.class, Character_Test.RED, Character_Test.BLUE)
                .withPrefabValues(Location.class, Location_Test.RED, Location_Test.BLUE)
                ;
    }
}
