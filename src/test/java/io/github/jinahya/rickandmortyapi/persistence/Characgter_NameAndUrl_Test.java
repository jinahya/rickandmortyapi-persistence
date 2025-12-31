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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Characgter_NameAndUrl_Test
        extends __Base_Test<Character_NameAndUrl> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Characgter_NameAndUrl_Test() {
        super(Character_NameAndUrl.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void ATTRIBUTE_NAME_NAME__() {
        assertThat(Character_NameAndUrl.ATTRIBUTE_NAME_NAME)
                .isEqualTo(Character_NameAndUrl_.NAME);
    }

    @Test
    void ATTRIBUTE_NAME_URL__() {
        assertThat(Character_NameAndUrl.ATTRIBUTE_NAME_URL)
                .isEqualTo(Character_NameAndUrl_.URL);
    }
}
