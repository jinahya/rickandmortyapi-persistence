package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
class Character_Species_Test {

    @EnumSource(Character_Species.class)
    @ParameterizedTest
    void valueOfColumnValues(final Character_Species species) {
        assertThat(Character_Species.valueOfColumnValue(species.columnValue()))
                .isSameAs(species);
    }

    @EnumSource(Character_Species.class)
    @ParameterizedTest
    void columnValues() {
        assertThat(Arrays.stream(Character_Species.values()))
                .map(Character_Species::columnValue)
                .doesNotContainNull()
                .doesNotHaveDuplicates()
        ;
    }
}
