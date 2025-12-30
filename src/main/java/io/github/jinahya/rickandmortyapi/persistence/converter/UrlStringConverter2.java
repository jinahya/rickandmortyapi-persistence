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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UrlStringConverter2 extends _StringConverter<URL> {

    private static final AttributeConverter<URI, String> CONVERTER = new UriStringConverter();

    static String toDatabaseColumn(final URL attribute) {
        assert attribute != null;
        try {
            return CONVERTER.convertToDatabaseColumn(attribute.toURI());
        } catch (final URISyntaxException urise) {
            throw new RuntimeException(urise);
        }
    }

    static URL toEntityAttribute(final String dbData) {
        assert dbData != null;
        assert !dbData.isBlank();
        try {
            return CONVERTER.convertToEntityAttribute(dbData).toURL();
        } catch (final MalformedURLException murle) {
            throw new RuntimeException(murle);
        }
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public UrlStringConverter2() {
        super(UrlStringConverter2::toDatabaseColumn, UrlStringConverter2::toEntityAttribute);
    }
}
