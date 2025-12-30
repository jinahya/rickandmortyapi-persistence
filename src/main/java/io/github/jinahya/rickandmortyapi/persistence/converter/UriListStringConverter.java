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

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * An attribute converter for converting {@link List}{@code <}{@link URI}{@code >} attributes
 * to and from strings.
 *
 * <p>This converter serializes a list of URIs into a comma-separated string for database
 * storage and deserializes it back to a list when reading from the database. The delimiter
 * used is {@value #DELIMITER}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see UriStringConverter
 * @see UrlListStringConverter
 */
@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class UriListStringConverter
        implements AttributeConverter<List<URI>, String> {

    static final String DELIMITER = ",";

    private static final AttributeConverter<URI, String> CONVERTER = new UriStringConverter();

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of this converter.
     */
    public UriListStringConverter() {
        super();
    }

    /**
     * Converts the given list of URIs to a database column value (comma-separated string).
     *
     * <p>This method serializes a list of URIs into a single string by converting each URI
     * to its string representation and joining them with {@value #DELIMITER}. If the input
     * list is {@code null}, this method returns {@code null}.
     *
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database column, or {@code null} if
     *         the input is {@code null}
     */
    @Override
    public String convertToDatabaseColumn(final List<URI> attribute) {
        return Optional.ofNullable(attribute)
                .map(l -> l.stream()
                        .map(CONVERTER::convertToDatabaseColumn)
                        .collect(Collectors.joining(DELIMITER))
                )
                .orElse(null);
    }

    /**
     * Converts the given database column value (comma-separated string) to a list of URIs.
     *
     * <p>This method deserializes a comma-separated string from the database into a list
     * of URIs by splitting on {@value #DELIMITER} and converting each segment to a URI
     * object. If the input string is {@code null}, this method returns {@code null}.
     *
     * @param dbData the data from the database column to be converted
     * @return the converted value to be stored in the entity attribute, or {@code null} if
     *         the input is {@code null}
     */
    @Override
    public List<URI> convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                .map(dd -> Arrays.stream(dd.split(DELIMITER))
                        .map(CONVERTER::convertToEntityAttribute)
                        .collect(Collectors.toCollection(ArrayList::new))
                )
                .orElse(null);
    }
}
