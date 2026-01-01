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

import java.util.Comparator;
import java.util.Objects;

@SuppressWarnings({
        "java:S114" // Interface names should comply with a naming convention
})
public interface _StringColumnEnumComparator<E extends Enum<E> & _StringColumnEnum<E>>
        extends __ColumnEnumComparator<E, String> {

    /**
     * Returns a comparator comparing {@link __ColumnEnum#columnValue() columnValue} ignoring case.
     *
     * @param <E> enum type parameter
     * @return a comparator comparing {@link __ColumnEnum#columnValue() columnValue} ignoring case.
     */
    static <E extends Enum<E> & _StringColumnEnum<E>> Comparator<E> comparingColumnValueIgnoreCase() {
        return new _StringColumnEnumComparator<>() {
            @Override
            public int compare(final E o1, final E o2) {
                Objects.requireNonNull(o1, "o1 is null");
                Objects.requireNonNull(o2, "o2 is null");
                return o1.columnValue().compareToIgnoreCase(o2.columnValue());
            }
        };
    }
}
