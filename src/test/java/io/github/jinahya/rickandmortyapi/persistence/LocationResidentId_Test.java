package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

class LocationResidentId_Test extends __Base_Test<LocationResidentId> {

    @Test
    void of__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var locationId = ThreadLocalRandom.current().nextInt();
        final var residentId = ThreadLocalRandom.current().nextInt();
        // -------------------------------------------------------------------------------------------------------- when
        final var id = LocationResidentId.of(locationId, residentId);
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(id).isNotNull().satisfies(v -> {
            assertThat(v.getLocationId()).isEqualTo(locationId);
            assertThat(v.getResidentId()).isEqualTo(residentId);
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    LocationResidentId_Test() {
        super(LocationResidentId.class);
    }

    @DisplayName("compareTo(o)")
    @Nested
    class CompareTo_Test {

        @Test
        void _Equal_SmeeValue() {
            final var instance = LocationResidentId.of(1, 1);
            final var o = LocationResidentId.of(instance.getLocationId(), instance.getResidentId());
            assertThat(instance).isEqualByComparingTo(o);
        }

        @Test
        void _LessThan_Plus() {
            final var instance = LocationResidentId.of(1, 1);
            {
                final var o = LocationResidentId.of(instance.getLocationId(), instance.getResidentId() + 1);
                assertThat(instance).isLessThan(o);
            }
            {
                final var o = LocationResidentId.of(instance.getLocationId() + 1, instance.getResidentId());
                assertThat(instance).isLessThan(o);
            }
        }

        @Test
        void _GreaterThat_Plus() {
            final var instance = LocationResidentId.of(1, 1);
            {
                final var o = LocationResidentId.of(instance.getLocationId(), instance.getResidentId() - 1);
                assertThat(instance).isGreaterThan(o);
            }
            {
                final var o = LocationResidentId.of(instance.getLocationId() - 1, instance.getResidentId());
                assertThat(instance).isGreaterThan(o);
            }
        }
    }
}
