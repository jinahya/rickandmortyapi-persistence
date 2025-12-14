package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter
public class _LocalDateConverter implements AttributeConverter<LocalDate, String> {

    // -----------------------------------------------------------------------------------------------------------------
    //    protected
    public
    _LocalDateConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String convertToDatabaseColumn(final LocalDate attribute) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public LocalDate convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            return null;
        }
        return LocalDate.parse(dbData, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
