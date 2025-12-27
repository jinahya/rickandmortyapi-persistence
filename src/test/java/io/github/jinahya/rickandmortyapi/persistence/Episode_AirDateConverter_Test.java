package io.github.jinahya.rickandmortyapi.persistence;

import io.github.jinahya.rickandmortyapi.persistence.converter._StringConverter_Test;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class Episode_AirDateConverter_Test extends _StringConverter_Test<Episode_AirDateConverterConverter, LocalDate> {

    Episode_AirDateConverter_Test() {
        super(Episode_AirDateConverterConverter.class, LocalDate.class);
    }

    @Test
    void convertToEntityAttribute__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var instance = newConverterInstance();
        final var dbData = "December 2, 2013";
        // -------------------------------------------------------------------------------------------------------- when
        final var attribute = instance.convertToEntityAttribute(dbData);
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(attribute).isEqualTo(LocalDate.of(2013, 12, 2));
    }

    @Test
    void convertToDatabaseColumn__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var instance = newConverterInstance();
        final var attribute = LocalDate.of(2013, 12, 2);
        // -------------------------------------------------------------------------------------------------------- when
        final var dbData = instance.convertToDatabaseColumn(attribute);
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(dbData).isEqualTo("December 2, 2013");
    }
}
