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

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A utility class for <a href="https://jakarta.ee/specifications/bean-validation/">Jakarta Validation</a>.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class __JakartaValidation_TestUtils {

    private static <R> R applyValidator_(final Function<? super ValidatorFactory, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        try (final var factory = Validation.buildDefaultValidatorFactory()) {
            return function.apply(factory);
        }
    }

    private static <R> R applyValidator(final Function<? super Validator, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return applyValidator_(factory -> function.apply(factory.getValidator()));
    }

    private static <T> Set<ConstraintViolation<T>> validate(final T object, final Class<?>... groups) {
        Objects.requireNonNull(object, "object is null");
        Objects.requireNonNull(groups, "groups is null");
        return applyValidator(v -> v.validate(object, groups));
    }

    static <T> T requireValid(final T object, final Class<?>... groups) {
        final var violations = validate(object, groups);
        assertThat(violations)
                .as("constraint violations of %s, targeted %s", object, Arrays.toString(groups)).isEmpty();
        return object;
    }

    private __JakartaValidation_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
