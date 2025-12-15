package io.github.jinahya.rickmortyapi.persistence;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

import java.net.URL;
import java.util.Objects;

/**
 * An embeddable class for {@link Character_#origin origin} and {@link Character_#location location} attributes of the
 * {@link Character} entity.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Character#getOrigin()
 * @see Character#getLocation()
 */
@Embeddable
public class NameAndUrl {

    // -----------------------------------------------------------------------------------------------------------------
    static final String COLUMN_NAME_NAME = "name";

    static final String ATTRIBUTE_NAME_NAME = "name";

    // -----------------------------------------------------------------------------------------------------------------
    static final String COLUMN_NAME_URL = "url";

    static final String ATTRIBUTE_NAME_URL = "url";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with specified values.
     *
     * @param name a value for {@value NameAndUrl_#NAME} attribute.
     * @param url  a value for {@value NameAndUrl_#URL} attribute.
     * @return a new instance of {@code name} and {@code url}.
     */
    public static NameAndUrl of(@Nonnull final String name, @Nullable final URL url) {
        final var instance = new NameAndUrl();
        instance.setName(name);
        instance.setUrl(url);
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    protected NameAndUrl() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{' +
               "name=" + name +
               ",url=" + url +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof NameAndUrl that)) {
            return false;
        }
        return Objects.equals(name, that.name)
               && Objects.equals(url, that.url);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(name, url);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns current value of {@value NameAndUrl_#NAME} attribute.
     *
     * @return current value of the {@value NameAndUrl_#NAME} attribute.
     */
    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns current value of {@value NameAndUrl_#URL} attribute.
     *
     * @return current value of the {@value NameAndUrl_#URL} attribute.
     */
    @Nullable
    public URL getUrl() {
        return url;
    }

    void setUrl(@Nullable final URL url) {
        this.url = url;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = false, updatable = false)
    private String name;

    @Nullable
    @Convert(converter = _UrlConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_URL, nullable = true, insertable = false, updatable = false)
    private URL url;
}
