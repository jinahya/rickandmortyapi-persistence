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

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A utility class for {@link __ColumnEnum}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class __ColumnEnumUtils {

    private static final Map<Class<?>, Map<Object, Enum<?>>> CACHE = new ConcurrentHashMap<>();

    static <
            E extends Enum<E> & __ColumnEnum<E, T>,
            T
            >
    E valueOfColumnValue(final Class<E> enumClass, final T columnValue) {
        if (!Objects.requireNonNull(enumClass, "enumClass is null").isEnum()) {
            throw new IllegalArgumentException("not an enum: " + enumClass);
        }
        Objects.requireNonNull(columnValue, "columnValue is null");
        return enumClass.cast(
                CACHE.computeIfAbsent(enumClass, k -> new ConcurrentHashMap<>())
                        .computeIfAbsent(columnValue, k -> {
                            for (final var enumConstants : enumClass.getEnumConstants()) {
                                if (enumConstants.columnValue().equals(k)) {
                                    return enumConstants;
                                }
                            }
                            throw new IllegalArgumentException(
                                    "no enum constant found for column value: " + k + " in " + enumClass
                            );
                        })
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private __ColumnEnumUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
