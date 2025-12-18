package io.github.jinahya.rickmortyapi.persistence;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@AddBeanClasses({
        __PersistenceProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _BaseEntity_Persistence_<ENTITY extends _BaseEntity, ID> extends __BaseEntity__<ENTITY, ID> {

    _BaseEntity_Persistence_(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -------------------------------------------------------------------------------------------- entityManagerFactory
    abstract EntityManagerFactory getEntityManagerFactory();

    /**
     * Applies an injected instance of {@link EntityManagerFactory} to the specified function, and returns the result.
     *
     * @param function the function.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     * @see #acceptEntityManagerFactory(Consumer)
     */
    protected final <R> R applyEntityManagerFactory(
            final @Nonnull Function<? super EntityManagerFactory, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(getEntityManagerFactory());
    }

    /**
     * Accepts an injected instance of {@link EntityManagerFactory} to the specified consumer.
     *
     * @param consumer the consumer.
     * @see #applyEntityManagerFactory(Function)
     */
    protected final void acceptEntityManagerFactory(final @Nonnull Consumer<? super EntityManagerFactory> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        applyEntityManagerFactory(emf -> {
            consumer.accept(emf);
            return null;
        });
    }

    /**
     * Applies an injected instance of {@link EntityManager} to the specified function, and returns the result.
     *
     * @param function the function.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     * @see #acceptEntityManager(Consumer)
     */
    protected final <R> R applyEntityManager(final @Nonnull Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return applyEntityManagerFactory(emf -> {
            try (final var entityManager = emf.createEntityManager()) {
                return function.apply(entityManager);
            }
        });
    }

    /**
     * Accepts an injected instance of {@link EntityManager} to the specified consumer.
     *
     * @param consumer the consumer.
     * @see #applyEntityManager(Function)
     */
    protected final void acceptEntityManager(final @Nonnull Consumer<? super EntityManager> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        applyEntityManager(em -> {
            consumer.accept(em);
            return null;
        });
    }
}
