package io.github.jinahya.rickandmortyapi.persistence;

abstract class _StringColumnEnum_Test<E extends Enum<E> & __ColumnEnum<E, String>>
        extends __ColumnEnum_Test<E, String> {

    _StringColumnEnum_Test(final Class<E> enumClass) {
        super(enumClass, String.class);
    }
}