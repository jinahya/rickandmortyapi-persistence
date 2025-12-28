package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Episode_PersistenceTest extends _BaseEntity_PersistenceTest<Episode, Integer> {

    // -----------------------------------------------------------------------------------------------------------------
    Episode_PersistenceTest() {
        super(Episode.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    void selectAll__(final Root<Episode> root) {
        super.selectAll__(root);
        final Fetch<Episode, Character> characters_Fetch = root.fetch(Episode_.characters_, JoinType.LEFT);
    }

    @Override
    void selectAll__(final EntityManager entityManager, final List<Episode> entityList) {
        super.selectAll__(entityManager, entityList);
        assertThat(entityList)
                .as("all episodes")
                .hasSize(_PersistenceConstants.NUMBER_OF_ALL_EPISODES);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("SelectSingle_WhereEpisodeEqual_")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class SelectSingle_WhereEpisodeEqual__Test {

        @Test
        void NamedQuery__() {
            applyEntityManager(em -> {
                final var query = em.createNamedQuery("Episode.SelectSingle_WhereEpisodeEqual_", entityClass);
                for (final var episodeValue : Episode_PersistenceTestUtils.getEpisodeList(em)) {
                    query.setParameter("episode", episodeValue);
                    // -------------------------------------------------------------------------------------------- when
                    final var selected = query.getSingleResult(); // NoResultException
                    // -------------------------------------------------------------------------------------------- then
                    assertThat(selected)
                            .isNotNull()
                            .extracting(Episode::getEpisode)
                            .isEqualTo(episodeValue);
                }
                return null;
            });
        }

        @Test
        void QueryLanguage__() {
        }

        @Test
        void CriteriaApi__() {
            applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(entityClass);
                final var root = query.from(entityClass);
                query.select(root);
                final var episodePath = root.get(Episode_.episode);
                for (final var episodeValue : Episode_PersistenceTestUtils.getEpisodeList(em)) {
                    query.where(builder.equal(episodePath, episodeValue));
                    // -------------------------------------------------------------------------------------------- when
                    final var selected = em.createQuery(query).getSingleResult(); // NoResultException
                    // -------------------------------------------------------------------------------------------- then
                    assertThat(selected)
                            .isNotNull()
                            .extracting(Episode::getEpisode)
                            .isEqualTo(episodeValue);
                }
                return null;
            });
        }
    }

    @DisplayName("SelectList__OrderByIdAsc")
    @Nested
    class SelectList__OrderByIdAsc_Test {

        @Test
        void NamedQuery__() {
            // ---------------------------------------------------------------------------------------------------- when
            final List<Episode> result = applyEntityManager(em -> {
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
            final List<Episode> result = applyEntityManager(em -> {
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

    @DisplayName("SelectList__OrderByEpisodeAsc")
    @Nested
    class SelectList__OrderByEpisodeAsc_Test {

        @Test
        void NamedQuery__() {
            // ---------------------------------------------------------------------------------------------------- when
            final List<Episode> result = applyEntityManager(em -> {
                final var query = em.createNamedQuery("Episode.SelectList__OrderByEpisodeAsc", entityClass);
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .isNotEmpty()
                    .isSortedAccordingTo(Episode.COMPARING_EPISODE);
        }

        @Test
        void QueryLanguage__() {
        }

        @Test
        void CriteriaApi__() {
            // ---------------------------------------------------------------------------------------------------- when
            final List<Episode> result = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(entityClass);
                final var root = query.from(entityClass);
                query.select(root);
                query.orderBy(builder.asc(root.get(Episode_.episode)));
                return em.createQuery(query).getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .isNotEmpty()
                    .isSortedAccordingTo(Episode.COMPARING_EPISODE);
        }
    }

    @DisplayName("SelectList__OrderByAirDateIso_Asc")
    @Nested
    class SelectList__OrderByIdAriDateIso_Asc_Test {

        @Test
        void NamedQuery__() {
            // ---------------------------------------------------------------------------------------------------- when
            final List<Episode> result = applyEntityManager(em -> {
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
            final List<Episode> result = applyEntityManager(em -> {
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
