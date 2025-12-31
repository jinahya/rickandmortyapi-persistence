#!/bin/sh

# find main classes which has no equals method
echo "--- classes with no equals method ---"
find src/main/java -name "*.java" ! -name "package-info.java" -exec grep -L "boolean equals(" {} +

# find main classes which has no hashCode method
echo "--- classes with no hashCode method ---"
find src/main/java -name "*.java" ! -name "package-info.java" -exec grep -L "int hashCode(" {} +

# find overridden equals, hashCode method is not final
echo "--- overridden equals/hashCode method which is not final ---"
find src/main/java -name "*.java" ! -name "package-info.java" -exec awk '
    /@Override/ {
        found_override = 1
        next
    }
    found_override {
        if ($0 ~ /boolean equals\(|int hashCode\(/) {
            if ($0 !~ /final/) {
                print FILENAME ": " $0
            }
        }
        found_override = 0
    }
' {} +
