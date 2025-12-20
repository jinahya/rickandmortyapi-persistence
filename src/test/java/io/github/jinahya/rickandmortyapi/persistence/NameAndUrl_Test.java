package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

class NameAndUrl_Test extends __Base_Test<NameAndUrl> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    NameAndUrl_Test() {
        super(NameAndUrl.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void ATTRIBUTE_NAME_NAME__() {
        assertThat(NameAndUrl.ATTRIBUTE_NAME_NAME).isEqualTo(NameAndUrl_.NAME);
    }

    @Test
    void ATTRIBUTE_NAME_URL__() {
        assertThat(NameAndUrl.ATTRIBUTE_NAME_URL).isEqualTo(NameAndUrl_.URL);
    }

    @Test
    void of__() throws MalformedURLException {
        final var name = "name";
        final var url = URI.create("http://localhost:8080").toURL();
        final var instance = NameAndUrl.of(name, url);
        assertThat(instance)
                .isNotNull()
                .satisfies(v -> {
                    assertThat(v.getName()).isEqualTo(name);
                    assertThat(v.getUrl()).isEqualTo(url);
                });
    }
}
