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

class Location_Test
        extends _BaseEntity_Test<Location, Integer> {

    static final Location RED = new Location().id(1);

    static final Location BLUE = new Location().id(2);

    // -----------------------------------------------------------------------------------------------------------------
    Location_Test() {
        super(Location.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    SingleTypeEqualsVerifierApi<Location> configureEqualsVerifier(
            final SingleTypeEqualsVerifierApi<Location> verifierApi) {
        return super.configureEqualsVerifier(verifierApi)
                .suppress(Warning.SURROGATE_KEY)
                .withPrefabValues(Character.class, Character_Test.RED, Character_Test.BLUE)
                ;
    }
}
