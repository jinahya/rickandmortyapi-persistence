package io.github.jinahya.rickandmortyapi.persistence;

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class Character_Test extends _BaseEntity_Test<Character, Integer> {

    static final Character RED = new Character().id(1);

    static final Character BLUE = new Character().id(2);

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class Status_Test {

        @EnumSource(Character.Status.class)
        @ParameterizedTest
        void valueOfColumnValues__(final Character.Status status) {
            assertThat(Character.Status.valueOfColumnValue(status.columnValue()))
                    .isSameAs(status);
        }

        @EnumSource(Character.Status.class)
        @ParameterizedTest
        void columnValues() {
            assertThat(Arrays.stream(Character.Status.values()))
                    .map(Character.Status::columnValue)
                    .doesNotContainNull()
                    .doesNotHaveDuplicates()
            ;
        }
    }

    @Nested
    class Species_Test {

        @EnumSource(Character.Species.class)
        @ParameterizedTest
        void valueOfColumnValues(final Character.Species species) {
            assertThat(Character.Species.valueOfColumnValue(species.columnValue()))
                    .isSameAs(species);
        }

        @EnumSource(Character.Species.class)
        @ParameterizedTest
        void columnValues() {
            assertThat(Arrays.stream(Character.Species.values()))
                    .map(Character.Species::columnValue)
                    .doesNotContainNull()
                    .doesNotHaveDuplicates()
            ;
        }
    }

    @Nested
    class Type_Test {

        @EnumSource(Character.Type.class)
        @ParameterizedTest
        void valueOfColumnValues(final Character.Type type) {
            assertThat(Character.Type.valueOfColumnValue(type.columnValue()))
                    .isSameAs(type);
        }

        @EnumSource(Character.Type.class)
        @ParameterizedTest
        void columnValues() {
            assertThat(Arrays.stream(Character.Type.values()))
                    .map(Character.Type::columnValue)
                    .doesNotContainNull()
                    .doesNotHaveDuplicates()
            ;
        }
    }

    @Nested
    class Gender_Test {

        @EnumSource(Character.Gender.class)
        @ParameterizedTest
        void valueOfColumnValues(final Character.Gender gender) {
            assertThat(Character.Gender.valueOfColumnValue(gender.columnValue()))
                    .isSameAs(gender);
        }

        @EnumSource(Character.Gender.class)
        @ParameterizedTest
        void columnValues() {
            assertThat(Arrays.stream(Character.Gender.values()))
                    .map(Character.Gender::columnValue)
                    .doesNotContainNull()
                    .doesNotHaveDuplicates()
            ;
        }
    }

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
