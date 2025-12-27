package io.github.jinahya.rickandmortyapi.persistence.converter;

public abstract class _StringConverter_Test<C extends _StringConverter<X>, X>
        extends __BaseConverter_Test<C, X, String> {

    protected _StringConverter_Test(final Class<C> converterClass, final Class<X> attributeClass) {
        super(converterClass, attributeClass, String.class);
    }
}
