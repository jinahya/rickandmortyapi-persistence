package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Converter;

/**
 * An attribute converter for converting {@link Location_Dimension} attributes to and from strings.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Converter(autoApply = true)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Location_DimensionConverter extends _StringColumnEnumAttributeConverter<Location_Dimension> {

    /**
     * Creates a new instance.
     */
    public Location_DimensionConverter() {
        super(Location_Dimension.class);
    }
}
