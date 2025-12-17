package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Converter
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class _UrlListConverter implements AttributeConverter<List<URL>, String> {

    //    protected
    public _UrlListConverter() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String convertToDatabaseColumn(final List<URL> attribute) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<URL> convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            return null;
        }
        return Arrays.stream(dbData.split(","))
                .map(String::strip)
                .filter(v -> !v.isBlank())
                .map(converter::convertToEntityAttribute)
                .toList();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final AttributeConverter<URL, String> converter = new _UrlConverter();
}
