package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EntityManager;
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
abstract class _BaseEntity_PersistenceTest<ENTITY extends _BaseEntity, ID> extends __Base_PersistenceTest<ENTITY> {

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
            final var entityList = __JakartaPersistenceTestUtils.selectAll(em, entityClass);
            selectAll__(em, entityList);
            return entityList;
        });
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
                .allSatisfy(e -> {
                    __JakartaValidationTestUtils.requireValid(e);
                });
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<ID> idClass;
}
