package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CharacgterNameAndUrl_Test extends __Base_Test<Character_NameAndUrl> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    CharacgterNameAndUrl_Test() {
        super(Character_NameAndUrl.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void ATTRIBUTE_NAME_NAME__() {
        assertThat(Character_NameAndUrl.ATTRIBUTE_NAME_NAME)
                .isEqualTo(Character_NameAndUrl_.NAME);
    }

    @Test
    void ATTRIBUTE_NAME_URL__() {
        assertThat(Character_NameAndUrl.ATTRIBUTE_NAME_URL)
                .isEqualTo(Character_NameAndUrl_.URL);
    }
}
