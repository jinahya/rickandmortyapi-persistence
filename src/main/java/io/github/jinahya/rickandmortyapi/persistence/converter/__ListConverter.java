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

import java.util.List;

/**
 * An interface for list converters.
 *
 * <p>This interface extends {@link AttributeConverter} to provide a type-safe contract
 * for converters that work with lists. It serves as a marker interface for converters
 * that handle conversions between {@link List} collections and database column types.
 *
 * @param <T> element type parameter - the type of elements in the list
 * @param <Y> database column type parameter - the type stored in the database column
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S114" // Interface names should comply with a naming convention
})
public interface __ListConverter<T, Y>
        extends AttributeConverter<List<T>, Y> {

}
