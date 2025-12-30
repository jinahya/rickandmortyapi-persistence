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

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

abstract class __ColumnEnumConverter_Test<
        C extends __ColumnEnumAttributeConverter<E, T>,
        E extends Enum<E> & __ColumnEnum<E, T>,
        T
        > {

    __ColumnEnumConverter_Test(final Class<C> converterClass, final Class<E> enumClass, final Class<T> columnClass) {
        super();
        this.converterClass = Objects.requireNonNull(converterClass, "converterClass is null");
        this.enumClass = Objects.requireNonNull(enumClass, "enumClass is null");
        this.columnClass = Objects.requireNonNull(columnClass, "columnClass is null");
    }

    @Test
    void convertToDatabaseColumn__() {
        final var instance = newConverterInstance();
        for (final var enumConstant : enumClass.getEnumConstants()) {
            final var column = instance.convertToDatabaseColumn(enumConstant);
            assertThat(column).isNotNull();
        }
    }

    @Test
    void convertToEntityAttribute__() {
        final var instance = newConverterInstance();
        for (final var enumConstant : enumClass.getEnumConstants()) {
            final var entityAttribute = instance.convertToEntityAttribute(enumConstant.columnValue());
            assertThat(entityAttribute).isSameAs(enumConstant);
        }
    }

    // -------------------------------------------------------------------------------------------------- converterClass
    C newConverterInstance() {
        try {
            final var constructor = converterClass.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    final Class<C> converterClass;

    final Class<E> enumClass;

    final Class<T> columnClass;
}
