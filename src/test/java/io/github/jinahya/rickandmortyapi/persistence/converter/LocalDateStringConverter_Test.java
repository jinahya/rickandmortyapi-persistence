package io.github.jinahya.rickandmortyapi.persistence.converter;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDateStringConverter_Test extends _StringConverter_Test<LocalDateStringConverter, LocalDate> {

    LocalDateStringConverter_Test() {
        super(LocalDateStringConverter.class, LocalDate.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void convertToDatabaseColumn__() {
        final var instance = newConverterInstance();
        final var attribute = LocalDate.now();
        final var dbData = instance.convertToDatabaseColumn(attribute);
        assertThat(dbData)
                .isEqualTo(attribute.toString())
                .isEqualTo(attribute.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Test
    void convertToEntityAttribute__() {
        final var instance = newConverterInstance();
        final var dbData = LocalDate.now().toString();
        final var attribute = instance.convertToEntityAttribute(dbData);
        assertThat(attribute).isEqualTo(dbData.formatted(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
