package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({
        "java:S115", // Constant names should comply with a naming convention
        "java:S3577" // Test classes should comply with a naming convention
})
class _StringColumnEnumComparatorTest {

    private enum StringColumnEnum
            implements _StringColumnEnum<StringColumnEnum> {

        _0,

        _1;

        @Override
        public String columnValue() {
            return name();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    _StringColumnEnumComparatorTest() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void __() {
        final var comparator = _StringColumnEnumComparator.comparingColumnValueIgnoreCase();
        final var list = Arrays.asList(StringColumnEnum.values());
        assertThat(list).isSortedAccordingTo(comparator);
        assertThat(list.reversed()).isSortedAccordingTo(comparator.reversed());
    }
}
