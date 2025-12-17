package io.github.jinahya.rickmortyapi.persistence;

import com.github.jinahya.persistence.mapped.test.___JakartaValidation_TestUtils;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _BaseEntity_PersistenceIT<ENTITY extends __BaseEntity, ID> extends _BaseEntity_Persistence_<ENTITY, ID> {

    _BaseEntity_PersistenceIT(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void selectAll__() {
        final var selected = applyEntityManager(em -> {
            final var builder = em.getCriteriaBuilder();
            final var query = builder.createQuery(entityClass);
            final var root = query.from(entityClass);
            query.select(root);
            final var resultList = em.createQuery(query).getResultList();
            selectAll__(em, resultList);
            return resultList;
        });
        assertThat(selected).isNotEmpty();
    }

    void selectAll__(final EntityManager entityManager, final List<ENTITY> all) {
        all.forEach(e -> {
            ___JakartaValidation_TestUtils.requireValid(e);
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    final EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__PersistenceProducer.__itPU
    @Inject
    private EntityManagerFactory entityManagerFactory;
}
