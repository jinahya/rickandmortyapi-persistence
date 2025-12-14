package io.github.jinahya.rickmortyapi.persistence;

import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
abstract class _BaseEntity_PersistenceIT<ENTITY extends _BaseEntity, ID> extends _BaseEntity_Persistence_<ENTITY, ID> {

    _BaseEntity_PersistenceIT(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void selectRandom() {
        final var selected = applyEntityManager(em -> {
            return ___JakartaPersistence_TestUtils.selectRandom(em, entityClass);
        });
        log.debug("selected: {}", selected.orElse(null));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__PersistenceProducer.__itPU
    @Inject
    private EntityManagerFactory entityManagerFactory;
}
