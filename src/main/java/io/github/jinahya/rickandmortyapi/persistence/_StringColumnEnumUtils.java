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

@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
final class _StringColumnEnumUtils {

    static <
            E extends Enum<E> & _StringColumnEnum<E>
            >
    E valueOfColumnValue(final Class<E> enumClass, final String columnValue) {
        return __ColumnEnumUtils.valueOfColumnValue(enumClass, columnValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private _StringColumnEnumUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
