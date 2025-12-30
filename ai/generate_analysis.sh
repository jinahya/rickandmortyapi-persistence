#!/bin/bash
# generate_analysis.sh - Automated script to gather data for persistence analysis

set -e

# Configuration
DB_FILE="${1:-src/test/resources/rickandmortyapi.db}"
OUTPUT_DIR="${2:-analysis_data}"
PROJECT_NAME="${3:-rickandmortyapi-persistence}"

echo "Generating analysis data for $PROJECT_NAME"
echo "Database: $DB_FILE"
echo "Output directory: $OUTPUT_DIR"
echo ""

# Create output directory
mkdir -p "$OUTPUT_DIR"

# 1. Extract complete database schema
echo "1. Extracting database schema..."
sqlite3 "$DB_FILE" ".schema" > "$OUTPUT_DIR/schema.txt"
echo "   ✓ Schema saved to $OUTPUT_DIR/schema.txt"

# 2. List all tables
echo "2. Listing all tables..."
sqlite3 "$DB_FILE" ".tables" > "$OUTPUT_DIR/tables.txt"
echo "   ✓ Tables saved to $OUTPUT_DIR/tables.txt"

# 3. Extract table info for each table
echo "3. Extracting table information..."
TABLES=$(sqlite3 "$DB_FILE" ".tables" | tr ' ' '\n')
for table in $TABLES; do
    if [ ! -z "$table" ]; then
        sqlite3 "$DB_FILE" "PRAGMA table_info($table);" > "$OUTPUT_DIR/${table}_info.txt"
        echo "   ✓ Table info for $table saved"
    fi
done

# 4. Extract foreign keys
echo "4. Extracting foreign key information..."
for table in $TABLES; do
    if [ ! -z "$table" ]; then
        sqlite3 "$DB_FILE" "PRAGMA foreign_key_list($table);" > "$OUTPUT_DIR/${table}_fks.txt" 2>/dev/null || true
    fi
done
echo "   ✓ Foreign keys extracted"

# 5. Extract indexes
echo "5. Extracting index information..."
sqlite3 "$DB_FILE" "SELECT name, tbl_name, sql FROM sqlite_master WHERE type='index' AND sql IS NOT NULL;" > "$OUTPUT_DIR/indexes.txt"
echo "   ✓ Indexes saved to $OUTPUT_DIR/indexes.txt"

# 6. Find all entity classes
echo "6. Finding entity classes..."
find src/main/java -name "*.java" -exec grep -l "@Entity" {} \; > "$OUTPUT_DIR/entity_classes.txt" 2>/dev/null || true
echo "   ✓ Entity classes saved to $OUTPUT_DIR/entity_classes.txt"

# 7. Extract table names from entities
echo "7. Extracting @Table annotations..."
grep -r "@Table" src/main/java/ 2>/dev/null | grep "name\s*=" > "$OUTPUT_DIR/table_annotations.txt" || true
echo "   ✓ Table annotations saved to $OUTPUT_DIR/table_annotations.txt"

# 8. Extract column annotations
echo "8. Extracting @Column annotations..."
grep -r "@Column" src/main/java/ 2>/dev/null > "$OUTPUT_DIR/column_annotations.txt" || true
echo "   ✓ Column annotations saved to $OUTPUT_DIR/column_annotations.txt"

# 9. Extract nullability information
echo "9. Extracting nullability information..."
grep -r "nullable\s*=" src/main/java/ 2>/dev/null > "$OUTPUT_DIR/nullability_info.txt" || true
grep -r "@Basic(optional" src/main/java/ 2>/dev/null > "$OUTPUT_DIR/basic_optional_info.txt" || true
grep -r "@NotNull\|@NotBlank\|@Nullable" src/main/java/ 2>/dev/null > "$OUTPUT_DIR/validation_annotations.txt" || true
echo "   ✓ Nullability information extracted"

# 10. Create summary report
echo "10. Creating summary report..."
cat > "$OUTPUT_DIR/summary.txt" <<EOF
Analysis Data Summary
====================
Generated: $(date)
Database: $DB_FILE
Project: $PROJECT_NAME

Tables found: $(wc -l < "$OUTPUT_DIR/tables.txt" | tr -d ' ')
Entity classes found: $(wc -l < "$OUTPUT_DIR/entity_classes.txt" 2>/dev/null | tr -d ' ' || echo "0")
Indexes found: $(wc -l < "$OUTPUT_DIR/indexes.txt" | tr -d ' ')

Files generated:
- schema.txt: Complete database schema
- tables.txt: List of all tables
- *_info.txt: Column information for each table
- *_fks.txt: Foreign key information for each table
- indexes.txt: All indexes
- entity_classes.txt: List of entity class files
- table_annotations.txt: @Table annotations
- column_annotations.txt: @Column annotations
- nullability_info.txt: nullable settings
- basic_optional_info.txt: @Basic(optional) settings
- validation_annotations.txt: @NotNull/@NotBlank/@Nullable

Next steps:
1. Review the extracted data
2. Use PERSISTENCE_ANALYSIS_TEMPLATE.md as a guide
3. Fill in the analysis document with findings
4. Use analysis_checklist.md to track progress
EOF

echo "   ✓ Summary report created"

echo ""
echo "=========================================="
echo "Analysis data extraction complete!"
echo "=========================================="
echo ""
echo "All data saved to: $OUTPUT_DIR/"
echo ""
echo "Next steps:"
echo "1. Review the extracted data in $OUTPUT_DIR/"
echo "2. Use PERSISTENCE_ANALYSIS_TEMPLATE.md to create your analysis document"
echo "3. Follow ANALYSIS_METHODOLOGY.md for the process"
echo "4. Use analysis_checklist.md to track your progress"
echo ""

