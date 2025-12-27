package io.github.jinahya.rickandmortyapi.persistence.converter;

import java.util.Objects;

public abstract class __BaseConverter_Test<C extends __BaseConverter<X, Y>, X, Y> {

    protected __BaseConverter_Test(final Class<C> converterClass, final Class<X> attributeClass,
                                   final Class<Y> dbDataClass) {
        super();
        this.converterClass = Objects.requireNonNull(converterClass, "converterClass is null");
        this.attributeClass = Objects.requireNonNull(attributeClass, "attributeClass is null");
        this.dbDataClass = Objects.requireNonNull(dbDataClass, "dbDataClass is null");
    }

    // ----------------------------------------------------------------------------------------------------------------- converterClass
    protected C newConverterInstance() {
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
    protected final Class<C> converterClass;

    protected final Class<X> attributeClass;

    protected final Class<Y> dbDataClass;
}
