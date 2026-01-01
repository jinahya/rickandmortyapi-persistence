package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.AttributeConverter;

abstract class ___EnumAttributeConverter_Test<
        C extends AttributeConverter<X, Y>,
        X extends Enum<X>,
        Y
        >
        extends ___AttributeConverter_Test<C, X, Y> {

    ___EnumAttributeConverter_Test(final Class<C> converterClass, final Class<X> attributeClass,
                                   final Class<Y> columnClass) {
        super(converterClass, attributeClass, columnClass);
    }
}
