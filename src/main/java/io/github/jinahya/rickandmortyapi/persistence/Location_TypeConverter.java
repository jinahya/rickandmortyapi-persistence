package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class Location_TypeConverter implements AttributeConverter<Location_Type, String> {

    Location_TypeConverter() {
        super();
    }

    @Override
    public String convertToDatabaseColumn(final Location_Type attribute) {
        return Optional.ofNullable(attribute)
                       .map(Location_Type::columnValue)
                       .orElse(null);
    }

    @Override
    public Location_Type convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                       .map(Location_Type::valueOfColumnValue)
                       .orElse(null);
    }
}
