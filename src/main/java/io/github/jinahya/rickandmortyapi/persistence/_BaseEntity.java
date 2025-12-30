package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.MappedSuperclass;

/**
 * An abstract mapped superclass for mapping {@code rickandmortyapi.db}.
 *
 * @param <ID> id type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _BaseEntity<ID> extends __Base {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _BaseEntity() {
        super();
    }
}
