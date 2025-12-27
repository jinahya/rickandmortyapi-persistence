package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class __ColumnEnumUtils_Test {

    private enum Sample implements __ColumnEnum<Sample, Object> {
        A(new Object()),
        B(new Object());

        Sample(final Object columnValue) {
            this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
        }

        @Nonnull
        @Override
        public @NonNull Object columnValue() {
            return columnValue;
        }

        private final Object columnValue;
    }

    @EnumSource(Sample.class)
    @ParameterizedTest
    void __(final Sample sample) {
        final var value = __ColumnEnumUtils.valueOfColumnValue(Sample.class, sample.columnValue());
        assertThat(value).isSameAs(sample);
    }
}
