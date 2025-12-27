package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Character_StatusConverter implements AttributeConverter<Character_Status, String> {

    public Character_StatusConverter() {
        super();
    }

    @Override
    public String convertToDatabaseColumn(final Character_Status attribute) {
        return Optional.ofNullable(attribute)
                       .map(Character_Status::columnValue)
                       .orElse(null);
    }

    @Override
    public Character_Status convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                       .map(Character_Status::valueOfColumnValue)
                       .orElse(null);
    }
}
