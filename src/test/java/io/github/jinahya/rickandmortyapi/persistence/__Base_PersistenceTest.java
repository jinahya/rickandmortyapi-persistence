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

import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@AddBeanClasses({
        __PersistenceUnitProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class __Base_PersistenceTest<T extends __Base> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for testing the specified class.
     *
     * @param entityClass the class to test.
     */
    __Base_PersistenceTest(final Class<T> entityClass) {
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
    }

    // --------------------------------------------------------------------------------------------------- entityManager

    /**
     * Returns the result of the specified functino applied to an entity manager.
     *
     * @param function the function.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     * @see __JakartaPersistence_TestUtils#applyEntityManager(EntityManagerFactory, Function)
     * @see #acceptEntityManager(Consumer)
     */
    final <R> R applyEntityManager(@Nonnull final Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return __JakartaPersistence_TestUtils.applyEntityManager(
                entityManagerFactory,
                function
        );
    }

    /**
     * Accepts an entity manager to the specified consumer.
     *
     * @param consumer the consumer.
     * @see #applyEntityManager(Function)
     */
    final void acceptEntityManager(@NonNull final Consumer<? super EntityManager> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        applyEntityManager(em -> {
            consumer.accept(em);
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<T> entityClass;

    @Inject
    private EntityManagerFactory entityManagerFactory;
}
