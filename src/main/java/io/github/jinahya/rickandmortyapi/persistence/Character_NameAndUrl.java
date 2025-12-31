package io.github.jinahya.rickandmortyapi.persistence;

/*-
 * #%L
 * rickandmortyapi-persistence
 * %%
 * Copyright (C) 2025 GitHub, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import io.github.jinahya.rickandmortyapi.persistence.converter.UrlStringConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

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
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class Character_NameAndUrl
        extends __Base {

    // -----------------------------------------------------------------------------------------------------------------
    private static final String COLUMN_NAME_NAME = "name";

    static final String ATTRIBUTE_NAME_NAME = "name";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String COLUMN_NAME_URL = "url";

    static final String ATTRIBUTE_NAME_URL = "url";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    protected Character_NameAndUrl() {
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
        if (!(obj instanceof Character_NameAndUrl that)) {
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
     * Returns current value of {@value Character_NameAndUrl_#NAME} attribute.
     *
     * @return current value of the {@value Character_NameAndUrl_#NAME} attribute.
     */
    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns current value of {@value Character_NameAndUrl_#URL} attribute.
     *
     * @return current value of the {@value Character_NameAndUrl_#URL} attribute.
     */
    @Nullable
    public URL getUrl() {
        return url;
    }

    void setUrl(@Nullable final URL url) {
        this.url = url;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(min = 1)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NAME,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private String name;

    @Nullable
    @Convert(converter = UrlStringConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_URL,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private URL url;
}
