package io.github.jinahya.rickmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Episode_PersistenceIT extends _BaseEntity_PersistenceIT<Episode, Integer> {

    // -----------------------------------------------------------------------------------------------------------------
    Episode_PersistenceIT() {
        super(Episode.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("SelectOne_WhereEpisodeEqual_")
    @Nested
    class SelectOne_WhereEpisodeEqual__IT {

        private static Stream<String> episodeStream() {
            return Stream.of(
                    "S01E01", "S01E02", "S01E03", "S01E04", "S01E05", "S01E06", "S01E07", "S01E08",
                    "S01E09", "S01E10", "S01E11", "S02E01", "S02E02", "S02E03", "S02E04", "S02E05",
                    "S02E06", "S02E07", "S02E08", "S02E09", "S02E10", "S03E01", "S03E02", "S03E03",
                    "S03E04", "S03E05", "S03E06", "S03E07", "S03E08", "S03E09", "S03E10", "S04E01",
                    "S04E02", "S04E03", "S04E04", "S04E05", "S04E06", "S04E07", "S04E08", "S04E09",
                    "S04E10", "S05E01", "S05E02", "S05E03", "S05E04", "S05E05", "S05E06", "S05E07",
                    "S05E08", "S05E09", "S05E10"
            ).sorted();
        }

        @MethodSource("episodeStream")
        @ParameterizedTest
        void NamedQuery__(final String episode) {
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var query = em.createNamedQuery("Episode.SelectOne_WhereEpisodeEqual_", entityClass);
                query.setParameter("episode", episode);
                return query.getSingleResult(); // NoResultException
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result.getEpisode()).isEqualTo(episode);
        }

        @MethodSource("episodeStream")
        @ParameterizedTest
        void QueryLanguage__(final String episode) {
        }

        @MethodSource("episodeStream")
        @ParameterizedTest
        void CriteriaApi__(final String episode) {
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(entityClass);
                final var root = query.from(entityClass);
                query.select(root);
                query.where(builder.equal(root.get(Episode_.episode), episode));
                return em.createQuery(query).getSingleResult();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result.getEpisode()).isEqualTo(episode);
        }
    }

    @DisplayName("SelectList__OrderByIdAsc")
    @Nested
    class SelectList__OrderByIdAsc_IT {

        @Test
        void NamedQuery__() {
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var query = em.createNamedQuery("Episode.SelectList__OrderByIdAsc", entityClass);
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .isNotEmpty()
                    .isSortedAccordingTo(Episode.COMPARING_ID);
        }

        @Test
        void QueryLanguage__() {
        }

        @Test
        void CriteriaApi__() {
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(entityClass);
                final var root = query.from(entityClass);
                query.select(root);
                query.orderBy(builder.asc(root.get(Episode_.id)));
                return em.createQuery(query).getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .isNotEmpty()
                    .isSortedAccordingTo(Episode.COMPARING_ID);
        }
    }

    @DisplayName("SelectList__OrderByAirDateIso_Asc")
    @Nested
    class SelectList__OrderByIdAriDateIso_Asc_IT {

        @Test
        void NamedQuery__() {
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var query = em.createNamedQuery("Episode.SelectList__OrderByAirDateIso_Asc", entityClass);
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .isNotEmpty()
                    .isSortedAccordingTo(Episode.COMPARING_AIR_DATE_ISO_);
        }

        @Test
        void QueryLanguage__() {
        }

        @Test
        void CriteriaApi__() {
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(entityClass);
                final var root = query.from(entityClass);
                query.select(root);
                query.orderBy(builder.asc(root.get(Episode_.airDateIso_)));
                return em.createQuery(query).getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .isNotEmpty()
                    .isSortedAccordingTo(Episode.COMPARING_AIR_DATE_ISO_);
        }
    }
}
