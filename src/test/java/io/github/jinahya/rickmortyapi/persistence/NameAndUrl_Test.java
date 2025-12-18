package io.github.jinahya.rickmortyapi.persistence;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NameAndUrl_Test extends __Base_PersistenceTest<NameAndUrl> {

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
}
