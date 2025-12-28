package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

abstract class __ColumnEnumConverter_Test<
        C extends __ColumnEnumAttributeConverter<E, T>,
        E extends Enum<E> & __ColumnEnum<E, T>,
        T
        > {

    __ColumnEnumConverter_Test(final Class<C> converterClass, final Class<E> enumClass, final Class<T> columnClass) {
        super();
        this.converterClass = Objects.requireNonNull(converterClass, "converterClass is null");
        this.enumClass = Objects.requireNonNull(enumClass, "enumClass is null");
        this.columnClass = Objects.requireNonNull(columnClass, "columnClass is null");
    }

    @Test
    void convertToDatabaseColumn__() {
        final var instance = newConverterInstance();
        for (final var enumConstant : enumClass.getEnumConstants()) {
            final var column = instance.convertToDatabaseColumn(enumConstant);
            assertThat(column).isNotNull();
        }
    }

    @Test
    void convertToEntityAttribute__() {
        final var instance = newConverterInstance();
        for (final var enumConstant : enumClass.getEnumConstants()) {
            final var entityAttribute = instance.convertToEntityAttribute(enumConstant.columnValue());
            assertThat(entityAttribute).isSameAs(enumConstant);
        }
    }

    // -------------------------------------------------------------------------------------------------- converterClass
    C newConverterInstance() {
        try {
            final var constructor = converterClass.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    final Class<C> converterClass;

    final Class<E> enumClass;

    final Class<T> columnClass;
}
