package io.github.jinahya.rickandmortyapi.persistence;

abstract class _StringEnumConverter_Test<
        C extends _StringColumnEnumConverter<E>,
        E extends Enum<E> & _StringColumnEnum<E>
        >
        extends __ColumnEnumConverter_Test<C, E, String> {

    _StringEnumConverter_Test(final Class<C> converterClass, final Class<E> enumClass) {
        super(converterClass, enumClass, String.class);
    }
}