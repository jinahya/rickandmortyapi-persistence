# Persistence Analysis Methodology

This document describes the methodology used to analyze JPA persistence classes against database schemas. This process can be reused for any JPA project.

## Overview

The goal is to ensure that JPA entity classes accurately represent the database schema, with particular attention to:
- Table and column mappings
- Nullability constraints
- Relationships and foreign keys
- Type conversions
- Data integrity

## Step-by-Step Process

### Step 1: Extract Database Schema

**SQLite:**
```bash
sqlite3 database.db ".schema" > schema.txt
sqlite3 database.db ".tables" > tables.txt
sqlite3 database.db "PRAGMA table_info(table_name);" > table_info.txt
```

**PostgreSQL:**
```bash
pg_dump -s database_name > schema.sql
```

**MySQL:**
```bash
mysqldump --no-data database_name > schema.sql
```

### Step 2: Discover Entity Classes

```bash
# Find all entity classes
find src/main/java -name "*.java" -exec grep -l "@Entity" {} \;

# Find all table names
grep -r "@Table" src/main/java/ | grep "name ="
```

### Step 3: Verify Table Mappings

For each entity:
1. Check `@Table(name)` matches database table
2. Verify table exists in database
3. Document any mismatches

### Step 4: Verify Column Mappings

For each entity field:
1. Check `@Column(name)` matches database column
2. Verify column exists in database
3. Check data type compatibility
4. Document in table format

### Step 5: Analyze Nullability

This is the most critical step:

1. **Extract database nullability:**
   ```bash
   sqlite3 database.db "PRAGMA table_info(table_name);" | awk -F'|' '{print $2 "|" $4}'
   # Column 4: 1 = NOT NULL, 0 = NULL
   ```

2. **Check JPA annotations:**
   - `@Basic(optional = false)` for NOT NULL
   - `@Basic(optional = true)` for NULL
   - `@Column(nullable = false)` for NOT NULL
   - `@Column(nullable = true)` for NULL
   - `@NotNull` or `@NotBlank` for NOT NULL
   - `@Nullable` for NULL (optional but recommended)

3. **Create verification table:**
   | Field | DB NULL | @Basic(optional) | @Column(nullable) | Validation | Status |

### Step 6: Verify Relationships

1. Check `@ManyToOne`, `@OneToMany`, `@ManyToMany` usage
2. Verify `@JoinColumn(name)` matches foreign key column
3. Check `@JoinTable(name)` matches join table
4. Verify foreign keys exist in database
5. Check bidirectional relationships are properly mapped

### Step 7: Verify Converters

1. List all custom converters
2. Check `@Converter(autoApply = true)` usage
3. Verify enum converters
4. Test converter logic matches database format

### Step 8: Check for Common Bugs

1. **Setter bugs:** Check setter methods don't call wrong ID setters
   ```java
   // BAD
   .setLocationId(character.getId())  // Should be setResidentId!
   
   // GOOD
   .setResidentId(character.getId())
   ```

2. **Missing annotations:** Check all NOT NULL fields have proper annotations
3. **Inconsistent patterns:** Check annotation patterns are consistent

### Step 9: Document Findings

Create comprehensive document with:
- Executive summary
- Entity-by-entity analysis
- Nullability analysis tables
- Issue summary
- Recommendations
- Overall assessment

### Step 10: Fix Issues

1. Fix critical bugs
2. Fix consistency issues
3. Update documentation
4. Re-verify after fixes

## Tools and Commands

### Database Inspection

```bash
# SQLite - Get schema
sqlite3 db.db ".schema"

# SQLite - Get table info
sqlite3 db.db "PRAGMA table_info(table_name);"

# SQLite - List tables
sqlite3 db.db ".tables"

# SQLite - Get foreign keys
sqlite3 db.db "PRAGMA foreign_key_list(table_name);"
```

### Code Inspection

```bash
# Find @Entity classes
grep -r "@Entity" src/main/java/

# Find @Table annotations
grep -r "@Table" src/main/java/

# Find @Basic annotations
grep -r "@Basic" src/main/java/

# Find @Column annotations
grep -r "@Column" src/main/java/

# Find nullability settings
grep -r "nullable\s*=" src/main/java/

# Find validation annotations
grep -r "@NotNull\|@NotBlank\|@Nullable" src/main/java/
```

## Verification Tables

### Column Mapping Table
| Entity Field | Database Column | Type | Nullable | Status |

### Nullability Analysis Table
| Field | DB NULL | @Basic(optional) | @Column(nullable) | Validation | Status |

## Common Patterns

### Pattern 1: NOT NULL Field
```java
@NotNull                    // Validation
@Basic(optional = false)    // JPA
@Column(name = "col", nullable = false)  // Column definition
private Type field;
```

### Pattern 2: NULL Field
```java
@Nullable                   // Validation (optional but recommended)
@Basic(optional = true)     // JPA
@Column(name = "col", nullable = true)   // Column definition
private Type field;
```

### Pattern 3: Foreign Key (Optional)
```java
@Nullable
@ManyToOne(optional = true, fetch = FetchType.LAZY)
@JoinColumn(name = "fk_col", nullable = true)
private RelatedEntity related;
```

## Quality Metrics

Track these metrics:
- **Table Mapping Accuracy**: % of tables correctly mapped
- **Column Mapping Accuracy**: % of columns correctly mapped
- **Nullability Consistency**: % of fields with matching nullability
- **Relationship Accuracy**: % of relationships correctly mapped
- **Issue Count**: Number of critical/warning issues

## Best Practices

1. **Be systematic:** Go through each entity methodically
2. **Document everything:** Even if it's correct, document it
3. **Use tables:** Tables make comparisons easy
4. **Verify fixes:** Re-check after making fixes
5. **Keep templates:** Reuse analysis structure
6. **Automate where possible:** Use scripts for repetitive tasks

## Reusability

To reuse this methodology:

1. **Copy the template** (`PERSISTENCE_ANALYSIS_TEMPLATE.md`)
2. **Use the checklist** (`analysis_checklist.md`)
3. **Follow this methodology** step by step
4. **Adapt for your database** (SQLite, PostgreSQL, MySQL, etc.)
5. **Customize for your project** (add project-specific checks)

## Example Workflow

```bash
# 1. Extract schema
sqlite3 project.db ".schema" > analysis/schema.txt

# 2. Find entities
find src/main/java -name "*Entity.java" > analysis/entities.txt

# 3. For each entity, verify:
#    - Table mapping
#    - Column mappings
#    - Nullability
#    - Relationships

# 4. Generate analysis document
# 5. Fix issues
# 6. Re-verify
```

## Version History

- v1.0 - Initial methodology based on rickandmortyapi-persistence analysis

