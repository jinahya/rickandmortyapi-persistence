# Persistence Analysis Template

This template can be reused for analyzing JPA persistence classes against database schemas in other projects.

## Quick Start Checklist

- [ ] Extract database schema (`.schema` command or equivalent)
- [ ] List all entity classes
- [ ] Verify table name mappings
- [ ] Verify column name mappings
- [ ] Check nullability constraints
- [ ] Verify foreign key relationships
- [ ] Check converter implementations
- [ ] Verify enum mappings
- [ ] Document indexes
- [ ] Create comprehensive analysis document

---

## Analysis Methodology

### 1. Database Schema Extraction

```bash
# SQLite
sqlite3 database.db ".schema" > schema.txt
sqlite3 database.db "PRAGMA table_info(table_name);" > table_info.txt

# PostgreSQL
pg_dump -s database_name > schema.sql

# MySQL
mysqldump --no-data database_name > schema.sql
```

### 2. Entity Class Discovery

```bash
# Find all @Entity classes
grep -r "@Entity" src/main/java/

# Find all @Table annotations
grep -r "@Table" src/main/java/
```

### 3. Column Mapping Verification

For each entity, verify:
- [ ] `@Table(name)` matches database table name
- [ ] `@Column(name)` matches database column name
- [ ] Data type compatibility
- [ ] Nullability matches

### 4. Nullability Analysis

For each field, check:
- [ ] Database `NOT NULL` → `@Basic(optional = false)`, `nullable = false`, `@NotNull`/`@NotBlank`
- [ ] Database `NULL` → `@Basic(optional = true)`, `nullable = true`, `@Nullable` or no validation annotation

### 5. Relationship Verification

- [ ] `@ManyToOne` / `@OneToMany` / `@ManyToMany` correctly mapped
- [ ] `@JoinColumn` names match foreign key columns
- [ ] `@JoinTable` names match join tables
- [ ] Fetch types appropriate (LAZY vs EAGER)

### 6. Converter Verification

- [ ] Custom converters properly annotated
- [ ] Enum converters auto-applied or explicitly specified
- [ ] Converter logic matches database storage format

---

## Analysis Document Structure

```markdown
# Persistence Classes Analysis Against [database_name]

## Executive Summary
- Overall assessment
- Key findings
- Critical issues

## Database Schema Overview
- List of tables
- Primary keys
- Foreign keys

## Entity-by-Entity Analysis
For each entity:
- Table mapping
- Column mappings (table format)
- Nullability analysis (table format)
- Relationships
- Issues found

## Foreign Key Constraints
- List all FKs
- Verify entity mappings

## Indexes
- List all indexes
- Note: Indexes are DB-level, not JPA

## Converter Analysis
- Custom converters
- Enum converters

## Summary of Issues
- Critical issues
- Warnings
- Positive findings

## Recommendations
- Fixes needed
- Future considerations

## Overall Assessment
- Metrics
- Final verdict
```

---

## Common Issues to Check

### Critical Issues
- [ ] Setter methods calling wrong ID setter (e.g., `setLocationId()` instead of `setResidentId()`)
- [ ] Missing `@Basic(optional = false)` on NOT NULL fields
- [ ] Missing `@NotNull` on NOT NULL fields
- [ ] Incorrect `nullable` attribute in `@Column`
- [ ] Foreign key column names don't match

### Consistency Issues
- [ ] Inconsistent annotation patterns across entities
- [ ] Missing validation annotations
- [ ] Converter not properly configured

### Relationship Issues
- [ ] Bidirectional relationships not properly mapped
- [ ] Join table names don't match
- [ ] Join column names incorrect

---

## Automation Script Template

```bash
#!/bin/bash
# persistence_analysis.sh

DB_FILE="path/to/database.db"
OUTPUT_DIR="analysis_output"

mkdir -p "$OUTPUT_DIR"

# Extract schema
sqlite3 "$DB_FILE" ".schema" > "$OUTPUT_DIR/schema.txt"

# Extract table info for each table
for table in $(sqlite3 "$DB_FILE" ".tables"); do
    sqlite3 "$DB_FILE" "PRAGMA table_info($table);" > "$OUTPUT_DIR/${table}_info.txt"
done

# Find entity classes
find src/main/java -name "*.java" -exec grep -l "@Entity" {} \; > "$OUTPUT_DIR/entity_classes.txt"

echo "Analysis data extracted to $OUTPUT_DIR/"
```

---

## Reusable Commands

### Extract Database Schema
```bash
# SQLite
sqlite3 database.db ".schema" | cat

# Get table info
sqlite3 database.db "PRAGMA table_info(table_name);" | cat

# List all tables
sqlite3 database.db ".tables" | cat
```

### Find JPA Annotations
```bash
# Find @Basic annotations
grep -r "@Basic" src/main/java/

# Find @Column annotations
grep -r "@Column" src/main/java/

# Find @NotNull/@NotBlank
grep -r "@NotNull\|@NotBlank" src/main/java/

# Find nullable settings
grep -r "nullable\s*=" src/main/java/
```

### Verify Nullability
```bash
# Check for @Basic(optional = false) on NOT NULL fields
# Check for @Basic(optional = true) on NULL fields
# Check for @NotNull on NOT NULL fields
# Check for @Nullable on NULL fields
```

---

## Template Variables

Replace these in the template:
- `[database_name]` - Name of your database
- `[project_name]` - Name of your project
- `[entity_count]` - Number of entities
- `[table_count]` - Number of tables

---

## Tips for Reusability

1. **Save the template** in a shared location or version control
2. **Create a checklist** for each new project
3. **Use the same table format** for consistency
4. **Document methodology** so others can follow
5. **Automate schema extraction** where possible
6. **Keep a library** of common issues found

---

## Example Workflow

1. Clone this template
2. Extract database schema
3. List all entity classes
4. For each entity:
   - Create table mapping section
   - Create column mapping table
   - Create nullability analysis table
   - Document relationships
   - Note any issues
5. Create summary sections
6. Generate final assessment

---

## Version History

- v1.0 - Initial template based on rickandmortyapi-persistence analysis

