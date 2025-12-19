package io.github.jinahya.rickandmortyapi.persistence;

@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
final class _PersistenceConstants {

    /**
     * The number of all characters which is {@value}.
     *
     * @see <a href="https://rickandmortyapi.com/documentation">Documentation</a> (rickandmortyapi.com)
     */
    public static final int NUMBER_OF_ALL_CHARACTERS = 826;

    /**
     * The number of all episodes which is {@value}.
     *
     * @see <a href="https://rickandmortyapi.com/documentation">Documentation</a> (rickandmortyapi.com)
     */
    public static final int NUMBER_OF_ALL_EPISODES = 51;

    /**
     * The number of all locations which is {@value}.
     *
     * @see <a href="https://rickandmortyapi.com/documentation">Documentation</a> (rickandmortyapi.com)
     */
    public static final int NUMBER_OF_ALL_LOCATIONS = 126;

    private _PersistenceConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
