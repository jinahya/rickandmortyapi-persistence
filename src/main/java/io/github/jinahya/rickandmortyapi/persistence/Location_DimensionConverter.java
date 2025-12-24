package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Converter;

@Converter(autoApply = true)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Location_DimensionConverter extends _StringColumnEnumConverter<Location_Dimension> {

    Location_DimensionConverter() {
        super(Location_Dimension.class);
    }
}
