package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Converter;

@Converter(autoApply = true)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Character_SpeciesConverter extends _StringColumnEnumAttributeConverter<Character_Species> {

    public Character_SpeciesConverter() {
        super(Character_Species.class);
    }
}
