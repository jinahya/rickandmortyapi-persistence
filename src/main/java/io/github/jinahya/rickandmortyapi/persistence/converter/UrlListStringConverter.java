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
import jakarta.persistence.Converter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UrlListStringConverter
        implements AttributeConverter<List<URL>, String> {

    private static final AttributeConverter<URL, String> CONVERTER = new UrlStringConverter();

    // -----------------------------------------------------------------------------------------------------------------
    public UrlListStringConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String convertToDatabaseColumn(final List<URL> attribute) {
        return Optional.ofNullable(attribute)
                .map(a -> a.stream()
                        .map(CONVERTER::convertToDatabaseColumn)
                        .collect(Collectors.joining(UriListStringConverter.DELIMITER)))
                .orElse(null);
    }

    @Override
    public List<URL> convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                .map(dd -> Arrays.stream(dd.split(UriListStringConverter.DELIMITER))
                        .map(CONVERTER::convertToEntityAttribute)
                        .collect(Collectors.toCollection(ArrayList::new))
                )
                .orElse(null);
    }
}
