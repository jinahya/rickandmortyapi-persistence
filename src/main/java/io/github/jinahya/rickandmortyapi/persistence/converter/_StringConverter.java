package io.github.jinahya.rickandmortyapi.persistence.converter;

import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
abstract class _StringConverter<X> extends __BaseConverter<X, String> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    _StringConverter(final Function<? super X, String> formatter,
                     final Function<? super String, ? extends X> parser) {
        super(formatter,
              v -> Optional.of(v)
                      .map(String::strip)
                      .filter(v2 -> !v2.isBlank())
                      .map(parser)
                      .orElse(null));
    }
}
