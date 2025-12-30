# How to Generate Your Analysis Document

This guide walks you through the practical steps to generate a comprehensive persistence analysis document.

## Quick Start (Automated)

### Step 1: Run the Analysis Script

```bash
# Make the script executable
chmod +x generate_analysis.sh

# Run it (defaults to rickandmortyapi.db)
./generate_analysis.sh

# Or specify your database
./generate_analysis.sh path/to/your/database.db output_directory project_name
```

This will create an `analysis_data/` directory with all extracted information.

### Step 2: Review the Extracted Data

```bash
# View the schema
cat analysis_data/schema.txt

# View tables
cat analysis_data/tables.txt

# View table info (example for 'character' table)
cat analysis_data/character_info.txt

# View entity classes
cat analysis_data/entity_classes.txt
```

### Step 3: Create Your Analysis Document

1. Copy the template:
   ```bash
   cp PERSISTENCE_ANALYSIS_TEMPLATE.md MY_PROJECT_ANALYSIS.md
   ```

2. Fill in the template using the extracted data
3. Follow the checklist: `analysis_checklist.md`

---

## Manual Process (Step-by-Step)

If you prefer to do it manually or the script doesn't work for your setup:

### Step 1: Extract Database Schema

**SQLite:**
```bash
sqlite3 your_database.db ".schema" > schema.txt
sqlite3 your_database.db ".tables" > tables.txt
```

**PostgreSQL:**
```bash
pg_dump -s database_name > schema.sql
```

**MySQL:**
```bash
mysqldump --no-data database_name > schema.sql
```

### Step 2: Extract Table Information

For each table, get column details:

**SQLite:**
```bash
sqlite3 your_database.db "PRAGMA table_info(table_name);" > table_name_info.txt
```

**PostgreSQL:**
```sql
SELECT 
    column_name, 
    data_type, 
    is_nullable,
    column_default
FROM information_schema.columns
WHERE table_name = 'your_table';
```

### Step 3: Find Entity Classes

```bash
# Find all @Entity classes
find src/main/java -name "*.java" -exec grep -l "@Entity" {} \; > entities.txt

# Find all @Table annotations
grep -r "@Table" src/main/java/ > table_annotations.txt

# Find all @Column annotations
grep -r "@Column" src/main/java/ > column_annotations.txt
```

### Step 4: Extract Nullability Information

```bash
# Find nullable settings
grep -r "nullable\s*=" src/main/java/ > nullability.txt

# Find @Basic(optional) settings
grep -r "@Basic(optional" src/main/java/ > basic_optional.txt

# Find validation annotations
grep -r "@NotNull\|@NotBlank\|@Nullable" src/main/java/ > validations.txt
```

### Step 5: Create the Analysis Document

1. **Start with the template:**
   ```bash
   cp PERSISTENCE_ANALYSIS_TEMPLATE.md MY_ANALYSIS.md
   ```

2. **Fill in the executive summary:**
   - Project name
   - Database name
   - Overall assessment

3. **List all tables:**
   - Use `tables.txt` from Step 1
   - Create a table with table name, purpose, primary key

4. **For each entity, create a section:**

   **a. Table Mapping:**
   ```markdown
   ### Table Mapping
   - **Entity Class**: `EntityName`
   - **Database Table**: `table_name` ✅ **MATCH**
   ```

   **b. Column Mappings Table:**
   Use the table info from Step 2:
   ```markdown
   | Entity Field | Database Column | Type | Nullable | Status |
   |--------------|----------------|------|----------|--------|
   | `id` | `id` | INTEGER | NOT NULL | ✅ **MATCH** |
   ```

   **c. Nullability Analysis Table:**
   Compare database nullability with JPA annotations:
   ```markdown
   | Field | DB NULL | @Basic(optional) | @Column(nullable) | Validation | Status |
   |-------|---------|------------------|-------------------|------------|--------|
   | `id` | NOT NULL (1) | `false` ✅ | `false` ✅ | `@Positive` ✅ | ✅ **MATCH** |
   ```

   **d. Relationships:**
   ```markdown
   ### Relationships
   - ✅ `relatedEntity` → `RelatedEntity` (ManyToOne, optional, lazy)
   ```

   **e. Issues Found:**
   ```markdown
   ### Issues Found
   - **None** - All mappings are correct.
   ```

### Step 6: Create Summary Sections

1. **Foreign Key Constraints:**
   - List all foreign keys from schema
   - Verify entity mappings

2. **Indexes:**
   - List all indexes from schema
   - Note they're database-level

3. **Converter Analysis:**
   - List all custom converters
   - List all enum converters

4. **Summary of Issues:**
   - Critical issues
   - Warnings
   - Positive findings

5. **Recommendations:**
   - Fixes needed
   - Future considerations

6. **Overall Assessment:**
   - Metrics
   - Final verdict

---

## Using the Checklist

While generating the document, use `analysis_checklist.md`:

1. Open the checklist
2. Check off items as you complete them
3. This ensures you don't miss anything

---

## Example Workflow

Here's a complete example for a single entity:

### 1. Extract Table Info
```bash
sqlite3 db.db "PRAGMA table_info(character);"
# Output:
# 0|id|INTEGER|1||1
# 1|name|TEXT|1||0
# Column 3: 1=NOT NULL, 0=NULL
```

### 2. Check Entity Class
```java
@Entity
@Table(name = "character")
public class Character {
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @NotBlank
    @Basic(optional = false)
    @Column(name = "name", nullable = false)
    private String name;
}
```

### 3. Create Analysis Entry
```markdown
## Character Entity Analysis

### Table Mapping
- **Entity Class**: `Character`
- **Database Table**: `character` ✅ **MATCH**

### Column Mappings
| Entity Field | Database Column | Type | Nullable | Status |
|--------------|----------------|------|----------|--------|
| `id` | `id` | INTEGER | NOT NULL | ✅ **MATCH** |
| `name` | `name` | TEXT | NOT NULL | ✅ **MATCH** |

### Nullability Analysis
| Field | DB NULL | @Basic(optional) | @Column(nullable) | Validation | Status |
|-------|---------|------------------|-------------------|------------|--------|
| `id` | NOT NULL (1) | `false` ✅ | `false` ✅ | `@Positive` ✅ | ✅ **MATCH** |
| `name` | NOT NULL (1) | `false` ✅ | `false` ✅ | `@NotBlank` ✅ | ✅ **MATCH** |
```

---

## Tips for Efficiency

### 1. Use Tables for Comparison

Create side-by-side tables to easily spot mismatches:
- Column mapping table
- Nullability analysis table

### 2. Use Scripts for Repetitive Tasks

The `generate_analysis.sh` script automates:
- Schema extraction
- Table info extraction
- Code annotation extraction

### 3. Work Entity-by-Entity

Don't try to do everything at once:
1. Pick one entity
2. Complete all sections for that entity
3. Move to the next

### 4. Document Issues as You Find Them

Don't wait until the end:
- Create an "Issues Found" section
- Add issues as you discover them
- Fix them immediately if possible

### 5. Use Consistent Formatting

- Use the same table format throughout
- Use consistent status indicators (✅ **MATCH**, ⚠️ **ISSUE**, etc.)
- Use consistent section structure

---

## Automation Ideas

### Create a Python Script

You could create a Python script to:
- Parse database schema
- Parse Java entity classes
- Generate markdown tables automatically
- Compare and highlight mismatches

### Use Database Tools

- **DBeaver**: Export schema to markdown
- **IntelliJ IDEA**: Database tools can show schema
- **pgAdmin**: Can export schema documentation

### Use JPA Tools

- **Hibernate Tools**: Can generate schema documentation
- **EclipseLink**: Has schema generation tools

---

## Final Checklist

Before considering the document complete:

- [ ] All entities analyzed
- [ ] All tables mapped
- [ ] All columns verified
- [ ] All nullability constraints checked
- [ ] All relationships verified
- [ ] All issues documented
- [ ] All fixes applied
- [ ] Summary sections complete
- [ ] Overall assessment written
- [ ] Document reviewed

---

## Example: Complete Analysis Document Structure

```markdown
# Persistence Classes Analysis Against [database]

## Executive Summary
[Overview, assessment, key findings]

## Database Schema Overview
[Table list with purposes]

## Analysis Methodology
[How you did the analysis]

## 1. Entity1 Analysis
### Table Mapping
### Column Mappings
### Nullability Analysis
### Relationships
### Issues Found

## 2. Entity2 Analysis
[...]

## Foreign Key Constraints
## Indexes
## Converter Analysis
## Summary of Issues
## Recommendations
## Overall Assessment
## Appendix: Nullability Summary
```

---

## Need Help?

If you get stuck:
1. Review `ANALYSIS_METHODOLOGY.md` for the process
2. Check `analysis_checklist.md` for what to verify
3. Look at `PERSISTENCE_ANALYSIS.md` (this project) as an example
4. Use the provided scripts to automate data extraction

