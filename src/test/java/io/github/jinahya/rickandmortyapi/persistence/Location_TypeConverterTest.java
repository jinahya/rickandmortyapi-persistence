package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class Location_TypeConverterTest {

    @EnumSource(Location_Type.class)
    @ParameterizedTest
    void convertToDatabaseColumn__(final Location_Type attribute) {
        final var converter = new Location_TypeConverter();
        final var dbData = converter.convertToDatabaseColumn(attribute);
        assertThat(dbData).isEqualTo(attribute.columnValue());
    }

    @EnumSource(Location_Type.class)
    @ParameterizedTest
    void convertToEntityAttribute__(final Location_Type type) {
        final var converter = new Location_TypeConverter();
        final var attribute = converter.convertToEntityAttribute(type.columnValue());
        assertThat(attribute).isSameAs(type);
    }
}