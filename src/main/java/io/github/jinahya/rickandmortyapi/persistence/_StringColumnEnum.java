package io.github.jinahya.rickandmortyapi.persistence;

@SuppressWarnings({
        "java:S114" // Interface names should comply with a naming convention
})
interface _StringColumnEnum<E extends Enum<E> & _StringColumnEnum<E>> extends __ColumnEnum<E, String> {

}
