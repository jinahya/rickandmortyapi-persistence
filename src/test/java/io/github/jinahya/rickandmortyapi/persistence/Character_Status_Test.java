package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

// -----------------------------------------------------------------------------------------------------------------
@Nested
class Character_Status_Test {

    @EnumSource(Character_Status.class)
    @ParameterizedTest
    void valueOfColumnValues__(final Character_Status status) {
        assertThat(Character_Status.valueOfColumnValue(status.columnValue()))
                .isSameAs(status);
    }

    @EnumSource(Character_Status.class)
    @ParameterizedTest
    void columnValues() {
        assertThat(Arrays.stream(Character_Status.values()))
                .map(Character_Status::columnValue)
                .doesNotContainNull()
                .doesNotHaveDuplicates()
        ;
    }
}
