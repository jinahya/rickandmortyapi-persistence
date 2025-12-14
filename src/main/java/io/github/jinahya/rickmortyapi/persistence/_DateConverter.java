package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter
public class _DateConverter implements AttributeConverter<LocalDate, String> {

    public static final String DATE_TIME_FORMAT_AIR_DATE = "MMMM d, uuuu";

    public static final DateTimeFormatter DATE_TIME_PATTERN_AIR_DATE =
            DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_AIR_DATE);

    // -----------------------------------------------------------------------------------------------------------------
    //    protected
    public
    _DateConverter() {
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
        return LocalDate.parse(dbData, DATE_TIME_PATTERN_AIR_DATE);
    }
}
