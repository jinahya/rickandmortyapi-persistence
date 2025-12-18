package io.github.jinahya.rickmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _BaseEntity_Test<ENTITY extends _BaseEntity, ID> extends __BaseEntity__<ENTITY, ID> {

    _BaseEntity_Test(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // ------------------------------------------------------------------------------------ toString
    @Test
    void toString_NotBlank_() {
        // ----------------------------------------------------------------------------------- given
        final var instance = newEntityInstance();
        // ------------------------------------------------------------------------------------ when
        final var string = instance.toString();
        // ------------------------------------------------------------------------------------ then
        assertThat(string).isNotBlank();
    }

    // --------------------------------------------------------------------------- equals / hashCode
    @Test
    void equals_Verity_() {
        final var verifier = createEqualsVerifier();
        configureEqualsVerifier(verifier);
        verifier.verify();
    }

    SingleTypeEqualsVerifierApi<ENTITY> createEqualsVerifier() {
        return EqualsVerifier.forClass(entityClass);
    }

    SingleTypeEqualsVerifierApi<ENTITY> configureEqualsVerifier(final SingleTypeEqualsVerifierApi<ENTITY> verifier) {
        return verifier;
    }

    // --------------------------------------------------------------------------- super.entityClass
    ENTITY newEntityInstance() {
        try {
            final var constructor = entityClass.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to instantiate " + entityClass, roe);
        }
    }
}