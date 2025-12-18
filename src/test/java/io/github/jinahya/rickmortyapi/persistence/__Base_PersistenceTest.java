package io.github.jinahya.rickmortyapi.persistence;

import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Objects;
import java.util.function.Function;

@AddBeanClasses({
        __PersistenceUnitProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class __Base_PersistenceTest<T> {

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
     * @see __JakartaPersistenceTestUtils#applyEntityManager(EntityManagerFactory, Function)
     */
    final <R> R applyEntityManager(final @Nonnull Function<? super EntityManager, ? extends R> function) {
        return __JakartaPersistenceTestUtils.applyEntityManager(entityManagerFactory, function);
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<T> entityClass;

    @Inject
    private EntityManagerFactory entityManagerFactory;
}
