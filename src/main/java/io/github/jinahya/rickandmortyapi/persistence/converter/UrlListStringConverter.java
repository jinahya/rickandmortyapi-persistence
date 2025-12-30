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

/**
 * An attribute converter for converting {@link List}{@code <}{@link URL}{@code >} attributes
 * to and from strings.
 *
 * <p>This converter serializes a list of URLs into a comma-separated string for database
 * storage and deserializes it back to a list when reading from the database. The conversion
 * uses {@link UriListStringConverter#DELIMITER} as the separator between URLs.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see UrlStringConverter
 * @see UriListStringConverter
 */
@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UrlListStringConverter
        implements AttributeConverter<List<URL>, String> {

    private static final AttributeConverter<URL, String> CONVERTER = new UrlStringConverter();

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of this converter.
     */
    public UrlListStringConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Converts the given list of URLs to a database column value (comma-separated string).
     *
     * <p>This method serializes a list of URLs into a single string by converting each URL
     * to its string representation and joining them with {@link UriListStringConverter#DELIMITER}.
     * If the input list is {@code null}, this method returns {@code null}.
     *
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database column, or {@code null} if
     *         the input is {@code null}
     */
    @Override
    public String convertToDatabaseColumn(final List<URL> attribute) {
        return Optional.ofNullable(attribute)
                .map(a -> a.stream()
                        .map(CONVERTER::convertToDatabaseColumn)
                        .collect(Collectors.joining(UriListStringConverter.DELIMITER)))
                .orElse(null);
    }

    /**
     * Converts the given database column value (comma-separated string) to a list of URLs.
     *
     * <p>This method deserializes a comma-separated string from the database into a list
     * of URLs by splitting on {@link UriListStringConverter#DELIMITER} and converting each
     * segment to a URL object. If the input string is {@code null}, this method returns
     * {@code null}.
     *
     * @param dbData the data from the database column to be converted
     * @return the converted value to be stored in the entity attribute, or {@code null} if
     *         the input is {@code null}
     */
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
