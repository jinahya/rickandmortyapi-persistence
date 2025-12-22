package io.github.jinahya.rickandmortyapi.persistence;

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Nested
    class Type_Test {

        @Test
        void columnValue_NoNullsNorDuplicates_() {
            assertThat(Arrays.stream(Location.Type.values()).map(Location.Type::columnValue))
                    .isNotEmpty()
                    .doesNotContainNull()
                    .doesNotHaveDuplicates();
        }
    }

    @Nested
    class Dimension_Test {

        @Test
        void columnValue_NoNullsNorDuplicates_() {
            assertThat(Arrays.stream(Location.Dimension.values()).map(Location.Dimension::columnValue))
                    .isNotEmpty()
                    .doesNotContainNull()
                    .doesNotHaveDuplicates();
        }
    }
}
