# Source Quality and Documentation Analysis Checklist

Use this checklist when analyzing Java source code quality and documentation.

## Pre-Analysis Setup

- [ ] Identify source directories (main and test)
- [ ] Set up analysis tools (if using automated tools)
- [ ] Create analysis output directory
- [ ] Gather project metadata (Java version, build tool, etc.)

## Code Statistics Gathering

- [ ] Count total Java files (main and test)
- [ ] Count lines of code
- [ ] Count classes, interfaces, enums
- [ ] Count methods
- [ ] Calculate averages (methods per class, lines per class)
- [ ] Identify package structure

## Code Quality Checks

### Structure and Organization
- [ ] Package organization follows logical grouping
- [ ] Classes are well-organized
- [ ] File naming follows Java conventions
- [ ] No circular dependencies

### Naming Conventions
- [ ] Class names use PascalCase
- [ ] Method names use camelCase
- [ ] Variable names use camelCase
- [ ] Constants use UPPER_SNAKE_CASE
- [ ] Package names use lowercase
- [ ] No abbreviations unless standard

### Code Complexity
- [ ] Calculate cyclomatic complexity
- [ ] Identify high complexity methods (>10)
- [ ] Identify very high complexity methods (>20)
- [ ] Check average complexity
- [ ] Identify large classes (>500 lines)
- [ ] Identify very large classes (>1000 lines)

### Design Patterns
- [ ] Identify design patterns used
- [ ] Assess pattern appropriateness
- [ ] Check for pattern misuse

### Best Practices
- [ ] Check SOLID principles compliance
- [ ] Identify code smells
- [ ] Check error handling
- [ ] Check logging practices
- [ ] Check resource management
- [ ] Check thread safety (if applicable)

### Code Smells
- [ ] Long methods
- [ ] Long parameter lists
- [ ] Duplicate code
- [ ] Dead code
- [ ] Magic numbers
- [ ] God classes
- [ ] Feature envy
- [ ] Data clumps
- [ ] Primitive obsession
- [ ] Large classes

## Documentation Checks

### JavaDoc Coverage
- [ ] Count classes with JavaDoc
- [ ] Count methods with JavaDoc
- [ ] Count fields with JavaDoc
- [ ] Calculate coverage percentages
- [ ] Check coverage by package

### JavaDoc Quality
For each documented element:
- [ ] Clear description of purpose
- [ ] Parameter documentation (@param)
- [ ] Return value documentation (@return)
- [ ] Exception documentation (@throws)
- [ ] Usage examples (if complex)
- [ ] @since tags (if applicable)
- [ ] @author tags (if applicable)
- [ ] @see references (if applicable)
- [ ] @deprecated tags (if applicable)

### Code Comments
- [ ] Check inline comments quality
- [ ] Comments explain "why", not "what"
- [ ] No commented-out code
- [ ] Count TODO comments
- [ ] Count FIXME comments
- [ ] Count XXX comments
- [ ] Review TODO/FIXME priorities

### Project Documentation
- [ ] README.md exists and is comprehensive
- [ ] API documentation exists
- [ ] Architecture documentation exists
- [ ] Design decisions documented (ADRs)
- [ ] Changelog exists

## Test Quality Checks

### Test Coverage
- [ ] Calculate line coverage
- [ ] Calculate branch coverage
- [ ] Calculate method coverage
- [ ] Calculate class coverage
- [ ] Check coverage by package

### Test Organization
- [ ] Tests mirror source structure
- [ ] Test naming follows conventions
- [ ] Test classes properly annotated
- [ ] Test methods properly annotated

### Test Quality
- [ ] Tests are independent
- [ ] Tests are repeatable
- [ ] Tests are fast
- [ ] Tests use meaningful assertions
- [ ] Tests have clear names
- [ ] Tests follow AAA pattern
- [ ] No test code duplication
- [ ] Test utilities are well-organized

## Metrics Calculation

- [ ] Calculate average cyclomatic complexity
- [ ] Calculate maximum cyclomatic complexity
- [ ] Calculate average lines per method
- [ ] Calculate average lines per class
- [ ] Calculate code duplication percentage
- [ ] Calculate comment density
- [ ] Calculate test coverage percentage

## Issue Documentation

- [ ] Document all critical issues
- [ ] Document all warnings
- [ ] Document improvement suggestions
- [ ] Prioritize issues (High/Medium/Low)
- [ ] Provide specific file locations
- [ ] Provide recommendations for each issue

## Final Review

- [ ] All sections completed
- [ ] All metrics calculated
- [ ] All issues documented
- [ ] Recommendations provided
- [ ] Overall assessment written
- [ ] Quality score calculated
- [ ] Document reviewed

---

## Quick Reference: Quality Criteria

### Excellent Code Quality
- Cyclomatic complexity < 10 (average)
- Methods < 50 lines
- Classes < 300 lines
- Code duplication < 3%
- Test coverage > 80%
- JavaDoc coverage > 80%

### Good Code Quality
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

---

## Notes

- Keep this checklist updated with project-specific requirements
- Add custom checks for your domain
- Document any project-specific patterns

