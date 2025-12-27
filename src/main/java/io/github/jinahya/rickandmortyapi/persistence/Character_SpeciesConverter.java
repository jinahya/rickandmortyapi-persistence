package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Character_SpeciesConverter implements AttributeConverter<Character_Species, String> {

    public Character_SpeciesConverter() {
        super();
    }

    @Override
    public String convertToDatabaseColumn(final Character_Species attribute) {
        return Optional.ofNullable(attribute)
                       .map(Character_Species::columnValue)
                       .orElse(null);
    }

    @Override
    public Character_Species convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                       .map(Character_Species::valueOfColumnValue)
                       .orElse(null);
    }
}
