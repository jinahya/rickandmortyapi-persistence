package io.github.jinahya.rickmortyapi.persistence;

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
final class __JakartaValidationTestUtils {

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

    private static <T> T requireValid(final T object, final Class<?>... groups) {
        final var violations = validate(object, groups);
        assertThat(violations)
                .as("constraint violations of %s, targeted %s", object, Arrays.toString(groups)).isEmpty();
        return object;
    }

    private __JakartaValidationTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
