package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    }

    @Override
    void selectAll__(final EntityManager entityManager, final List<Episode> entityList) {
        super.selectAll__(entityManager, entityList);
        assertThat(entityList)
                .as("all episodes")
                .hasSize(_PersistenceConstants.NUMBER_OF_ALL_EPISODES);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class FetchAll_IT {

        private static void verify__(final List<Episode> result) {
            assertThat(result).isNotEmpty().doesNotContainNull().doesNotHaveDuplicates().allSatisfy((Episode e) -> {
                assertThat(e.getCharacters_())
                        .as("episode(%d).characters_", e.getId())
                        .isNotEmpty()
                        .doesNotContainNull()
                        .doesNotHaveDuplicates()
                        .allSatisfy((Character c) -> {
                            // character.origin - location
                            Optional.ofNullable(c.getOrigin()).ifPresent(v -> {
                                assertThat(v)
                                        .as("episode(%d).characters_[%d].origin", e.getId(), c.getId())
                                        .satisfies(o -> {
                                            assertThat(o.getUrl() == null)
                                                    .isEqualTo(c.getOrigin_() == null);
                                        });
                            });
                            // character.location - location
                            Optional.ofNullable(c.getLocation()).ifPresent(v -> {
                                assertThat(v)
                                        .as("episode(%d).characters_[%d].location", e.getId(), c.getId())
                                        .satisfies(l -> {
                                            assertThat(l.getUrl() == null)
                                                    .isEqualTo(c.getLocation_() == null);
                                        });
                            });
                            // character.location_
                            assertThat(c.getLocation_())
                                    .as("episode(%d).characters_[%d].location_", e.getId(), c.getId())
                                    .satisfiesAnyOf(
                                            l -> {
                                                assertThat(l).isNull();
                                            },
                                            l -> {
                                                assertThat(l).isNotNull()
                                                        .extracting(Location::getResidents_,
                                                                    InstanceOfAssertFactories.LIST)
                                                        .as("episode(%d).characters_[%d].location_(%d).residents",
                                                            e.getId(), c.getId(), l.getId())
                                                        .isNotEmpty()
                                                        .doesNotContainNull()
                                                        .doesNotHaveDuplicates()
                                                        .contains(c);
                                            }
                                    );
                            // character.episodes_
                            assertThat(c.getEpisodes_())
                                    .as("episode(%d).characters_[%d].episodes_", e.getId(), c.getId())
                                    .isNotNull()
                                    .isNotEmpty()
                                    .doesNotContainNull()
                                    .doesNotHaveDuplicates()
                                    .contains(e);
                        });
            });
        }

        @Test
        void queryLangauge__() {
            // ---------------------------------------------------------------------------------------------------- when
            final List<Episode> result = applyEntityManager(em -> {
                final List<Episode> episodes;
                {
                    final var query = em.createQuery(
                            """
                                    SELECT DISTINCT e
                                    FROM Episode e
                                    JOIN FETCH e.characters_ c
                                    LEFT JOIN FETCH c.origin_
                                    LEFT JOIN FETCH c.location_""",
                            Episode.class
                    );
                    episodes = query.getResultList();
                }
                // fetch episodes[*].characters_[*].episodes_
                {
                    final var ids = episodes.stream()
                            .flatMap(e -> e.getCharacters_().stream())
                            .map(Character::getId)
                            .distinct()
                            .toList();
                    final var q = em.createQuery(
                            """
                                    SELECT DISTINCT c
                                    FROM Character c
                                    JOIN FETCH c.episodes_ e
                                    WHERE c.id IN ?1
                                    """,
                            Character.class
                    );
                    final var characters = q.setParameter(1, ids).getResultList();
                }
                // fetch episodes[*].characters_[*].location_.residents_
                {
                    final var ids =
                            episodes.stream()
                                    .flatMap(e -> e.getCharacters_().stream())
                                    .map(Character::getLocation_)
                                    .filter(Objects::nonNull)
                                    .map(Location::getId)
                                    .distinct()
                                    .toList();
                    final var q = em.createQuery(
                            """
                                    SELECT DISTINCT l
                                    FROM Location l
                                    JOIN FETCH l.residents_ r
                                    WHERE l.id IN ?1
                                    """,
                            Location.class
                    );
                    final var locations = q.setParameter(1, ids).getResultList();
                }
                return episodes;
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify__(result);
        }

        @Test
        void criteriaApi__() {
            // ---------------------------------------------------------------------------------------------------- when
            final List<Episode> result = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final List<Episode> episodes;
                {
                    final var query = builder.createQuery(entityClass);
                    final var root = query.from(entityClass);
                    query.select(root);
                    query.distinct(true);
                    // fetch join characters and sub-fetch their origin_ and location_
                    final Fetch<Episode, Character> characters_Fetch = root.fetch(Episode_.characters_, JoinType.INNER);
                    final Fetch<Character, Location> origin_Fetch =
                            characters_Fetch.fetch(Character_.origin_, JoinType.LEFT);
                    final Fetch<Character, Location> location_Fetch =
                            characters_Fetch.fetch(Character_.location_, JoinType.LEFT);
                    episodes = em.createQuery(query).getResultList();
                }
                // fetch episodes[*].characters_[*].episodes_
                {
                    final var ids = episodes.stream()
                            .flatMap(e -> e.getCharacters_().stream())
                            .map(Character::getId)
                            .distinct()
                            .toList();
                    final var q = builder.createQuery(Character.class);
                    final var r = q.from(Character.class);
                    q.select(r).distinct(true);
                    final Fetch<Character, Episode> episodes_Fetch = r.fetch(Character_.episodes_);
                    q.where(r.get(Character_.id).in(ids));
                    final var characters = em.createQuery(q).getResultList();
                }
                // fetch episodes[*].characters_[*].location_.residents_
                {
                    final var ids =
                            episodes.stream()
                                    .flatMap(e -> e.getCharacters_().stream())
                                    .map(Character::getLocation_)
                                    .filter(Objects::nonNull)
                                    .map(Location::getId)
                                    .distinct()
                                    .toList();
                    final var q = builder.createQuery(Location.class);
                    final var r = q.from(Location.class);
                    q.select(r).distinct(true);
                    final Fetch<Location, Character> residents_Fetch = r.fetch(Location_.residents_);
                    q.where(r.get(Location_.id).in(ids));
                    final var locations = em.createQuery(q).getResultList();
                }
                return episodes;
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify__(result);
        }
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
