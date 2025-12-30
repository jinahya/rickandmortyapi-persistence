# Persistence Analysis Checklist

Use this checklist when analyzing JPA persistence classes against a database schema.

## Pre-Analysis Setup

- [ ] Identify database file/location
- [ ] Identify source code location
- [ ] Set up analysis output directory
- [ ] Extract database schema

## Schema Extraction

- [ ] Extract complete database schema
- [ ] List all tables
- [ ] Extract table info for each table (columns, types, nullability)
- [ ] Extract foreign key constraints
- [ ] Extract indexes
- [ ] Document unique constraints

## Entity Discovery

- [ ] Find all `@Entity` classes
- [ ] Find all `@Table` annotations
- [ ] List all entity classes
- [ ] Identify embeddable classes (`@Embeddable`)
- [ ] Identify join table entities

## Table Mapping Verification

For each entity:
- [ ] `@Table(name)` matches database table name
- [ ] Table exists in database
- [ ] No orphaned tables (tables without entities)
- [ ] No missing tables (entities without tables)

## Column Mapping Verification

For each entity field:
- [ ] `@Column(name)` matches database column name
- [ ] Column exists in database
- [ ] Data type is compatible
- [ ] Column order matches (if important)

## Nullability Analysis

For each field:
- [ ] Database `NOT NULL` → `@Basic(optional = false)`
- [ ] Database `NOT NULL` → `@Column(nullable = false)`
- [ ] Database `NOT NULL` → `@NotNull` or `@NotBlank`
- [ ] Database `NULL` → `@Basic(optional = true)`
- [ ] Database `NULL` → `@Column(nullable = true)`
- [ ] Database `NULL` → `@Nullable` or no validation annotation
- [ ] Consistency across all fields

## Relationship Verification

For each relationship:
- [ ] `@ManyToOne` / `@OneToMany` / `@ManyToMany` correctly used
- [ ] `@JoinColumn(name)` matches foreign key column
- [ ] `@JoinTable(name)` matches join table
- [ ] Foreign key exists in database
- [ ] Bidirectional relationships properly mapped
- [ ] `mappedBy` correctly specified (if applicable)
- [ ] Fetch type appropriate (LAZY vs EAGER)

## Primary Key Verification

- [ ] `@Id` correctly specified
- [ ] `@GeneratedValue` if needed
- [ ] Composite keys use `@EmbeddedId`
- [ ] Primary key matches database

## Converter Verification

- [ ] Custom converters properly annotated
- [ ] `@Converter(autoApply = true)` if needed
- [ ] Enum converters correctly implemented
- [ ] Converter logic matches database format
- [ ] All converted fields verified

## Validation Annotations

- [ ] `@NotNull` on all NOT NULL fields
- [ ] `@NotBlank` on required string fields
- [ ] `@Nullable` on NULL fields (optional but good practice)
- [ ] Other validations appropriate (e.g., `@Positive`, `@Past`)

## Setter/Getter Methods

- [ ] Setter methods correctly update embedded IDs
- [ ] No bugs in setter logic (wrong ID setters)
- [ ] Getter methods return correct types

## Documentation

- [ ] Create comprehensive analysis document
- [ ] Include all findings
- [ ] Document all issues
- [ ] Provide recommendations
- [ ] Include summary tables
- [ ] Add overall assessment

## Final Verification

- [ ] All critical issues identified
- [ ] All issues fixed or documented
- [ ] Analysis document complete
- [ ] Ready for review

---

## Quick Reference: Common Patterns

### NOT NULL Field Pattern
```java
@NotNull
@Basic(optional = false)
@Column(name = "column_name", nullable = false)
private Type field;
```

### NULL Field Pattern
```java
@Nullable
@Basic(optional = true)
@Column(name = "column_name", nullable = true)
private Type field;
```

### Foreign Key Pattern
```java
@Nullable
@ManyToOne(optional = true, fetch = FetchType.LAZY)
@JoinColumn(name = "fk_column", nullable = true)
private RelatedEntity related;
```

---

## Notes

- Keep this checklist updated with project-specific requirements
- Add custom checks for your domain
- Document any project-specific patterns

