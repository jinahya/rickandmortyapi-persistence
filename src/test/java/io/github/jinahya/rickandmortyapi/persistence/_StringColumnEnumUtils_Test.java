package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class _StringColumnEnumUtils_Test {

    private enum Sample implements _StringColumnEnum<Sample> {

        A("a"),

        B("b");

        Sample(final String columnValue) {
            this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
        }

        @Nonnull
        @Override
        public @NonNull String columnValue() {
            return columnValue;
        }

        private final String columnValue;
    }

    @EnumSource(Sample.class)
    @ParameterizedTest
    void __(final Sample sample) {
        final var value = _StringColumnEnumUtils.valueOfColumnValue(Sample.class, sample.columnValue());
        assertThat(value).isSameAs(sample);
    }
}
