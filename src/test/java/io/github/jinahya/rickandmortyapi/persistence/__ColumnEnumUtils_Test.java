package io.github.jinahya.rickandmortyapi.persistence;

/*-
 * #%L
 * rickandmortyapi-persistence
 * %%
 * Copyright (C) 2025 GitHub, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import jakarta.annotation.Nonnull;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class __ColumnEnumUtils_Test {

    private enum Sample
            implements __ColumnEnum<Sample, Object> {
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
