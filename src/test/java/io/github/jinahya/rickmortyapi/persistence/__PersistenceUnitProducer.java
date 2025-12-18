package io.github.jinahya.rickmortyapi.persistence;

/*-
 * #%L
 * jinahya-persistence-mapped-test
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
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

import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

import java.lang.invoke.MethodHandles;

/**
 * A class for providing persistence resources.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class __PersistenceUnitProducer {

    private static final System.Logger logger = System.getLogger(MethodHandles.lookup().lookupClass().getName());

    private static final String PERSISTENCE_UNIT_NAME = "rickandmortyPU";

    @PersistenceUnit
    @Produces
    public EntityManagerFactory produceItEntityManagerFactory() {
        final var entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        logger.log(System.Logger.Level.TRACE, "entity manager factory created: {0}", entityManagerFactory);
        if (false) {
//            __PersistenceProducer_TestUtils.assertSchemagenDatabaseActionNone(entityManagerFactory);
//            __PersistenceProducer_TestUtils.assertEclipselinkDdlGenerationNone(entityManagerFactory);
//            __PersistenceProducer_TestUtils.assertHibernateHbm2ddlAutoNone(entityManagerFactory);
        }
        logger.log(
                System.Logger.Level.DEBUG,
                "producing entity manager factory: {0}",
                entityManagerFactory
        );
        entityManagerFactory.getProperties().forEach((k, v) -> {
            logger.log(System.Logger.Level.DEBUG, "entityManagerFactory.property; {0}: {1}", k, v);
        });
        return entityManagerFactory;
    }

    /**
     * Disposes the specified entity manager factory produced by {@link #produceItEntityManagerFactory()} method.
     *
     * @param entityManagerFactory the entity manager factory to dispose.
     */
    public void disposeItEntityManagerFactory(
            @Disposes final EntityManagerFactory entityManagerFactory) {
        logger.log(
                System.Logger.Level.DEBUG,
                "disposing entity manager factory: {0}",
                entityManagerFactory
        );
        entityManagerFactory.close();
    }
}
