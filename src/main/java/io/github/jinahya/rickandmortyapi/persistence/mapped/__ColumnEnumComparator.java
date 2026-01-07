package io.github.jinahya.rickandmortyapi.persistence.mapped;

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

/**
 * A comparator compares enum constants with their {@link __ColumnEnum#columnValue() columnValue}s.
 *
 * @param <E> enum type parameter
 * @param <T> column value type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S114" // Interface names should comply with a naming convention
})
public interface __ColumnEnumComparator<
        E extends __ColumnEnum<?, T>,
        T extends Comparable<? super T>
        >
        extends Comparator<E> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a comparator comparing enum constants with their {@link __ColumnEnum#columnValue() columnValue}s using
     * the specified comparator.
     *
     * @param comparator the comparator to compare column values.
     * @param <E>        enum type parameter
     * @param <T>        column value type parameter
     * @return a comparator comparing enum constants with their {@link __ColumnEnum#columnValue() columnValue}s using
     *         {@code comparator}.
     */
    static <
            E extends __ColumnEnum<?, T>,
            T
            >
    Comparator<E> comparingColumnValue(final Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator, "comparator is null");
        return Comparator.comparing(__ColumnEnum::columnValue, comparator);
    }

    /**
     * Returns a comparator comparing enum constants with their {@link __ColumnEnum#columnValue() columnValue}s in
     * {@link Comparator#naturalOrder() natural order}.
     *
     * @param <E> enum type parameter
     * @param <T> column value type parameter
     * @return a comparator comparing enum constants with their {@link __ColumnEnum#columnValue() columnValue}s using
     *         {@link Comparator#naturalOrder() natural order}
     */
    static <
            E extends __ColumnEnum<?, T>,
            T extends Comparable<? super T>
            >
    Comparator<E> comparingColumnValue() {
        return comparingColumnValue(Comparator.naturalOrder());
    }

    // -------------------------------------------------------------------------------------------- java.util.Comparator
    default int compare(final E o1, final E o2) {
        Objects.requireNonNull(o1, "o1 is null");
        Objects.requireNonNull(o2, "o2 is null");
        return o1.columnValue().compareTo(o2.columnValue());
    }
}
