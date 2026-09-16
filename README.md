# Demo API Automation Framework

A scalable and maintainable **API Test Automation Framework** built using **Java, REST Assured, TestNG, Maven, and GitHub Actions**.

The framework follows the **Service Object Model** pattern and provides reusable components for API requests, authentication, request/response serialization, logging, reporting, and CI/CD execution.

---

## 🚀 Key Features

- REST API automation using **REST Assured**
- **Java 11+** compatible
- Test execution using **TestNG**
- Service Object Model architecture
- Reusable `BaseService` for common HTTP operations
- Authentication service implementation
- Request and response POJO models
- JSON serialization/deserialization using **Jackson**
- Request/response logging using REST Assured filters
- Custom TestNG listeners
- **Extent Reports** integration
- **Log4j2** logging
- Maven-based test execution
- GitHub Actions CI/CD integration
- Supports execution through TestNG Suite XML
- Designed for maintainability and scalability

---

## 🏗️ Framework Architecture

```text
DemoAPIAutomationFramework
│
├── .github
│   └── workflows
│       └── maven.yml
│
├── logs
│
├── src
│   ├── main
│   │   └── java
│   │       └── org.example
│   │
│   └── test
│       ├── java
│       │   └── com.api
│       │       │
│       │       ├── base
│       │       │   ├── BaseService.java
│       │       │   ├── AuthService.java
│       │       │   └── UserProfileManagementService.java
│       │       │
│       │       ├── filters
│       │       │   └── LoggingFilter.java
│       │       │
│       │       ├── listeners
│       │       │   └── TestListener.java
│       │       │
│       │       ├── models
│       │       │   ├── requests
│       │       │   │   ├── LoginRequest.java
│       │       │   │   ├── SignupRequest.java
│       │       │   │   └── ProfileRequest.java
│       │       │   │
│       │       │   └── response
│       │       │       ├── LoginResponse.java
│       │       │       └── UserProfileResponse.java
│       │       │
│       │       ├── tests
│       │       │   └── LoginApiTest.java
│       │       │
│       │       └── utility
│
├── suite.xml
├── pom.xml
└── README.md

## 🔄 Request Flow

The framework follows a layered architecture:

Test Class
    │
    ▼
Service Class
    │
    ▼
BaseService
    │
    ▼
REST Assured
    │
    ▼
API Endpoint
    │
    ▼
Response
    │
    ▼
Response POJO
    │
    ▼
Assertions
Example
LoginApiTest
      ↓
AuthService
      ↓
BaseService
      ↓
POST /api/auth/login
      ↓
LoginResponse
      ↓
TestNG Assertions

## 🧩 Service Object Model

API operations are encapsulated inside service classes instead of directly writing REST Assured requests in every test.

Example:

public class AuthService extends BaseService {

    private static final String BASE_PATH = "/api/auth/";

    public Response login(LoginRequest body) {
        return postRequest(body, BASE_PATH + "login");
    }

    public Response signUp(SignupRequest body) {
        return postRequest(body, BASE_PATH + "signup");
    }
}

Tests can therefore focus on business validation rather than request construction.

## 📝 Request Models

The framework uses POJO classes to represent API request payloads.

Example:

LoginRequest loginRequest =
        new LoginRequest("uday1234", "uday1234");

This approach is cleaner and more maintainable than hard-coding JSON strings inside tests.

## 📦 Response Deserialization

API responses are converted into Java objects using Jackson.

Example:

LoginResponse loginResponse =
        response.as(LoginResponse.class);

The test can then access individual response attributes:

loginResponse.getToken();
loginResponse.getUsername();
loginResponse.getEmail();
loginResponse.getRoles();


## 🔐 Authentication

Authentication-related operations are centralized in AuthService.

Current examples include:

Login
Signup
Forgot Password

The framework also provides reusable authorization token handling through BaseService.

protected void setAuthToken(String token) {
    requestSpecification.header(
            "Authorization",
            "Bearer " + token
    );
}


## 🧪 TestNG

TestNG is used as the test execution framework.

Example:

@Test(description = "Verify if login API is working")
public void loginTest() {

    LoginRequest loginRequest =
            new LoginRequest("uday1234", "uday1234");

    AuthService authService = new AuthService();

    Response response =
            authService.login(loginRequest);

    LoginResponse loginResponse =
            response.as(LoginResponse.class);

    Assert.assertEquals(
            response.getStatusCode(),
            200
    );
}


## 📊 Reporting

The framework integrates Extent Reports for test execution reporting.

Reports can provide information such as:

Test execution status
Passed tests
Failed tests
Test descriptions
Failure details


## 📝 Logging

The framework uses Log4j2 for application/test logging.

REST Assured request and response logging is handled through a custom filter:

LoggingFilter

This helps with debugging API failures by capturing request and response information.

## 🔧 Maven

The project uses Maven for dependency management and test execution.

Run the complete suite
mvn clean test -Dsuite=suite
Run Maven tests
mvn clean test


## 📋 TestNG Suite

The framework supports TestNG Suite XML execution.

Example:

suite.xml

The Maven Surefire Plugin can execute the configured TestNG suite using:

mvn clean test -Dsuite=suite


## ⚙️ Prerequisites

Before running the framework, install:

Java 11 or higher
Maven 3.9+
Git
IntelliJ IDEA or another Java IDE

Verify Java:

java -version

Verify Maven:

mvn -version


## 🔗 API

The framework was originally developed against the demo authentication API used by the accompanying E2E API Automation tutorial.

Swagger documentation:

http://64.227.160.186:8080/swagger-ui/index.html

Note: The demo API is externally hosted and may not always be available. If the server is unavailable, API tests depending on that environment will fail with a connection error.

## 🔄 CI/CD

GitHub Actions is configured through:

.github/workflows/maven.yml

The framework can be integrated into a CI pipeline to automatically execute API tests whenever changes are pushed to the repository.

Typical CI flow:

Git Push
   ↓
GitHub Actions
   ↓
Checkout Code
   ↓
Setup Java
   ↓
Maven Build
   ↓
Execute TestNG Tests
   ↓
Generate Reports


## 🛠️ Technology Stack

Technology	Purpose
Java	Programming Language
REST Assured	API Automation
TestNG	Test Execution
Maven	Build & Dependency Management
Jackson	JSON Serialization / Deserialization
Extent Reports	Test Reporting
Log4j2	Logging
Git	Version Control
GitHub Actions	CI/CD



## 🎯 API Testing Coverage

The framework is designed to support:

Functional API testing
Authentication testing
Positive testing
Negative testing
Request validation
Response validation
Status code validation
JSON schema/data validation
API regression testing
Integration testing



## 📈 Future Enhancements

Planned improvements include:

Environment-based configuration
Externalized API base URLs
Improved request specification management
Centralized response validation
Advanced authentication/token management
Data-driven testing
Parameterized API tests
JSON Schema validation
Contract testing
Retry mechanism for transient failures
Parallel execution
Enhanced CI/CD reporting
Allure reporting
Docker-based execution


## 👨‍💻 Author

Ankit Sharma

Senior QA / SDET | API Automation | UI Automation | AI & Agent Testing

GitHub:

https://github.com/ankit19apr

## ⭐ Purpose

This project demonstrates practical implementation of a maintainable API automation framework using industry-standard testing practices and modern CI/CD capabilities.

The framework is intended for learning, demonstration, portfolio development, and showcasing API automation engineering skills.
