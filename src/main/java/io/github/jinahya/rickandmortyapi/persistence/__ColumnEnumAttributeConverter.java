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

import jakarta.persistence.AttributeConverter;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

/**
 * An abstract attribute converter for {@link __ColumnEnum}s.
 *
 * @param <E> enum type parameter
 * @param <T> column type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class __ColumnEnumAttributeConverter<E extends Enum<E> & __ColumnEnum<E, T>, T>
        implements AttributeConverter<E, T> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance for converting the specified enum class.
     *
     * @param enumClass the enum class.
     * @see #enumClass
     */
    protected __ColumnEnumAttributeConverter(final Class<E> enumClass) {
        super();
        this.enumClass = Objects.requireNonNull(enumClass, "enumClass is null");
    }

    // -------------------------------------------------------------------------- jakarta.persistence.AttributeConverter
    @Nullable
    @Override
    public final T convertToDatabaseColumn(@Nullable final E attribute) {
        return Optional.ofNullable(attribute)
                .map(__ColumnEnum::columnValue)
                .orElse(null);
    }

    @Nullable
    @Override
    public final E convertToEntityAttribute(@Nullable final T dbData) {
        return Optional.ofNullable(dbData)
                .map(v -> __ColumnEnumUtils.valueOfColumnValue(enumClass, v))
                .orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The enum class to convert.
     */
    protected final Class<E> enumClass;
}
