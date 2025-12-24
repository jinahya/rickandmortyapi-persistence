package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class Location_DimensionConverter implements AttributeConverter<Location_Dimension, String> {

    Location_DimensionConverter() {
        super();
    }

    @Override
    public String convertToDatabaseColumn(final Location_Dimension attribute) {
        return Optional.ofNullable(attribute)
                       .map(Location_Dimension::columnValue)
                       .orElse(null);
    }

    @Override
    public Location_Dimension convertToEntityAttribute(final String dbData) {
        return Optional.ofNullable(dbData)
                       .map(Location_Dimension::valueOfColumnValue)
                       .orElse(null);
    }
}
