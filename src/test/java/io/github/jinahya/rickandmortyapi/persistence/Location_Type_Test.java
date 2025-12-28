package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Test;

class Location_Type_Test extends _StringColumnEnum_Test<Location_Type> {

    @Test
    void _AllConstantsExist_AllColumnsValues() {
        super._AllConstantsExist_AllPredefinedColumnsValues(
                _ColumnValues_TestUtils.getAllValues(Location_TypeColumnValues.class));
    }

    // -----------------------------------------------------------------------------------------------------------------
    Location_Type_Test() {
        super(Location_Type.class);
    }
}
