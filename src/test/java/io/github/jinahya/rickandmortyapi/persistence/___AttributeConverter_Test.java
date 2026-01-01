package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.AttributeConverter;

import java.util.Objects;

abstract class ___AttributeConverter_Test<
        C extends AttributeConverter<X, Y>,
        X,
        Y
        > {

    ___AttributeConverter_Test(final Class<C> converterClass, final Class<X> attributeClass,
                               final Class<Y> columnClass) {
        super();
        this.converterClass = Objects.requireNonNull(converterClass, "converterClass is null");
        this.attributeClass = Objects.requireNonNull(attributeClass, "attributeClass is null");
        this.columnclass = Objects.requireNonNull(columnClass, "columnClass is null");
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

    final Class<X> attributeClass;

    final Class<Y> columnclass;
}
