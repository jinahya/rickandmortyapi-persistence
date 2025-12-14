package io.github.jinahya.rickmortyapi.persistence;

import com.github.jinahya.persistence.mapped.test.___JakartaValidation_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Character_PersistenceIT extends _BaseEntity_PersistenceIT<Character, Integer> {

    Character_PersistenceIT() {
        super(Character.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void selectAll() {
        applyEntityManager(em -> {
            for (int id = 1; id <= 826; id++) {
                final var result = em.find(entityClass, id);
                log.debug("[{}]: {}", id, result);
                assertThat(result).as("id: %d", id).isNotNull();
                ___JakartaValidation_TestUtils.requireValid(result);
            }
            return null;
        });
    }
}
