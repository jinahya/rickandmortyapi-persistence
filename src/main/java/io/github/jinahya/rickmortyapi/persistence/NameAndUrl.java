package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

import java.net.URL;
import java.util.Objects;

@Embeddable
public class NameAndUrl {

    static final String COLUMN_NAME_NAME = "name";

    static final String ATTRIBUTE_NAME_NAME = "name";

    // -----------------------------------------------------------------------------------------------------------------
    static final String COLUMN_NAME_URL = "url";

    static final String ATTRIBUTE_NAME_URL = "url";

    // -----------------------------------------------------------------------------------------------------------------
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
    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public URL getUrl() {
        return url;
    }

    void setUrl(final URL url) {
        this.url = url;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = false, updatable = false)
    private String name;

    @Convert(converter = _UrlConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_URL, nullable = false, insertable = false, updatable = false)
    private URL url;
}
