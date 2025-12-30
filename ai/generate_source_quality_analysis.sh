#!/bin/bash
# generate_source_quality_analysis.sh - Automated script to gather data for source quality analysis

set -e

# Configuration
PROJECT_NAME="${1:-rickandmortyapi-persistence}"
OUTPUT_DIR="${2:-source_quality_data}"
MAIN_SRC="${3:-src/main/java}"
TEST_SRC="${4:-src/test/java}"

echo "Generating source quality analysis data for $PROJECT_NAME"
echo "Main source: $MAIN_SRC"
echo "Test source: $TEST_SRC"
echo "Output directory: $OUTPUT_DIR"
echo ""

# Create output directory
mkdir -p "$OUTPUT_DIR"

# 1. Count Java files
echo "1. Counting Java files..."
MAIN_FILES=$(find "$MAIN_SRC" -name "*.java" -type f 2>/dev/null | wc -l | tr -d ' ')
TEST_FILES=$(find "$TEST_SRC" -name "*.java" -type f 2>/dev/null | wc -l | tr -d ' ')
TOTAL_FILES=$((MAIN_FILES + TEST_FILES))
echo "$MAIN_FILES" > "$OUTPUT_DIR/main_file_count.txt"
echo "$TEST_FILES" > "$OUTPUT_DIR/test_file_count.txt"
echo "$TOTAL_FILES" > "$OUTPUT_DIR/total_file_count.txt"
echo "   ✓ Main: $MAIN_FILES files, Test: $TEST_FILES files, Total: $TOTAL_FILES files"

# 2. Count lines of code
echo "2. Counting lines of code..."
MAIN_LOC=$(find "$MAIN_SRC" -name "*.java" -type f -exec wc -l {} + 2>/dev/null | tail -1 | awk '{print $1}' || echo "0")
TEST_LOC=$(find "$TEST_SRC" -name "*.java" -type f -exec wc -l {} + 2>/dev/null | tail -1 | awk '{print $1}' || echo "0")
TOTAL_LOC=$((MAIN_LOC + TEST_LOC))
echo "$MAIN_LOC" > "$OUTPUT_DIR/main_loc.txt"
echo "$TEST_LOC" > "$OUTPUT_DIR/test_loc.txt"
echo "$TOTAL_LOC" > "$OUTPUT_DIR/total_loc.txt"
echo "   ✓ Main: $MAIN_LOC lines, Test: $TEST_LOC lines, Total: $TOTAL_LOC lines"

# 3. List all Java files
echo "3. Listing all Java files..."
find "$MAIN_SRC" -name "*.java" -type f 2>/dev/null > "$OUTPUT_DIR/main_files.txt" || touch "$OUTPUT_DIR/main_files.txt"
find "$TEST_SRC" -name "*.java" -type f 2>/dev/null > "$OUTPUT_DIR/test_files.txt" || touch "$OUTPUT_DIR/test_files.txt"
echo "   ✓ File lists saved"

# 4. Extract package structure
echo "4. Extracting package structure..."
find "$MAIN_SRC" -name "*.java" -type f -exec grep -h "^package " {} \; 2>/dev/null | sort -u > "$OUTPUT_DIR/main_packages.txt" || touch "$OUTPUT_DIR/main_packages.txt"
find "$TEST_SRC" -name "*.java" -type f -exec grep -h "^package " {} \; 2>/dev/null | sort -u > "$OUTPUT_DIR/test_packages.txt" || touch "$OUTPUT_DIR/test_packages.txt"
echo "   ✓ Package structure extracted"

# 5. Find classes, interfaces, enums
echo "5. Finding classes, interfaces, and enums..."
grep -r "^public class\|^class\|^public interface\|^interface\|^public enum\|^enum" "$MAIN_SRC" 2>/dev/null > "$OUTPUT_DIR/main_types.txt" || touch "$OUTPUT_DIR/main_types.txt"
grep -r "^public class\|^class\|^public interface\|^interface\|^public enum\|^enum" "$TEST_SRC" 2>/dev/null > "$OUTPUT_DIR/test_types.txt" || touch "$OUTPUT_DIR/test_types.txt"
echo "   ✓ Types extracted"

# 6. Count methods
echo "6. Counting methods..."
MAIN_METHODS=$(grep -r "^\s*public\|^\s*private\|^\s*protected" "$MAIN_SRC" 2>/dev/null | grep -c "(" || echo "0")
TEST_METHODS=$(grep -r "^\s*public\|^\s*private\|^\s*protected\|@Test" "$TEST_SRC" 2>/dev/null | grep -c "(" || echo "0")
echo "$MAIN_METHODS" > "$OUTPUT_DIR/main_method_count.txt"
echo "$TEST_METHODS" > "$OUTPUT_DIR/test_method_count.txt"
echo "   ✓ Main: $MAIN_METHODS methods, Test: $TEST_METHODS methods"

# 7. Extract JavaDoc information
echo "7. Extracting JavaDoc information..."
# Find files with JavaDoc
find "$MAIN_SRC" -name "*.java" -type f -exec grep -l "/\*\*" {} \; 2>/dev/null > "$OUTPUT_DIR/main_javadoc_files.txt" || touch "$OUTPUT_DIR/main_javadoc_files.txt"
find "$TEST_SRC" -name "*.java" -type f -exec grep -l "/\*\*" {} \; 2>/dev/null > "$OUTPUT_DIR/test_javadoc_files.txt" || touch "$OUTPUT_DIR/test_javadoc_files.txt"

# Count JavaDoc blocks
MAIN_JAVADOC=$(grep -r "/\*\*" "$MAIN_SRC" 2>/dev/null | grep -c "^\s*/\*\*" || echo "0")
TEST_JAVADOC=$(grep -r "/\*\*" "$TEST_SRC" 2>/dev/null | grep -c "^\s*/\*\*" || echo "0")
echo "$MAIN_JAVADOC" > "$OUTPUT_DIR/main_javadoc_count.txt"
echo "$TEST_JAVADOC" > "$OUTPUT_DIR/test_javadoc_count.txt"
echo "   ✓ JavaDoc blocks: Main: $MAIN_JAVADOC, Test: $TEST_JAVADOC"

# 8. Find TODO/FIXME comments
echo "8. Finding TODO/FIXME comments..."
grep -rn "TODO\|FIXME\|XXX" "$MAIN_SRC" "$TEST_SRC" 2>/dev/null > "$OUTPUT_DIR/todo_comments.txt" || touch "$OUTPUT_DIR/todo_comments.txt"
TODO_COUNT=$(grep -c "TODO" "$OUTPUT_DIR/todo_comments.txt" 2>/dev/null || echo "0")
FIXME_COUNT=$(grep -c "FIXME" "$OUTPUT_DIR/todo_comments.txt" 2>/dev/null || echo "0")
echo "   ✓ TODO: $TODO_COUNT, FIXME: $FIXME_COUNT"

# 9. Extract @param, @return, @throws tags
echo "9. Extracting JavaDoc tags..."
grep -r "@param\|@return\|@throws\|@since\|@author\|@see\|@deprecated" "$MAIN_SRC" "$TEST_SRC" 2>/dev/null > "$OUTPUT_DIR/javadoc_tags.txt" || touch "$OUTPUT_DIR/javadoc_tags.txt"
echo "   ✓ JavaDoc tags extracted"

# 10. Find test files
echo "10. Finding test files..."
find "$TEST_SRC" -name "*Test.java" -o -name "*Tests.java" 2>/dev/null > "$OUTPUT_DIR/test_files_list.txt" || touch "$OUTPUT_DIR/test_files_list.txt"
TEST_FILE_COUNT=$(wc -l < "$OUTPUT_DIR/test_files_list.txt" | tr -d ' ')
echo "   ✓ Test files: $TEST_FILE_COUNT"

# 11. Extract annotations
echo "11. Extracting annotations..."
grep -r "@Test\|@BeforeEach\|@AfterEach\|@BeforeAll\|@AfterAll\|@DisplayName" "$TEST_SRC" 2>/dev/null > "$OUTPUT_DIR/test_annotations.txt" || touch "$OUTPUT_DIR/test_annotations.txt"
echo "   ✓ Test annotations extracted"

# 12. Find large files
echo "12. Finding large files..."
find "$MAIN_SRC" "$TEST_SRC" -name "*.java" -type f -exec wc -l {} + 2>/dev/null | sort -rn | head -20 > "$OUTPUT_DIR/large_files.txt" || touch "$OUTPUT_DIR/large_files.txt"
echo "   ✓ Large files identified"

# 13. Extract imports
echo "13. Extracting imports..."
find "$MAIN_SRC" -name "*.java" -type f -exec grep -h "^import " {} \; 2>/dev/null | sort -u > "$OUTPUT_DIR/main_imports.txt" || touch "$OUTPUT_DIR/main_imports.txt"
find "$TEST_SRC" -name "*.java" -type f -exec grep -h "^import " {} \; 2>/dev/null | sort -u > "$OUTPUT_DIR/test_imports.txt" || touch "$OUTPUT_DIR/test_imports.txt"
echo "   ✓ Imports extracted"

# 14. Check for common code smells
echo "14. Checking for common code smells..."
# Long methods (rough estimate - methods with many lines)
grep -rn "public\|private\|protected" "$MAIN_SRC" 2>/dev/null | awk -F: '{print $1}' | sort | uniq -c | sort -rn | head -10 > "$OUTPUT_DIR/potential_long_methods.txt" || touch "$OUTPUT_DIR/potential_long_methods.txt"

# Magic numbers (numbers in code)
grep -rn "[^a-zA-Z_]\b[0-9]\{2,\}\b" "$MAIN_SRC" 2>/dev/null | grep -v "//\|/\*" | head -20 > "$OUTPUT_DIR/potential_magic_numbers.txt" || touch "$OUTPUT_DIR/potential_magic_numbers.txt"
echo "   ✓ Code smell indicators extracted"

# 15. Create summary report
echo "15. Creating summary report..."
cat > "$OUTPUT_DIR/summary.txt" <<EOF
Source Quality Analysis Data Summary
====================================
Generated: $(date)
Project: $PROJECT_NAME

Source Statistics:
- Main Java files: $MAIN_FILES
- Test Java files: $TEST_FILES
- Total Java files: $TOTAL_FILES
- Main lines of code: $MAIN_LOC
- Test lines of code: $TEST_LOC
- Total lines of code: $TOTAL_LOC
- Main methods: $MAIN_METHODS
- Test methods: $TEST_METHODS
- Main JavaDoc blocks: $MAIN_JAVADOC
- Test JavaDoc blocks: $TEST_JAVADOC
- TODO comments: $TODO_COUNT
- FIXME comments: $FIXME_COUNT
- Test files: $TEST_FILE_COUNT

Files generated:
- main_files.txt: List of main source files
- test_files.txt: List of test source files
- main_packages.txt: Package structure (main)
- test_packages.txt: Package structure (test)
- main_types.txt: Classes, interfaces, enums (main)
- test_types.txt: Classes, interfaces, enums (test)
- todo_comments.txt: TODO/FIXME comments
- javadoc_tags.txt: JavaDoc tags
- test_files_list.txt: Test files
- test_annotations.txt: Test annotations
- large_files.txt: Largest files
- main_imports.txt: Imports (main)
- test_imports.txt: Imports (test)
- potential_long_methods.txt: Potential long methods
- potential_magic_numbers.txt: Potential magic numbers

Next steps:
1. Review the extracted data in $OUTPUT_DIR/
2. Use SOURCE_QUALITY_ANALYSIS_TEMPLATE.md to create your analysis document
3. Follow source_quality_checklist.md to track your progress
4. Use SOURCE_QUALITY_METHODOLOGY.md for the process
EOF

echo "   ✓ Summary report created"

echo ""
echo "=========================================="
echo "Source quality data extraction complete!"
echo "=========================================="
echo ""
echo "All data saved to: $OUTPUT_DIR/"
echo ""
echo "Next steps:"
echo "1. Review the extracted data in $OUTPUT_DIR/"
echo "2. Use ai/SOURCE_QUALITY_ANALYSIS_TEMPLATE.md to create your analysis document"
echo "3. Follow ai/source_quality_checklist.md to track your progress"
echo "4. Use ai/SOURCE_QUALITY_METHODOLOGY.md for the process"
echo ""

