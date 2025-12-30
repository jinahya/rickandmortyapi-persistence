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

import io.github.jinahya.rickandmortyapi.persistence.converter._StringConverter_Test;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class Episode_AirDateConverter_Test extends _StringConverter_Test<Episode_AirDateConverter, LocalDate> {

    Episode_AirDateConverter_Test() {
        super(Episode_AirDateConverter.class, LocalDate.class);
    }

    @Test
    void convertToEntityAttribute__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var instance = newConverterInstance();
        final var dbData = "December 2, 2013";
        // -------------------------------------------------------------------------------------------------------- when
        final var attribute = instance.convertToEntityAttribute(dbData);
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(attribute).isEqualTo(LocalDate.of(2013, 12, 2));
    }

    @Test
    void convertToDatabaseColumn__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var instance = newConverterInstance();
        final var attribute = LocalDate.of(2013, 12, 2);
        // -------------------------------------------------------------------------------------------------------- when
        final var dbData = instance.convertToDatabaseColumn(attribute);
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(dbData).isEqualTo("December 2, 2013");
    }
}
