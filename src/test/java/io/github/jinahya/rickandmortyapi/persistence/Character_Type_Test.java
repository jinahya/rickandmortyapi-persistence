package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Test;

class Character_Type_Test extends _StringColumnEnum_Test<Character_Type> {

    @Test
    void _AllConstantsExist_AllColumnsValues() {
        super._AllConstantsExist_AllColumnsValues(
                _ColumnValues_TestUtils.getAllValues(Character_TypeColumnValues.class));
    }

    Character_Type_Test() {
        super(Character_Type.class);
    }
}
