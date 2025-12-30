# Source Quality and Documentation Analysis Template

This template can be reused for analyzing Java source code quality and documentation in any project.

## Quick Start Checklist

- [ ] Gather source code statistics
- [ ] Analyze code structure and organization
- [ ] Evaluate code quality metrics
- [ ] Review documentation coverage
- [ ] Check coding standards compliance
- [ ] Assess test coverage and quality
- [ ] Document findings and recommendations

---

## Analysis Document Structure

```markdown
# Source Quality and Documentation Analysis - [Project Name]

## Executive Summary
- Overall assessment
- Key metrics
- Critical findings
- Recommendations summary

## Project Overview
- Project structure
- Source code statistics
- Technology stack

## Code Quality Analysis
- Structure and organization
- Naming conventions
- Code complexity
- Design patterns
- Best practices compliance

## Documentation Analysis
- JavaDoc coverage
- Code comments
- README and project documentation
- API documentation

## Test Quality Analysis
- Test coverage
- Test organization
- Test quality metrics

## Issues and Recommendations
- Critical issues
- Warnings
- Improvement suggestions

## Overall Assessment
- Quality score
- Strengths
- Areas for improvement
```

---

## 1. Executive Summary

### Overall Assessment
- **Code Quality**: [EXCELLENT/GOOD/FAIR/NEEDS_IMPROVEMENT]
- **Documentation**: [EXCELLENT/GOOD/FAIR/NEEDS_IMPROVEMENT]
- **Test Coverage**: [EXCELLENT/GOOD/FAIR/NEEDS_IMPROVEMENT]

### Key Metrics
- Total Java files: [count]
- Lines of code: [count]
- Test files: [count]
- Documentation coverage: [percentage]

### Critical Findings
- [List top 3-5 critical issues]

### Recommendations Summary
- [List top 3-5 recommendations]

---

## 2. Project Overview

### Project Structure
```
[Show directory structure]
src/main/java/
src/test/java/
```

### Source Code Statistics

| Metric | Main | Test | Total |
|--------|------|------|-------|
| Java files | [count] | [count] | [count] |
| Lines of code | [count] | [count] | [count] |
| Classes | [count] | [count] | [count] |
| Interfaces | [count] | [count] | [count] |
| Enums | [count] | [count] | [count] |
| Methods | [count] | [count] | [count] |
| Average methods per class | [avg] | [avg] | [avg] |
| Average lines per class | [avg] | [avg] | [avg] |

### Technology Stack
- Java version: [version]
- Build tool: [Maven/Gradle]
- Testing framework: [JUnit/TestNG/etc]
- Dependencies: [key dependencies]

---

## 3. Code Quality Analysis

### 3.1 Structure and Organization

#### Package Organization
- ✅ **GOOD**: Packages follow logical grouping
- ⚠️ **ISSUE**: [Describe any issues]

#### Class Organization
- ✅ **GOOD**: Classes are well-organized
- ⚠️ **ISSUE**: [Describe any issues]

#### File Naming
- ✅ **GOOD**: Files follow Java naming conventions
- ⚠️ **ISSUE**: [Describe any issues]

### 3.2 Naming Conventions

| Convention | Compliance | Examples | Issues |
|------------|------------|----------|--------|
| Class names (PascalCase) | ✅/⚠️ | [examples] | [issues] |
| Method names (camelCase) | ✅/⚠️ | [examples] | [issues] |
| Variable names (camelCase) | ✅/⚠️ | [examples] | [issues] |
| Constants (UPPER_SNAKE_CASE) | ✅/⚠️ | [examples] | [issues] |
| Package names (lowercase) | ✅/⚠️ | [examples] | [issues] |

### 3.3 Code Complexity

#### Cyclomatic Complexity
- Average complexity: [number]
- High complexity methods (>10): [count]
- Very high complexity methods (>20): [count]

**Methods with High Complexity:**
| Method | Complexity | Location | Recommendation |
|--------|------------|----------|---------------|
| [method] | [number] | [file:line] | [suggestion] |

#### Class Size
- Average lines per class: [number]
- Large classes (>500 lines): [count]
- Very large classes (>1000 lines): [count]

**Large Classes:**
| Class | Lines | Methods | Recommendation |
|-------|-------|---------|----------------|
| [class] | [number] | [number] | [suggestion] |

### 3.4 Design Patterns

#### Patterns Identified
- [ ] Singleton
- [ ] Factory
- [ ] Builder
- [ ] Strategy
- [ ] Observer
- [ ] Other: [list]

#### Pattern Usage Assessment
- ✅ **GOOD**: Appropriate use of design patterns
- ⚠️ **ISSUE**: [Describe any issues]

### 3.5 Best Practices Compliance

#### SOLID Principles
- **Single Responsibility**: ✅/⚠️ [Assessment]
- **Open/Closed**: ✅/⚠️ [Assessment]
- **Liskov Substitution**: ✅/⚠️ [Assessment]
- **Interface Segregation**: ✅/⚠️ [Assessment]
- **Dependency Inversion**: ✅/⚠️ [Assessment]

#### Code Smells
- [ ] Long methods
- [ ] Long parameter lists
- [ ] Duplicate code
- [ ] Dead code
- [ ] Magic numbers
- [ ] God classes
- [ ] Feature envy
- [ ] Data clumps

### 3.6 Error Handling

#### Exception Handling
- ✅ **GOOD**: Proper exception handling
- ⚠️ **ISSUE**: [Describe any issues]

**Issues Found:**
- [List specific issues]

#### Logging
- ✅ **GOOD**: Appropriate logging
- ⚠️ **ISSUE**: [Describe any issues]

---

## 4. Documentation Analysis

### 4.1 JavaDoc Coverage

#### Overall Coverage
- Classes with JavaDoc: [count] / [total] ([percentage]%)
- Methods with JavaDoc: [count] / [total] ([percentage]%)
- Fields with JavaDoc: [count] / [total] ([percentage]%)

#### Coverage by Package

| Package | Classes | Methods | Fields | Overall |
|---------|---------|---------|--------|---------|
| [package] | [%] | [%] | [%] | [%] |

#### JavaDoc Quality Assessment

**Good Examples:**
- [List well-documented classes/methods]

**Needs Improvement:**
- [List classes/methods needing better documentation]

### 4.2 JavaDoc Quality Criteria

For each documented element, check:
- [ ] Clear description of purpose
- [ ] Parameter documentation (@param)
- [ ] Return value documentation (@return)
- [ ] Exception documentation (@throws)
- [ ] Usage examples (if complex)
- [ ] @since tags (if applicable)
- [ ] @author tags (if applicable)
- [ ] @see references (if applicable)

### 4.3 Code Comments

#### Inline Comments
- ✅ **GOOD**: Comments explain "why", not "what"
- ⚠️ **ISSUE**: [Describe any issues]

**Issues Found:**
- [List specific issues]

#### TODO/FIXME Comments
- TODO comments: [count]
- FIXME comments: [count]
- XXX comments: [count]

**Notable TODOs:**
| Comment | Location | Priority |
|---------|----------|----------|
| [text] | [file:line] | [High/Medium/Low] |

### 4.4 Project Documentation

#### README.md
- ✅ **EXISTS**: README.md present
- ✅ **COMPREHENSIVE**: Contains necessary information
- ⚠️ **ISSUE**: [Describe any issues]

**Sections Checked:**
- [ ] Project description
- [ ] Installation instructions
- [ ] Usage examples
- [ ] Build instructions
- [ ] Contributing guidelines
- [ ] License information

#### Other Documentation
- [ ] API documentation
- [ ] Architecture documentation
- [ ] Design decisions (ADRs)
- [ ] Changelog

---

## 5. Test Quality Analysis

### 5.1 Test Coverage

#### Overall Coverage
- Line coverage: [percentage]%
- Branch coverage: [percentage]%
- Method coverage: [percentage]%
- Class coverage: [percentage]%

#### Coverage by Package

| Package | Line Coverage | Branch Coverage | Status |
|---------|---------------|-----------------|--------|
| [package] | [%] | [%] | ✅/⚠️ |

### 5.2 Test Organization

#### Test Structure
- ✅ **GOOD**: Tests mirror source structure
- ⚠️ **ISSUE**: [Describe any issues]

#### Test Naming
- ✅ **GOOD**: Tests follow naming conventions
- ⚠️ **ISSUE**: [Describe any issues]

**Convention Check:**
- [ ] Test classes: `*Test.java` or `*Tests.java`
- [ ] Test methods: descriptive names
- [ ] Test methods: `@Test` annotation

### 5.3 Test Quality

#### Test Types
- Unit tests: [count]
- Integration tests: [count]
- Test utilities: [count]

#### Test Quality Metrics
- Average assertions per test: [number]
- Tests with no assertions: [count]
- Tests with only one assertion: [count]

#### Test Best Practices
- [ ] Tests are independent
- [ ] Tests are repeatable
- [ ] Tests are fast
- [ ] Tests use meaningful assertions
- [ ] Tests have clear names
- [ ] Tests follow AAA pattern (Arrange-Act-Assert)

**Issues Found:**
- [List specific issues]

---

## 6. Code Metrics Summary

### Complexity Metrics

| Metric | Value | Target | Status |
|--------|-------|--------|--------|
| Average cyclomatic complexity | [number] | < 10 | ✅/⚠️ |
| Maximum cyclomatic complexity | [number] | < 20 | ✅/⚠️ |
| Average lines per method | [number] | < 50 | ✅/⚠️ |
| Average lines per class | [number] | < 300 | ✅/⚠️ |

### Maintainability Metrics

| Metric | Value | Target | Status |
|--------|-------|--------|--------|
| Code duplication | [percentage]% | < 3% | ✅/⚠️ |
| Comment density | [percentage]% | > 20% | ✅/⚠️ |
| Test coverage | [percentage]% | > 80% | ✅/⚠️ |

---

## 7. Issues and Recommendations

### Critical Issues

1. **[Issue Title]**
   - **Location**: [file:line]
   - **Description**: [detailed description]
   - **Impact**: [impact assessment]
   - **Recommendation**: [how to fix]

### Warnings

1. **[Warning Title]**
   - **Location**: [file:line]
   - **Description**: [description]
   - **Recommendation**: [suggestion]

### Improvement Suggestions

1. **[Suggestion Title]**
   - **Description**: [description]
   - **Benefit**: [expected benefit]
   - **Effort**: [Low/Medium/High]

---

## 8. Overall Assessment

### Quality Score

| Category | Score | Weight | Weighted Score |
|----------|-------|--------|---------------|
| Code Quality | [0-100] | 40% | [score] |
| Documentation | [0-100] | 30% | [score] |
| Test Quality | [0-100] | 30% | [score] |
| **Overall** | **[0-100]** | **100%** | **[score]** |

### Strengths

- [List key strengths]

### Areas for Improvement

- [List areas needing work]

### Priority Recommendations

1. **[High Priority]**: [recommendation]
2. **[Medium Priority]**: [recommendation]
3. **[Low Priority]**: [recommendation]

---

## Appendix: Detailed Metrics

### A.1 File-by-File Analysis

[Optional: Detailed analysis of specific files]

### A.2 Code Smell Details

[Optional: Detailed code smell analysis]

### A.3 Documentation Examples

[Optional: Examples of good and bad documentation]

---

## Template Variables

Replace these in the template:
- `[Project Name]` - Name of your project
- `[count]` - Actual counts
- `[percentage]` - Actual percentages
- `[number]` - Actual numbers
- `[file:line]` - Actual file locations
- `[examples]` - Actual examples
- `[issues]` - Actual issues found

---

## Tips for Reusability

1. **Use automated tools**: Leverage static analysis tools (Checkstyle, PMD, SonarQube)
2. **Gather metrics first**: Run analysis scripts before manual review
3. **Focus on patterns**: Look for recurring issues
4. **Prioritize**: Focus on critical issues first
5. **Document examples**: Include good and bad examples
6. **Be specific**: Provide file locations and line numbers

---

## Version History

- v1.0 - Initial template based on source quality analysis best practices

