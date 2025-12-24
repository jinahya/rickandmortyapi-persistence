package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class Location_TypeConverter extends _StringColumnEnumConverter<Location_Type> {

    Location_TypeConverter() {
        super(Location_Type.class);
    }
}
