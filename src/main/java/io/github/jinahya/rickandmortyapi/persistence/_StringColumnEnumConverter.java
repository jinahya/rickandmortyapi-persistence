package io.github.jinahya.rickandmortyapi.persistence;

abstract class _StringColumnEnumConverter<E extends Enum<E> & _StringColumnEnum<E>>
        extends __ColumnEnumConverter<E, String> {

    _StringColumnEnumConverter(final Class<E> enumClass) {
        super(enumClass);
    }
}
