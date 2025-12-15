package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@MappedSuperclass
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
abstract class _BaseEntityWithId extends _BaseEntity {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_ID = "id";

    public static final String ATTRIBUTE__ID = "id";

    // -----------------------------------------------------------------------------------------------------------------
    _BaseEntityWithId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        _BaseEntityWithId that = (_BaseEntityWithId) obj;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // -------------------------------------------------------------------------------------------------------------- id
    public Integer getId() {
        return id;
    }

    void setId(final Integer id) {
        this.id = id;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ID, nullable = false, insertable = false, updatable = false)
    private Integer id;
}
