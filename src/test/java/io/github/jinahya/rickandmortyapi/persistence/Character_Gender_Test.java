package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Test;

class Character_Gender_Test extends _StringColumnEnum_Test<Character_Gender> {

    @Test
    void _AllConstantsExist_AllColumnsValues() {
        _AllConstantsExist_AllColumnsValues(
                _ColumnValues_TestUtils.getAllValues(Character_GenderColumnValues.class)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    Character_Gender_Test() {
        super(Character_Gender.class);
    }
}
