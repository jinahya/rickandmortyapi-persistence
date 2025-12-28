package io.github.jinahya.rickandmortyapi.persistence;

abstract class _StringColumnEnumConverter_Test<
        C extends _StringColumnEnumAttributeConverter<E>,
        E extends Enum<E> & _StringColumnEnum<E>
        >
        extends __ColumnEnumConverter_Test<C, E, String> {

    _StringColumnEnumConverter_Test(final Class<C> converterClass, final Class<E> enumClass) {
        super(converterClass, enumClass, String.class);
    }
}
