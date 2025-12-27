package io.github.jinahya.rickandmortyapi.persistence.converter;

import jakarta.persistence.Converter;

import java.net.URI;

@Converter
public class UriConverter extends _StringConverter<URI> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public UriConverter() {
        super(URI::toASCIIString, URI::create);
    }
}
