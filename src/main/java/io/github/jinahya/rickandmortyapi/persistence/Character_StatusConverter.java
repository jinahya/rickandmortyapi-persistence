package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class Character_StatusConverter implements AttributeConverter<Character_Status, String> {

    Character_StatusConverter() {
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
