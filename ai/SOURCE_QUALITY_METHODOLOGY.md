# Source Quality and Documentation Analysis Methodology

This document describes the methodology used to analyze Java source code quality and documentation. This process can be reused for any Java project.

## Overview

The goal is to assess:
- **Code Quality**: Structure, complexity, best practices, design patterns
- **Documentation**: JavaDoc coverage, code comments, project documentation
- **Test Quality**: Coverage, organization, test practices

## Step-by-Step Process

### Step 1: Gather Source Code Statistics

**Count files and lines:**
```bash
# Count Java files
find src/main/java src/test/java -name "*.java" | wc -l

# Count lines of code
find src/main/java src/test/java -name "*.java" -exec wc -l {} + | tail -1
```

**Extract structure:**
```bash
# List all packages
find src/main/java -name "*.java" -exec grep -h "^package " {} \; | sort -u

# Find all classes/interfaces/enums
grep -r "^public class\|^class\|^public interface\|^interface\|^public enum\|^enum" src/main/java
```

### Step 2: Analyze Code Structure

1. **Package Organization**
   - Check if packages follow logical grouping
   - Verify no circular dependencies
   - Assess package naming

2. **Class Organization**
   - Check class size (lines, methods)
   - Verify single responsibility
   - Check for god classes

3. **File Naming**
   - Verify Java naming conventions
   - Check consistency

### Step 3: Evaluate Code Quality

#### 3.1 Complexity Analysis

**Cyclomatic Complexity:**
- Use tools like PMD, Checkstyle, or SonarQube
- Identify methods with complexity > 10
- Identify methods with complexity > 20

**Manual assessment:**
- Count decision points (if, while, for, switch, catch, &&, ||)
- Complexity = Decision points + 1

#### 3.2 Code Metrics

**Calculate:**
- Average lines per method
- Average lines per class
- Average methods per class
- Maximum class size
- Maximum method size

#### 3.3 Code Smells

**Check for:**
- Long methods (> 50 lines)
- Long parameter lists (> 5 parameters)
- Duplicate code
- Dead code
- Magic numbers
- God classes (> 1000 lines)
- Feature envy
- Data clumps

#### 3.4 Best Practices

**SOLID Principles:**
- Single Responsibility: Each class has one reason to change
- Open/Closed: Open for extension, closed for modification
- Liskov Substitution: Subtypes must be substitutable
- Interface Segregation: Many specific interfaces
- Dependency Inversion: Depend on abstractions

**Other Practices:**
- Error handling
- Resource management
- Thread safety (if applicable)
- Logging practices

### Step 4: Analyze Documentation

#### 4.1 JavaDoc Coverage

**Count documentation:**
```bash
# Files with JavaDoc
find src/main/java -name "*.java" -exec grep -l "/\*\*" {} \;

# Count JavaDoc blocks
grep -r "/\*\*" src/main/java | grep -c "^\s*/\*\*"
```

**Calculate coverage:**
- Classes with JavaDoc / Total classes
- Methods with JavaDoc / Total methods
- Fields with JavaDoc / Total fields

#### 4.2 JavaDoc Quality

For each documented element, verify:
- Clear description
- @param tags for parameters
- @return tag for return values
- @throws tags for exceptions
- @since, @author, @see tags (if applicable)
- Usage examples (for complex methods)

#### 4.3 Code Comments

**Check:**
- Comments explain "why", not "what"
- No commented-out code
- TODO/FIXME comments are tracked

**Count:**
```bash
grep -rn "TODO\|FIXME\|XXX" src/main/java src/test/java
```

#### 4.4 Project Documentation

**Check for:**
- README.md (comprehensive)
- API documentation
- Architecture documentation
- Design decisions (ADRs)
- Changelog

### Step 5: Analyze Test Quality

#### 5.1 Test Coverage

**Use coverage tools:**
- JaCoCo (Maven/Gradle)
- Cobertura
- EclEmma

**Calculate:**
- Line coverage
- Branch coverage
- Method coverage
- Class coverage

#### 5.2 Test Organization

**Check:**
- Tests mirror source structure
- Test naming conventions (*Test.java, *Tests.java)
- Test methods properly annotated (@Test)
- Test classes properly organized

#### 5.3 Test Quality

**Verify:**
- Tests are independent
- Tests are repeatable
- Tests are fast
- Tests use meaningful assertions
- Tests follow AAA pattern (Arrange-Act-Assert)
- No test code duplication

### Step 6: Calculate Metrics

**Complexity Metrics:**
- Average cyclomatic complexity
- Maximum cyclomatic complexity
- Average lines per method
- Average lines per class

**Maintainability Metrics:**
- Code duplication percentage
- Comment density
- Test coverage percentage

**Quality Score:**
- Code Quality: 0-100
- Documentation: 0-100
- Test Quality: 0-100
- Overall: Weighted average

### Step 7: Document Findings

1. **Create analysis document** using template
2. **Document all issues** with:
   - Location (file:line)
   - Description
   - Impact
   - Recommendation
3. **Prioritize issues** (High/Medium/Low)
4. **Provide examples** (good and bad)

### Step 8: Generate Recommendations

1. **Critical issues** - Must fix
2. **Warnings** - Should fix
3. **Improvements** - Nice to have

## Tools and Commands

### Automated Analysis Tools

**Static Analysis:**
- **PMD**: Code analysis
- **Checkstyle**: Style checking
- **SpotBugs**: Bug detection
- **SonarQube**: Comprehensive analysis

**Coverage:**
- **JaCoCo**: Code coverage
- **Cobertura**: Code coverage

**Documentation:**
- **JavaDoc**: Generate API docs
- **Maven Javadoc Plugin**: Generate docs

### Manual Commands

**File Statistics:**
```bash
# Count files
find src/main/java src/test/java -name "*.java" | wc -l

# Count lines
find src/main/java src/test/java -name "*.java" -exec wc -l {} + | tail -1

# List packages
find src/main/java -name "*.java" -exec grep -h "^package " {} \; | sort -u
```

**JavaDoc Analysis:**
```bash
# Files with JavaDoc
find src/main/java -name "*.java" -exec grep -l "/\*\*" {} \;

# Count JavaDoc blocks
grep -r "/\*\*" src/main/java | grep -c "^\s*/\*\*"

# Extract JavaDoc tags
grep -r "@param\|@return\|@throws" src/main/java
```

**Code Quality:**
```bash
# Find large files
find src/main/java -name "*.java" -exec wc -l {} + | sort -rn | head -10

# Find TODO comments
grep -rn "TODO\|FIXME" src/main/java src/test/java

# Find test files
find src/test/java -name "*Test.java" -o -name "*Tests.java"
```

## Quality Criteria

### Excellent
- Cyclomatic complexity < 10 (average)
- Methods < 50 lines
- Classes < 300 lines
- Code duplication < 3%
- Test coverage > 80%
- JavaDoc coverage > 80%

### Good
- Cyclomatic complexity < 15 (average)
- Methods < 100 lines
- Classes < 500 lines
- Code duplication < 5%
- Test coverage > 60%
- JavaDoc coverage > 60%

### Needs Improvement
- Cyclomatic complexity > 15 (average)
- Methods > 100 lines
- Classes > 500 lines
- Code duplication > 5%
- Test coverage < 60%
- JavaDoc coverage < 60%

## Verification Tables

### Code Quality Table
| Metric | Value | Target | Status |

### Documentation Coverage Table
| Element Type | Documented | Total | Coverage % |

### Test Coverage Table
| Package | Line Coverage | Branch Coverage | Status |

## Best Practices

1. **Be systematic:** Go through each package methodically
2. **Use tools:** Leverage static analysis tools
3. **Document everything:** Even if it's good, document it
4. **Provide examples:** Include good and bad examples
5. **Prioritize:** Focus on critical issues first
6. **Be specific:** Provide file locations and line numbers

## Reusability

To reuse this methodology:

1. **Copy the template** (`SOURCE_QUALITY_ANALYSIS_TEMPLATE.md`)
2. **Use the checklist** (`source_quality_checklist.md`)
3. **Follow this methodology** step by step
4. **Run the script** (`generate_source_quality_analysis.sh`)
5. **Adapt for your project** (add project-specific checks)

## Example Workflow

```bash
# 1. Run analysis script
./ai/generate_source_quality_analysis.sh

# 2. Review extracted data
cat source_quality_data/summary.txt

# 3. Use template to create analysis
cp ai/SOURCE_QUALITY_ANALYSIS_TEMPLATE.md SOURCE_QUALITY_ANALYSIS.md

# 4. Fill in template with findings
# 5. Follow checklist
# 6. Generate final document
```

## Version History

- v1.0 - Initial methodology based on source quality analysis best practices

