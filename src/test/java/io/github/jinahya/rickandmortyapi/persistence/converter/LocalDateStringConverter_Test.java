package io.github.jinahya.rickandmortyapi.persistence.converter;

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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDateStringConverter_Test extends _StringConverter_Test<LocalDateStringConverter, LocalDate> {

    LocalDateStringConverter_Test() {
        super(LocalDateStringConverter.class, LocalDate.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void convertToDatabaseColumn__() {
        final var instance = newConverterInstance();
        final var attribute = LocalDate.now();
        final var dbData = instance.convertToDatabaseColumn(attribute);
        assertThat(dbData)
                .isEqualTo(attribute.toString())
                .isEqualTo(attribute.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Test
    void convertToEntityAttribute__() {
        final var instance = newConverterInstance();
        final var dbData = LocalDate.now().toString();
        final var attribute = instance.convertToEntityAttribute(dbData);
        assertThat(attribute).isEqualTo(dbData.formatted(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
