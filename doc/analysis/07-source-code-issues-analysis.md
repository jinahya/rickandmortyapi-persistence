# 7. Source Code Issues Analysis

[← Previous: Code Quality & Architectural Review](06-code-quality-architectural-review.md) | [Index](index.md) | [Next: Issues & Recommendations →](08-issues-recommendations.md)

---

This section provides a detailed analysis of code quality issues found in the `src/main/java` directory.

### Summary

- **Total Issues Found**: 8
- **Critical Issues**: 0 ✅
- **Warnings**: 6
- **Code Quality Issues**: 2

---

### 1. Redundant @Convert Annotations (Character.java)

**Location**: `src/main/java/io/github/jinahya/rickandmortyapi/persistence/Character.java`

**Lines**: 534, 544, 554, 564

**Issue**: Four fields have explicit `@Convert` annotations with TODO comments indicating they should be removed because
the converters are auto-applying.

```java
@Convert(converter = Character_StatusConverter.class) // TODO: remove; the converter is an auto-applying one
@Convert(converter = Character_SpeciesConverter.class) // TODO: remove; the converter is an auto-applying one
@Convert(converter = Character_TypeConverter.class) // TODO: remove; the converter is an auto-applying one
@Convert(converter = Character_GenderConverter.class) // TODO: remove; the converter is an auto-applying one
```

**Impact**: Low - Code works but has redundant annotations

**Recommendation**: Remove the `@Convert` annotations if the converters are indeed auto-applying (have
`@Converter(autoApply = true)`)

**Status**: ⚠️ **WARNING**

---

### 2. Empty Utility Class (_PersistenceUtils.java)

**Location**: `src/main/java/io/github/jinahya/rickandmortyapi/persistence/_PersistenceUtils.java`

**Issue**: The class `_PersistenceUtils` is completely empty - it only has a private constructor that throws an
AssertionError.

**Current Code**:

```java
final class _PersistenceUtils {
    private _PersistenceUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
```

**Impact**: Low - No functionality, but no harm either

**Recommendation**:

- If this is a placeholder for future utilities, add a comment explaining that
- If it's not needed, consider removing it
- If it's meant to be used, add utility methods

**Status**: ⚠️ **WARNING**

---

### 3. Deprecated Class Still in Use (EpisodeCharacter.java)

**Location**: `src/main/java/io/github/jinahya/rickandmortyapi/persistence/EpisodeCharacter.java`

**Related**: `EpisodeCharacterId.java` is marked as `@Deprecated`

**Issue**: `EpisodeCharacterId` is deprecated with message "use {@link CharacterEpisode} and {@link CharacterEpisodeId}
instead", but `EpisodeCharacter` entity class still exists and uses it.

**Impact**: Medium - Deprecated code should be removed or migration path documented

**Recommendation**:

- Either mark `EpisodeCharacter` as deprecated as well
- Or document why it's still needed
- Or remove both if they're truly deprecated

**Status**: ⚠️ **WARNING**

---

### 4. Empty Lifecycle Callback Methods (Episode.java)

**Location**: `src/main/java/io/github/jinahya/rickandmortyapi/persistence/Episode.java`

**Lines**: 262-264, 276-278, 280-282, 284-286

**Issue**: Several lifecycle callback methods are empty (do nothing):

```java
@PostPersist
private void doOnPostPersist() {
}

@PostUpdate
private void doOnPostUpdate() {
}

@PreRemove
private void doOnPreRemove() {
}

@PostRemove
private void doOnPostRemove() {
}
```

**Impact**: Low - Empty methods serve no purpose but don't cause errors

**Recommendation**:

- Remove empty callback methods if they're not needed
- Or add comments explaining why they're kept (e.g., "Reserved for future use")
- Or implement the intended functionality

**Status**: ⚠️ **WARNING**

---

### 5. Commented-Out Code (Episode.java)

**Location**: `src/main/java/io/github/jinahya/rickandmortyapi/persistence/Episode.java`

**Lines**: 600-605 (approximately)

**Issue**: There's commented-out code related to `@JoinTable` using `EpisodeCharacter.TABLE_NAME`:

```java
//    @JoinTable(name = EpisodeCharacter.TABLE_NAME,
//               joinColumns = {
//                       @JoinColumn(name = EpisodeCharacter.COLUMN_NAME_EPISODE_ID)
//               },
//               inverseJoinColumns = {
//                       @JoinColumn(name = EpisodeCharacter.COLUMN_NAME_CHARACTER_ID)
//               }
//    )
```

**Impact**: Low - Commented code can be confusing

**Recommendation**:

- Remove if no longer needed
- Or add a comment explaining why it's kept
- Or uncomment if it should be active

**Status**: ⚠️ **WARNING**

---

### 6. Unused Converter Classes

**Location**:

- `src/main/java/io/github/jinahya/rickandmortyapi/persistence/converter/UrlStringConverter2.java`
- `src/main/java/io/github/jinahya/rickandmortyapi/persistence/converter/UrlListStringConverter2.java`

**Issue**: These converter classes exist but are not used anywhere in the codebase. They appear to be alternative
implementations or test versions.

**Impact**: Low - Unused code adds maintenance burden

**Recommendation**:

- Remove if not needed
- Or document why they exist (e.g., "Alternative implementation for testing")
- Or use them if they're meant to replace the original versions

**Status**: ⚠️ **WARNING**

---

### 7. @Nonnull Import Usage

**Location**: `src/main/java/io/github/jinahya/rickandmortyapi/persistence/Character.java`

**Line**: 26 (import), 611 (usage)

**Issue**: `@Nonnull` is imported and used once. This is fine, but consider consistency with `@NotNull` from Jakarta
validation.

**Impact**: None - Both annotations work, but mixing them can be confusing

**Recommendation**:

- Use `@NotNull` from `jakarta.validation.constraints` for consistency
- Or use `@Nonnull` from `jakarta.annotation` consistently throughout

**Status**: ℹ️ **INFO** (Not an error, but inconsistency)

---

### Summary by Severity

#### Critical Issues

**None** - All critical issues have been resolved! ✅

#### Warnings

1. Redundant @Convert annotations (4 instances)
2. Empty utility class (_PersistenceUtils)
3. Deprecated class still in use (EpisodeCharacter)
4. Commented-out code (Episode.java)
5. Empty lifecycle callback methods (4 methods in Episode.java)
6. Unused converter classes (UrlStringConverter2, UrlListStringConverter2)

#### Information

1. Inconsistent annotation usage (@Nonnull vs @NotNull)

---

[← Previous: Code Quality & Architectural Review](06-code-quality-architectural-review.md) | [Index](index.md) | [Next: Issues & Recommendations →](08-issues-recommendations.md)
