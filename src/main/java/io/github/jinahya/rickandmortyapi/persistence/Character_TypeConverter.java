package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Converter;

@Converter(autoApply = true)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Character_TypeConverter extends _StringColumnEnumAttributeConverter<Character_Type> {

    // -----------------------------------------------------------------------------------------------------------------
    public Character_TypeConverter() {
        super(Character_Type.class);
    }
}
