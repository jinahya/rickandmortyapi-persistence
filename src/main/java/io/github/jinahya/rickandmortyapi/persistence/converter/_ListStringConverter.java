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

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _ListStringConverter<T>
        extends _StringConverter<List<T>>
        implements __ListConverter<T, String> {

    // -----------------------------------------------------------------------------------------------------------------
    protected _ListStringConverter(final AttributeConverter<T, String> converter,
                                   final String delimiter) {
        super(a -> a.stream()
                      .filter(Objects::nonNull)
                      .map(converter::convertToDatabaseColumn)
                      .collect(Collectors.joining(delimiter)),
              dd -> Arrays.stream(dd.split(delimiter))
                      .map(converter::convertToEntityAttribute)
                      .toList()
        );
    }
}
