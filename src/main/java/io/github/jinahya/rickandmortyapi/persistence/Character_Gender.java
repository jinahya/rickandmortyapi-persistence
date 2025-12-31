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

import java.util.Objects;

/**
 * Constants for the {@value Character_#GENDER} attribute of the {@link Character} entity.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum Character_Gender
        implements _StringColumnEnum<Character_Gender> {

    /**
     * A constant for the {@code "Female"} column value.
     */
    FEMALE("Female"),

    /**
     * A constant for the {@code "Genderless"} column value.
     */
    GENDERLESS("Genderless"),

    /**
     * A constant for the {@code "Male"} column value.
     */
    MALE("Male"),

    /**
     * A constant for the {@code "unknown"} column value.
     */
    UNKNOWN("unknown");

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the constants whose {@link #columnValue() columnValue} matches the specified value.
     *
     * @param columnValue the value for the {@link #columnValue() columnValue} to match.
     * @return the constants whose {@link #columnValue() columnValue} matches the specified {@code columnValue}.
     */
    public static Character_Gender valueOfColumnValue(final String columnValue) {
        return _StringColumnEnumUtils.valueOfColumnValue(Character_Gender.class, columnValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    Character_Gender(final String columnValue) {
        this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String columnValue() {
        return columnValue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final String columnValue;
}
