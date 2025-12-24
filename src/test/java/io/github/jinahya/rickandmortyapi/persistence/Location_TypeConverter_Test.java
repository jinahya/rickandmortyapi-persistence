package io.github.jinahya.rickandmortyapi.persistence;

class Location_TypeConverter_Test extends _StringColumnEnumConverter_Test<Location_TypeConverter, Location_Type> {

    Location_TypeConverter_Test() {
        super(Location_TypeConverter.class, Location_Type.class);
    }
}