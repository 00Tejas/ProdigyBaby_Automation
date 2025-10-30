# ProdigyBaby - Mobile Test Automation Framework

## 📋 Project Overview

This is a comprehensive mobile test automation framework for the Prodigy Baby app, built with Java, Appium, and TestNG. The framework follows clean architecture principles with a clear separation of concerns.

## 🏗️ Project Structure

```
ProdigyBaby/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/prodigy/automation/
│   │   │   │   ├── base/
│   │   │   │   │   ├── BaseTest.java        # Base test class with setup/teardown
│   │   │   │   │   └── DriverFactory.java   # Appium driver factory
│   │   │   │   ├── pages/                  # Page Object Models
│   │   │   │   │   ├── BasePage.java
│   │   │   │   │   ├── HomePage.java
│   │   │   │   │   ├── LoginPage*.java
│   │   │   │   │   └── ...
│   │   │   │   ├── flows/                  # Business logic flows
│   │   │   │   │   ├── LoginFlow.java
│   │   │   │   │   ├── HomeFlow.java
│   │   │   │   │   └── ...
│   │   │   │   ├── validators/             # Validation logic
│   │   │   │   │   ├── LoginValidator.java
│   │   │   │   │   └── ...
│   │   │   │   ├── utils/                  # Utility classes
│   │   │   │   │   ├── TestLogger.java
│   │   │   │   │   └── TestReport.java
│   │   │   │   ├── data/                   # Test data
│   │   │   │   │   └── TestUsers.java
│   │   │   │   └── enums/                  # Enumerations
│   │   │   │       └── UserType.java
│   │   └── resources/
│   │       ├── config.properties
│   │       └── logback.xml
│   └── test/
│       ├── java/
│       │   ├── com/prodigy/automation/
│       │   │   ├── tests/
│       │   │   │   ├── login/
│       │   │   │   │   └── LoginTest.java
│       │   │   │   └── tabs/
│       │   │   │       ├── HomeTest.java
│       │   │   │       ├── ProgramTabTest.java
│       │   │   │       ├── CommunityTabTest.java
│       │   │   │       └── LogoutTest.java
│       └── resources/
│           └── testng.xml
└── pom.xml
```

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- Android SDK
- Appium Server
- Android Emulator or Device

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd ProdigyBaby
```

2. Install dependencies:
```bash
mvn clean install
```

3. Start Appium server:
```bash
appium
```

4. Start Android emulator or connect device

### Running Tests

Run all tests:
```bash
mvn test
```

Run specific test suite:
```bash
mvn test -Dsuite=tests/LoginTest
```

Run with TestNG groups:
```bash
mvn test -Dgroups=smoke
```

## 📝 Test Organization

### Test Categories

- **Login Tests**: Authentication tests for different user types
- **Tab Tests**: UI validation for Home, Program, and Community tabs
- **Logout Tests**: Logout flow validation

### User Types

The framework supports multiple user types:
- NEW_USER
- PROGRAM_USER
- SUBSCRIPTION_USER
- LAUNCHPAD_USER
- PROGRAM_SUBSCRIPTION_USER

## 🎯 Features

- **Page Object Model**: Clean page object implementation
- **Flow Pattern**: Business logic separated from page actions
- **Validator Pattern**: Assertions separated from test logic
- **Data-Driven**: Centralized test data management
- **Comprehensive Reporting**: CSV report generation
- **Logging**: Detailed test execution logs

## 📊 Reports

Test reports are generated in CSV format after test execution. Look for `TestReport_<timestamp>.csv` in the project root.

## 🔧 Configuration

Edit `src/main/resources/config.properties` to customize:
- App package and activity
- Device information
- Appium server URL

## 📞 Support

For issues or questions, please contact the automation team.

