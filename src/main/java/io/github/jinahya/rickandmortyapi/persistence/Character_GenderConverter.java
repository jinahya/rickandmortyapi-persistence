package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Converter;

@Converter(autoApply = true)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Character_GenderConverter extends _StringColumnEnumAttributeConverter<Character_Gender> {

    public Character_GenderConverter() {
        super(Character_Gender.class);
    }
}
