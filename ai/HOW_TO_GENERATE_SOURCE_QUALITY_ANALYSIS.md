# How to Generate Source Quality and Documentation Analysis

This guide walks you through the practical steps to generate a comprehensive source quality and documentation analysis document.

## Quick Start (Automated)

### Step 1: Run the Analysis Script

```bash
# Make the script executable
chmod +x ai/generate_source_quality_analysis.sh

# Run it (defaults to current project)
./ai/generate_source_quality_analysis.sh

# Or specify parameters
./ai/generate_source_quality_analysis.sh project_name output_directory main_src test_src
```

This will create a `source_quality_data/` directory with all extracted information.

### Step 2: Review the Extracted Data

```bash
# View summary
cat source_quality_data/summary.txt

# View file counts
cat source_quality_data/total_file_count.txt
cat source_quality_data/total_loc.txt

# View TODO comments
cat source_quality_data/todo_comments.txt

# View large files
cat source_quality_data/large_files.txt
```

### Step 3: Create Your Analysis Document

1. Copy the template:
   ```bash
   cp ai/SOURCE_QUALITY_ANALYSIS_TEMPLATE.md SOURCE_QUALITY_ANALYSIS.md
   ```

2. Fill in the template using the extracted data
3. Follow the checklist: `ai/source_quality_checklist.md`

---

## Manual Process (Step-by-Step)

### Step 1: Gather Basic Statistics

**Count files:**
```bash
find src/main/java src/test/java -name "*.java" | wc -l
```

**Count lines:**
```bash
find src/main/java src/test/java -name "*.java" -exec wc -l {} + | tail -1
```

**List packages:**
```bash
find src/main/java -name "*.java" -exec grep -h "^package " {} \; | sort -u
```

### Step 2: Analyze Code Structure

**Find classes/interfaces/enums:**
```bash
grep -r "^public class\|^class\|^public interface\|^interface\|^public enum\|^enum" src/main/java
```

**Count methods:**
```bash
grep -r "^\s*public\|^\s*private\|^\s*protected" src/main/java | grep -c "("
```

### Step 3: Analyze Documentation

**Find files with JavaDoc:**
```bash
find src/main/java -name "*.java" -exec grep -l "/\*\*" {} \;
```

**Count JavaDoc blocks:**
```bash
grep -r "/\*\*" src/main/java | grep -c "^\s*/\*\*"
```

**Find TODO/FIXME:**
```bash
grep -rn "TODO\|FIXME\|XXX" src/main/java src/test/java
```

### Step 4: Analyze Test Quality

**Find test files:**
```bash
find src/test/java -name "*Test.java" -o -name "*Tests.java"
```

**Count test methods:**
```bash
grep -r "@Test" src/test/java | wc -l
```

### Step 5: Create the Analysis Document

1. **Start with the template:**
   ```bash
   cp ai/SOURCE_QUALITY_ANALYSIS_TEMPLATE.md SOURCE_QUALITY_ANALYSIS.md
   ```

2. **Fill in executive summary:**
   - Overall assessment
   - Key metrics
   - Critical findings

3. **Fill in project overview:**
   - Source code statistics (from Step 1)
   - Technology stack
   - Project structure

4. **For each package/class, analyze:**
   - Code quality
   - Documentation coverage
   - Complexity
   - Issues found

5. **Create summary sections:**
   - Code quality summary
   - Documentation summary
   - Test quality summary
   - Issues and recommendations
   - Overall assessment

---

## Using Static Analysis Tools

### Maven Projects

**PMD:**
```bash
mvn pmd:check
mvn pmd:cpd-check  # Check for duplicates
```

**Checkstyle:**
```bash
mvn checkstyle:check
```

**SpotBugs:**
```bash
mvn spotbugs:check
```

**JaCoCo (Coverage):**
```bash
mvn clean test jacoco:report
# View report: target/site/jacoco/index.html
```

**JavaDoc:**
```bash
mvn javadoc:javadoc
# View docs: target/site/apidocs/index.html
```

### Gradle Projects

**Similar plugins:**
```bash
./gradlew check
./gradlew jacocoTestReport
./gradlew javadoc
```

---

## Example Analysis Workflow

### For a Single Package

1. **List files in package:**
   ```bash
   find src/main/java/com/example/package -name "*.java"
   ```

2. **Count lines:**
   ```bash
   find src/main/java/com/example/package -name "*.java" -exec wc -l {} +
   ```

3. **Check JavaDoc:**
   ```bash
   find src/main/java/com/example/package -name "*.java" -exec grep -l "/\*\*" {} \;
   ```

4. **Document findings:**
   ```markdown
   ## com.example.package Analysis
   
   ### Statistics
   - Files: 5
   - Lines: 450
   - Classes: 3
   - Interfaces: 1
   - Enums: 1
   
   ### Documentation
   - JavaDoc coverage: 80%
   - Issues: [list]
   
   ### Code Quality
   - Average complexity: 8
   - Issues: [list]
   ```

---

## Tips for Efficiency

### 1. Use the Script First

Run `generate_source_quality_analysis.sh` to get all basic statistics automatically.

### 2. Focus on High-Impact Areas

- Large files (> 500 lines)
- Complex methods (> 15 complexity)
- Undocumented public APIs
- Test files with low coverage

### 3. Use Tools for Metrics

- PMD for complexity
- Checkstyle for style
- JaCoCo for coverage
- SonarQube for comprehensive analysis

### 4. Document as You Go

Don't wait until the end:
- Create "Issues Found" section early
- Add issues as you discover them
- Include file locations

### 5. Use Consistent Formatting

- Same table format throughout
- Consistent status indicators
- Consistent section structure

---

## Quality Score Calculation

### Example Calculation

**Code Quality (40% weight):**
- Structure: 85/100
- Complexity: 80/100
- Best Practices: 90/100
- Average: 85/100

**Documentation (30% weight):**
- JavaDoc Coverage: 75/100
- JavaDoc Quality: 80/100
- Project Docs: 90/100
- Average: 81.67/100

**Test Quality (30% weight):**
- Coverage: 85/100
- Organization: 90/100
- Quality: 80/100
- Average: 85/100

**Overall Score:**
- Code Quality: 85 × 0.40 = 34.0
- Documentation: 81.67 × 0.30 = 24.5
- Test Quality: 85 × 0.30 = 25.5
- **Total: 84.0/100**

---

## Final Checklist

Before considering the document complete:

- [ ] All statistics gathered
- [ ] All packages analyzed
- [ ] Code quality assessed
- [ ] Documentation coverage calculated
- [ ] Test quality evaluated
- [ ] All issues documented
- [ ] Recommendations provided
- [ ] Quality score calculated
- [ ] Overall assessment written
- [ ] Document reviewed

---

## Need Help?

If you get stuck:
1. Review `ai/SOURCE_QUALITY_METHODOLOGY.md` for the process
2. Check `ai/source_quality_checklist.md` for what to verify
3. Use the provided scripts to automate data extraction
4. Use static analysis tools for metrics

---

## Example: Complete Analysis Document Structure

```markdown
# Source Quality and Documentation Analysis - [Project]

## Executive Summary
[Overview, assessment, key findings]

## Project Overview
[Statistics, structure, technology stack]

## Code Quality Analysis
### Structure and Organization
### Naming Conventions
### Code Complexity
### Design Patterns
### Best Practices
### Code Smells

## Documentation Analysis
### JavaDoc Coverage
### JavaDoc Quality
### Code Comments
### Project Documentation

## Test Quality Analysis
### Test Coverage
### Test Organization
### Test Quality

## Code Metrics Summary
## Issues and Recommendations
## Overall Assessment
## Appendix: Detailed Metrics
```

