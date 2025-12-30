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
import java.util.Optional;

/**
 * An attribute converter for converting {@link URL} attributes to and from strings.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see UriListStringConverter
 * @see UrlStringConverter
 */
@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UrlStringConverter
        implements AttributeConverter<URL, String> {

    private static final AttributeConverter<URI, String> CONVERTER = new UriStringConverter();

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public UrlStringConverter() {
        super();
    }

    // -------------------------------------------------------------------------- jakarta.persistence.AttributeConverter
    @Override
    public String convertToDatabaseColumn(final URL attribute) {
        return Optional.ofNullable(attribute)
                .map(a -> {
                    try {
                        return a.toURI();
                    } catch (final URISyntaxException urise) {
                        throw new RuntimeException("failed to convert " + attribute + " to URI", urise);
                    }
                })
                .map(CONVERTER::convertToDatabaseColumn)
                .orElse(null);
    }

    @Override
    public URL convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                .map(CONVERTER::convertToEntityAttribute)
                .map(dd -> {
                    try {
                        return dd.toURL();
                    } catch (final MalformedURLException murle) {
                        throw new RuntimeException("failed to convert " + dbData + " to URL", murle);
                    }
                })
                .orElse(null);
    }
}
