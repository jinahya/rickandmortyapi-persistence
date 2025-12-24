package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
class Character_Gender_Test extends _StringColumnEnum_Test<Character_Gender> {

    Character_Gender_Test() {
        super(Character_Gender.class);
    }

    @EnumSource(Character_Gender.class)
    @ParameterizedTest
    void valueOfColumnValues(final Character_Gender gender) {
        assertThat(Character_Gender.valueOfColumnValue(gender.columnValue()))
                .isSameAs(gender);
    }
}
