package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class Character_GenderConverter extends _StringColumnEnumConverter<Character_Gender> {

    Character_GenderConverter() {
        super(Character_Gender.class);
    }
}
