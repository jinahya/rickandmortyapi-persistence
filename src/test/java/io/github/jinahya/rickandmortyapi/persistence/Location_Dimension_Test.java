package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Test;

class Location_Dimension_Test extends _StringColumnEnum_Test<Location_Dimension> {

    @Test
    void _AllConstantsExist_AllColumnsValues() {
        super._AllConstantsExist_AllColumnsValues(
                _ColumnValues_TestUtils.getAllValues(Location_DimensionColumnValues.class));
    }

    Location_Dimension_Test() {
        super(Location_Dimension.class);
    }
}
