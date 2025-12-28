package io.github.jinahya.rickandmortyapi.persistence;

abstract class _StringColumnEnumAttributeConverter<E extends Enum<E> & _StringColumnEnum<E>>
        extends __ColumnEnumAttributeConverter<E, String> {

    _StringColumnEnumAttributeConverter(final Class<E> enumClass) {
        super(enumClass);
    }
}
