package io.github.jinahya.rickandmortyapi.persistence;

/*-
 * #%L
 * rickandmortyapi-persistence
 * %%
 * Copyright (C) 2025 - 2026 Jinahya
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
