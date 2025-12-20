package io.github.jinahya.rickandmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

import java.beans.Introspector;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
abstract class __Base_Test<T extends __Base> {

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

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void accessors__() throws Exception {
        final var typeInstance = newTypeInstance();
        final var beanInfo = Introspector.getBeanInfo(typeClass, Introspector.USE_ALL_BEANINFO);
        for (final var propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            final var propertyType = propertyDescriptor.getPropertyType();
            final var propertyName = propertyDescriptor.getName();
            final var capitalizedPropertyName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
            final var readMethod = Optional.ofNullable(propertyDescriptor.getReadMethod()).orElseGet(() -> {
                try {
                    return typeClass.getDeclaredMethod("get" + capitalizedPropertyName, propertyType);
                } catch (final NoSuchMethodException nsme) {
                    return null;
                }
            });
            if (readMethod == null) {
                continue;
            }
            if (!readMethod.canAccess(typeInstance)) {
                readMethod.setAccessible(true);
            }
            final var propertyValue = readMethod.invoke(typeInstance);
            final var writeMethod = Optional.ofNullable(propertyDescriptor.getWriteMethod()).orElseGet(() -> {
                try {
                    return typeClass.getDeclaredMethod("set" + capitalizedPropertyName, propertyType);
                } catch (final NoSuchMethodException nsme) {
                    return null;
                }
            });
            if (writeMethod == null) {
                continue;
            }
            if (!writeMethod.canAccess(typeInstance)) {
                writeMethod.setAccessible(true);
            }
            writeMethod.invoke(typeInstance, propertyValue);
        }
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
