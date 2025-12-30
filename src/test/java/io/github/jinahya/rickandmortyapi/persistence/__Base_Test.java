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

import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

import java.beans.Introspector;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract class for testing classes extends {@link __Base} class.
 *
 * @param <T> base type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Slf4j
abstract class __Base_Test<T extends __Base> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for testing the specified type class.
     *
     * @param typeClass the type class to test.
     */
    __Base_Test(final Class<T> typeClass) {
        super();
        this.typeClass = Objects.requireNonNull(typeClass, "typeClass is null");
    }

    // -------------------------------------------------------------------------------------------------------- toString
    @Test
    void toString_NotBlank_() {
        // ------------------------------------------------------------------------------------------------------- given
        final var instance = newTypeInstance();
        // -------------------------------------------------------------------------------------------------------- when
        final var string = instance.toString();
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(string).isNotBlank();
    }

    // ----------------------------------------------------------------------------------------------- equals / hashCode
    @Test
    void equals_Verity_() {
        final var verifier = createEqualsVerifier();
        configureEqualsVerifier(verifier);
        verifier.verify();
    }

    SingleTypeEqualsVerifierApi<T> createEqualsVerifier() {
        return EqualsVerifier.forClass(typeClass);
    }

    SingleTypeEqualsVerifierApi<T> configureEqualsVerifier(final SingleTypeEqualsVerifierApi<T> verifier) {
        return verifier;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void accessors__() throws Exception {
        final var instance = newTypeInstance();
        final var info = Introspector.getBeanInfo(typeClass, Introspector.USE_ALL_BEANINFO);
        for (final var descriptor : info.getPropertyDescriptors()) {
            final var type = descriptor.getPropertyType();
            final var name = descriptor.getName();
            final var capitalized = name.substring(0, 1).toUpperCase() + name.substring(1);
            final var reader = Optional.ofNullable(descriptor.getReadMethod()).orElseGet(() -> {
                try {
                    return typeClass.getDeclaredMethod("get" + capitalized, type);
                } catch (final NoSuchMethodException nsme) {
                    return null;
                }
            });
            if (reader == null) {
                continue;
            }
            if (!reader.canAccess(instance)) {
                reader.setAccessible(true);
            }
            final var value = reader.invoke(instance);
            final var writer = Optional.ofNullable(descriptor.getWriteMethod()).orElseGet(() -> {
                try {
                    return typeClass.getDeclaredMethod("set" + capitalized, type);
                } catch (final NoSuchMethodException nsme) {
                    return null;
                }
            });
            if (writer == null) {
                continue;
            }
            if (!writer.canAccess(instance)) {
                writer.setAccessible(true);
            }
            writer.invoke(instance, value);
        }
    }

    // ------------------------------------------------------------------------------------------------------- typeClass
    T newTypeInstance() {
        try {
            final var constructor = typeClass.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to instantiate " + typeClass, roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<T> typeClass;
}
