package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.Converter;

/**
 * An attribute converter for converting {@link Character_Species}s to and from strings.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Converter(autoApply = true)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Character_SpeciesConverter extends _StringColumnEnumAttributeConverter<Character_Species> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public Character_SpeciesConverter() {
        super(Character_Species.class);
    }
}
