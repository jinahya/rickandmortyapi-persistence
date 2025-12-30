package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Converter;

/**
 * An attribute converter for converting {@link Location_Type} attributes to and from strings. author Jin Kwon
 * &lt;onacit_at_gmail.com&gt;
 */
@Converter(autoApply = true)
public class Location_TypeConverter extends _StringColumnEnumAttributeConverter<Location_Type> {

    /**
     * Creates a new instance.
     */
    public Location_TypeConverter() {
        super(Location_Type.class);
    }
}
