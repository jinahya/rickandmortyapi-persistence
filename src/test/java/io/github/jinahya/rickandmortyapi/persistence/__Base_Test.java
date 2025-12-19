package io.github.jinahya.rickandmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
abstract class __Base_Test<T extends _BaseEntity> {

    __Base_Test(final Class<T> typeClass) {
        super();
        this.typeClass = Objects.requireNonNull(typeClass, "typeClass is null");
    }

    // -------------------------------------------------------------------------------------------------------- toString
    @Test
    void toString_NotBlank_() {
        // ------------------------------------------------------------------------------------------------------- given
        final var instance = newTypeInstance();
        // -------------------------------------------------------------------------------------------------------- when
        final var string = instance.toString();
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(string).isNotBlank();
    }

    // ----------------------------------------------------------------------------------------------- equals / hashCode
    @Test
    void equals_Verity_() {
        final var verifier = createEqualsVerifier();
        configureEqualsVerifier(verifier);
        verifier.verify();
    }

    SingleTypeEqualsVerifierApi<T> createEqualsVerifier() {
        return EqualsVerifier.forClass(typeClass);
    }

    SingleTypeEqualsVerifierApi<T> configureEqualsVerifier(final SingleTypeEqualsVerifierApi<T> verifier) {
        return verifier;
    }

    // ------------------------------------------------------------------------------------------------------- typeClass
    T newTypeInstance() {
        try {
            final var constructor = typeClass.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to instantiate " + typeClass, roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<T> typeClass;
}
