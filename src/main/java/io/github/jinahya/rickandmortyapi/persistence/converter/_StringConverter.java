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

import java.util.Optional;
import java.util.function.Function;

/**
 * An attribute converter for converting an attribute of a specific types to and from strings.
 *
 * @param <X> attribute type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _StringConverter<X> extends __BaseConverter<X, String> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance with the specified formatter and parser.
     *
     * @param formatter the formatter for formatting attributes to strings
     * @param parser    the parser for parsing strings to attributes
     */
    protected _StringConverter(final Function<? super X, String> formatter,
                               final Function<? super String, ? extends X> parser) {
        super(formatter,
              dd -> Optional.of(dd)
                      .map(String::strip)
                      .filter(v -> !v.isBlank())
                      .map(parser)
                      .orElse(null));
    }
}
