package io.github.jinahya.rickmortyapi.persistence;

import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@AddBeanClasses({
        __PersistenceUnitProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _BaseEntity_PersistenceTest<ENTITY extends _BaseEntity, ID> extends __BaseEntity__<ENTITY, ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    _BaseEntity_PersistenceTest(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
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

    // --------------------------------------------------------------------------------------------------- entityManager

    /**
     * Returns the result of the specified functino applied to an entity manager.
     *
     * @param function the function.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     */
    final <R> R applyEntityManager(final @Nonnull Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        try (final var entityManager = entityManagerFactory.createEntityManager()) {
            return function.apply(entityManager);
        }
    }

    // ------------------------------------------------------------------------------------------------------ entityName
    final String getEntityName() {
        var result = entityName;
        if (result == null) {
            entityName = result = entityManagerFactory.getMetamodel().entity(entityClass).getName();
        }
        return result;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Inject
    private EntityManagerFactory entityManagerFactory;

    private volatile String entityName;
}
