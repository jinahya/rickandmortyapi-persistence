package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class Character_TypeConverter implements AttributeConverter<Character_Type, String> {

    public Character_TypeConverter() {
        super();
    }

    @Override
    public String convertToDatabaseColumn(final Character_Type attribute) {
        return Optional.ofNullable(attribute)
                .map(Character_Type::columnValue)
                .orElse(null);
    }

    @Override
    public Character_Type convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                .map(Character_Type::valueOfColumnValue)
                .orElse(null);
    }
}
