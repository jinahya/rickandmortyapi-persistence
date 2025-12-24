package io.github.jinahya.rickandmortyapi.persistence;

class Character_GenderConverter_Test
        extends _StringColumnEnumConverter_Test<Character_GenderConverter, Character_Gender> {

    Character_GenderConverter_Test() {
        super(Character_GenderConverter.class, Character_Gender.class);
    }
}