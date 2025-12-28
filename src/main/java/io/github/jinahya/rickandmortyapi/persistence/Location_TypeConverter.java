package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class Location_TypeConverter extends _StringColumnEnumAttributeConverter<Location_Type> {

    public Location_TypeConverter() {
        super(Location_Type.class);
    }
}
