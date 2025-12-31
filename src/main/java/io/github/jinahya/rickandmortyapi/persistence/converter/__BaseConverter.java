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

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * An abstract base attribute converter.
 *
 * @param <X> attribute type parameter
 * @param <Y> db data type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class __BaseConverter<X, Y>
        implements AttributeConverter<X, Y> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance with the specified formatter and parser.
     *
     * @param formatter the formatter for formatting attributes to db data.
     * @param parser    the parser for parsing db data to attributes
     */
    protected __BaseConverter(final Function<? super X, ? extends Y> formatter,
                              final Function<? super Y, ? extends X> parser) {
        super();
        this.formatter = Objects.requireNonNull(formatter, "formatter is null");
        this.parser = Objects.requireNonNull(parser, "parser is null");
    }

    // -------------------------------------------------------------------------- jakarta.persistence.AttributeConverter
    @Override
    public final Y convertToDatabaseColumn(final X attribute) {
        return Optional.ofNullable(attribute)
                .map(formatter)
                .orElse(null);
    }

    @Override
    public final X convertToEntityAttribute(final Y dbData) {
        return Optional.ofNullable(dbData)
                .map(parser)
                .orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final Function<? super X, ? extends Y> formatter;

    private final Function<? super Y, ? extends X> parser;
}
