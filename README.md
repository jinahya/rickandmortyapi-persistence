# rickandmortyapi-persistence

[![Java CI with Maven](https://github.com/jinahya/rickandmortyapi-persistence/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/rickandmortyapi-persistence/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_rickandmortyapi-persistence&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_rickandmortyapi-persistence)

[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.jinahya/rickandmortyapi-persistence)](https://central.sonatype.com/artifact/io.github.jinahya/rickandmortyapi-persistence/overview)
[![javadoc](https://javadoc.io/badge2/io.github.jinahya/rickandmortyapi-persistence/javadoc.svg)](https://javadoc.io/doc/io.github.jinahya/rickandmortyapi-persistence)

A persistence module for the [rickandmortyapi-db](https://github.com/jinahya/rickandmortyapi-db).

See

* [rickandmortyapi-db](https://github.com/jinahya/rickandmortyapi-db)
* [rickandmortyapi-spring-data-rest](https://github.com/jinahya/rickandmortyapi-spring-data-rest)


## Notes

### About EclipseLink

EclipseLink does not have a native `DatabasePlatform` (dialect) for SQLite. To use EclipseLink with SQLite, you must either provide a custom platform implementation or use a compatible one.

EclipseLink는 SQLite를 위한 기본 `DatabasePlatform`(dialect)을 제공하지 않습니다. SQLite와 함께 EclipseLink를 사용하려면 직접 플랫폼 구현을 제공하거나 호환되는 플랫폼을 사용해야 합니다.

**References:**

* [EclipseLink Official Website](https://eclipse.dev/eclipselink/)
* [EclipseLink Documentation Center](https://eclipse.dev/eclipselink/documentation/)
* [EclipseLink on Maven Central](https://central.sonatype.com/artifact/org.eclipse.persistence/eclipselink)
* [EclipseLink Project Page](https://projects.eclipse.org/projects/ee4j.eclipselink)
