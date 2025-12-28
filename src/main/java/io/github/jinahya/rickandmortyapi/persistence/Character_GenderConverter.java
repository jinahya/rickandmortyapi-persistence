package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Converter;

/**
 * An attribute converter for converting {@link Character_Gender} attribute to/from strings.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Converter(autoApply = true)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Character_GenderConverter extends _StringColumnEnumAttributeConverter<Character_Gender> {

    /**
     * Creates a new instance.
     */
    public Character_GenderConverter() {
        super(Character_Gender.class);
    }
}
