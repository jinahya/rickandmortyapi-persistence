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

import io.github.jinahya.rickandmortyapi.persistence.mapped.__ColumnEnum;
import io.github.jinahya.rickandmortyapi.persistence.mapped.__ColumnEnumAttributeConverter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

abstract class __ColumnEnumAttributeConverter_Test<
        C extends __ColumnEnumAttributeConverter<X, Y>,
        X extends Enum<X> & __ColumnEnum<X, Y>,
        Y
        >
        extends ___EnumAttributeConverter_Test<C, X, Y> {

    // -----------------------------------------------------------------------------------------------------------------
    __ColumnEnumAttributeConverter_Test(final Class<C> converterClass, final Class<X> attributeClass,
                                        final Class<Y> columnClass) {
        super(converterClass, attributeClass, columnClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void convertToDatabaseColumn__() {
        final var instance = newConverterInstance();
        for (final var enumConstant : attributeClass.getEnumConstants()) {
            final var column = instance.convertToDatabaseColumn(enumConstant);
            assertThat(column).isNotNull();
        }
    }

    @Test
    void convertToEntityAttribute__() {
        final var instance = newConverterInstance();
        for (final var enumConstant : attributeClass.getEnumConstants()) {
            final var entityAttribute = instance.convertToEntityAttribute(enumConstant.columnValue());
            assertThat(entityAttribute).isSameAs(enumConstant);
        }
    }
}
