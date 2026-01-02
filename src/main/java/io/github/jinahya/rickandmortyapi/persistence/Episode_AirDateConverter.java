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

import io.github.jinahya.rickandmortyapi.persistence.converter._StringConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * An attribute converter for converting {@value Episode_#AIR_DATE} attribute to
 * {@value Episode_AirDateConverter#PATTERN} string.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Episode_AirDateConverter
        extends _StringConverter<LocalDate> {

    // -----------------------------------------------------------------------------------------------------------------
    private static final String PATTERN = "MMMM d, uuuu";

    /**
     * A date time formatter for parsing {@value #PATTERN} pattern.
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN, Locale.ENGLISH);

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public Episode_AirDateConverter() {
        super(FORMATTER::format, v -> LocalDate.parse(v, FORMATTER));
    }
}
