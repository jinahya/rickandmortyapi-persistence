package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
class Character_Type_Test {

    @EnumSource(Character_Type.class)
    @ParameterizedTest
    void valueOfColumnValues(final Character_Type type) {
        assertThat(Character_Type.valueOfColumnValue(type.columnValue()))
                .isSameAs(type);
    }

    @EnumSource(Character_Type.class)
    @ParameterizedTest
    void columnValues() {
        assertThat(Arrays.stream(Character_Type.values()))
                .map(Character_Type::columnValue)
                .doesNotContainNull()
                .doesNotHaveDuplicates()
        ;
    }
}
