package io.github.jinahya.rickandmortyapi.persistence;

import java.util.List;

class Character_GenderColumnValues_TestUtils {

    static List<String> getAllValues() {
        return _ColumnValues_TestUtils.getAllValues(Character_GenderColumnValues.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Character_GenderColumnValues_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
