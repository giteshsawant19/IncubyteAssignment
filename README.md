# String Calculator - TDD Implementation

A Test-Driven Development (TDD) implementation of a String Calculator that can handle various input formats and delimiter configurations.

## Features

- **Basic Operations**: Add numbers separated by commas
- **Empty String Support**: Returns 0 for empty input
- **Unknown Amount of Numbers**: Handles any number of comma-separated values
- **New Line Delimiters**: Supports new lines as delimiters (`\n`)
- **Custom Delimiters**: Single character custom delimiters (`//;\n1;2`)
- **Any Length Delimiters**: Delimiters of any length (`//[***]\n1***2***3`)
- **Multiple Delimiters**: Multiple delimiters of any length (`//[*][%]\n1*2%3`)
- **Negative Number Validation**: Throws exception with all negative numbers listed
- **Large Number Filtering**: Ignores numbers greater than 1000
- **Special Character Support**: Handles regex special characters in delimiters

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher


## Testing

### Run All Tests
```bash
mvn test
```

### Run Tests with Detailed Output
```bash
mvn test -Dtest=CalculatorTest
```

### Run Specific Test Methods
```bash
# Run only basic tests
mvn test -Dtest=CalculatorTest#shouldReturnZeroOnEmptyString

# Run only negative number tests
mvn test -Dtest=CalculatorTest#shouldNotAllowAddWithSingleNegativeNumber
```
## Dependencies

- **JUnit 5**: Testing framework
- **Maven Surefire Plugin**: Test execution
- **Maven Compiler Plugin**: Java compilation

## Build Information

- **Java Version**: 21
- **Maven Version**: 3.x
- **JUnit Version**: 5.9.2
- **Encoding**: UTF-8

