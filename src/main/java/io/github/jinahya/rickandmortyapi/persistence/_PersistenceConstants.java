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
final class _PersistenceConstants {

    /**
     * The number of all characters which is {@value}.
     *
     * @see <a href="https://rickandmortyapi.com/documentation">Documentation</a> (rickandmortyapi.com)
     */
    public static final int NUMBER_OF_ALL_CHARACTERS = 826;

    /**
     * The number of all episodes which is {@value}.
     *
     * @see <a href="https://rickandmortyapi.com/documentation">Documentation</a> (rickandmortyapi.com)
     */
    public static final int NUMBER_OF_ALL_EPISODES = 51;

    /**
     * The number of all locations which is {@value}.
     *
     * @see <a href="https://rickandmortyapi.com/documentation">Documentation</a> (rickandmortyapi.com)
     */
    public static final int NUMBER_OF_ALL_LOCATIONS = 126;

    // -----------------------------------------------------------------------------------------------------------------
    private _PersistenceConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
