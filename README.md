# Demoblaze Automation Project

Web UI automation testing project for the [Demoblaze](https://www.demoblaze.com/) e-commerce application using Selenium WebDriver, Java, TestNG, and Maven.

## 🛠️ Technologies

- Java
- Selenium WebDriver
- TestNG
- Maven
- ExtentReports

## 📂 Project Structure

```text
src/test/java
├── Pages
│   ├── AboutPage
│   ├── CartPage
│   ├── CheckoutPage
│   ├── ContactPage
│   ├── HomePage
│   ├── LoginPage
│   ├── ProductPage
│   └── SignUpPage
│
├── Tests
│   ├── AboutTest
│   ├── CartTest
│   ├── CheckoutTest
│   ├── ContactTest
│   ├── LoginTest
│   ├── ProductTest
│   └── SignUpTest
│
└── Utilities
    ├── AnnotationTransformer
    ├── DataGenerator
    ├── ExtentManager
    ├── JSONReader
    ├── RetryAnalyzer
    ├── SessionData
    ├── TestBase
    └── TestListener

## 🧪 Test Coverage

The automation suite covers multiple Demoblaze functionalities:

- User Sign Up
- User Login
- Input Validation
- Product Functionality
- Shopping Cart
- Checkout
- Contact Functionality
- About Functionality

## ⚙️ Framework Features

- Page Object Model (POM)
- TestNG-based test execution
- Test priorities and descriptions
- Retry mechanism for failed tests
- Test listeners
- Soft Assertions
- Failure screenshots
- Test data generation
- JSON data handling
- Session data management
- ExtentReports integration

## 📊 Test Reporting

The project uses ExtentReports to generate detailed HTML automation test reports.

The report includes:

- Overall test execution summary
- Passed, failed, and skipped tests
- Individual test details
- Test execution timestamps
- Test duration
- Test descriptions
- Execution timeline
- Author statistics
- Test environment information
- Dashboard with execution statistics

### Sample Execution

A sample test execution included:

| Result | Count |
| :--- | :--- |
| Total Tests | 19 |
| Passed | 15 |
| Failed | 1 |
| Skipped | 3 |

**Test Environment**

- **Environment:** QA
- **Browser:** Chrome
- **Operating System:** Windows 11

## 📸 Failure Screenshots

The framework captures screenshots for failed test scenarios to support failure investigation and debugging.

Screenshots are stored in the `Screenshots` directory.

## ▶️ How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Install Maven dependencies.
4. Run the TestNG suite using `RUN.xml`.
5. Open the generated ExtentReport to review the test execution results.

## 📂 Reports

The project includes generated test reports under the `reports` directory, including the ExtentReports HTML report.

## 👩‍💻 Author

**Shorooq Elawi**

QA Engineer | Manual & Automation Testing
