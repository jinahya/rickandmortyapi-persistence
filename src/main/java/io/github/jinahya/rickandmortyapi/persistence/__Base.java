package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.MappedSuperclass;

/**
 * An abstract mapped superclass for mapping {@code rickandmortyapi.db}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
abstract class __Base {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    __Base() {
        super();
    }
}
