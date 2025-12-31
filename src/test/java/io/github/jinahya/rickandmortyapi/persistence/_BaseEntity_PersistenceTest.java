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

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Root;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@AddBeanClasses({
        __PersistenceUnitProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _BaseEntity_PersistenceTest<ENTITY extends _BaseEntity<ID>, ID>
        extends __Base_PersistenceTest<ENTITY> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for testing the specified entity class and it's id class.
     *
     * @param entityClass the entity class.
     * @param idClass     the id class of the {@code entityClass}.
     */
    _BaseEntity_PersistenceTest(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass);
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void selectAll__() {
        applyEntityManager(em -> {
            final var entityList = __JakartaPersistence_TestUtils.selectAll(
                    em,
                    entityClass,
                    this::selectAll__
            );
            selectAll__(em, entityList);
            return entityList;
        });
    }

    /**
     * Gets notified from {@link #selectAll__()} method that the specified root is going to be selected.
     *
     * @param root the root of {@link #entityClass}.
     */
    void selectAll__(final Root<ENTITY> root) {
        // empty
    }

    /**
     * Gets notified from {@link #selectAll__()} method with an entity manager and a list of slected entities.
     *
     * @param entityManager the entity manager used to select entities.
     * @param entityList    the list of selected entities.
     */
    void selectAll__(final EntityManager entityManager, final List<ENTITY> entityList) {
        assertThat(entityList)
                .isNotNull()
                .isNotEmpty()
                .doesNotContainNull()
                .doesNotHaveDuplicates()
        ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<ID> idClass;
}
