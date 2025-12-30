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
 * Constants for the {@value Character_#STATUS} attribute.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum Character_Status
        implements _StringColumnEnum<Character_Status> {

    /**
     * A constant for the {@code "Alive"} column value.
     */
    ALIVE("Alive"),

    /**
     * A constant for the {@code "Dead"} column value.
     */
    DEAD("Dead"),

    /**
     * A constant for the {@code "unknown"} column value.
     */
    UNKNOWN("unknown");

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the value whose {@link #columnValue() columnValue} property matches to the specified value.
     *
     * @param columnValue the value for the {@link #columnValue() columnValue} to match.
     * @return the value whose {@link #columnValue() columnValue} property matches to the specified value.
     */
    public static Character_Status valueOfColumnValue(final String columnValue) {
        return _StringColumnEnumUtils.valueOfColumnValue(Character_Status.class, columnValue);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Character_Status(final String columnValue) {
        this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the column value of this constant.
     *
     * @return the column value of this constant.
     */
    public String columnValue() {
        return columnValue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final String columnValue;
}
