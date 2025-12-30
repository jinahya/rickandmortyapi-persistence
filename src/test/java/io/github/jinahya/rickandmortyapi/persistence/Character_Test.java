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

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Character_Test extends _BaseEntity_Test<Character, Integer> {

    static final Character RED = new Character().id(1);

    static final Character BLUE = new Character().id(2);

    // -----------------------------------------------------------------------------------------------------------------
    Character_Test() {
        super(Character.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    SingleTypeEqualsVerifierApi<Character> configureEqualsVerifier(
            final SingleTypeEqualsVerifierApi<Character> verifier) {
        return super.configureEqualsVerifier(verifier)
                .suppress(Warning.SURROGATE_KEY)
                .withPrefabValues(Episode.class, Episode_Test.RED, Episode_Test.BLUE)
                .withPrefabValues(Location.class, Location_Test.RED, Location_Test.BLUE);
    }
}
