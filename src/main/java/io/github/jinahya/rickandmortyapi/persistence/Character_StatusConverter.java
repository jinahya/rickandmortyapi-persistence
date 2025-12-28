package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Converter;

@Converter(autoApply = true)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Character_StatusConverter extends _StringColumnEnumAttributeConverter<Character_Status> {

    public Character_StatusConverter() {
        super(Character_Status.class);
    }
}
