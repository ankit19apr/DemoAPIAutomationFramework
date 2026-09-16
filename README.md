# Demo API Automation Framework

A professional API automation testing framework built using **Java, REST Assured, TestNG, Maven, Extent Reports, Log4j2, and GitHub Actions**.

The framework follows a maintainable **Service Object Model** approach and provides reusable components for API request handling, authentication, request/response models, logging, reporting, and CI/CD execution.

## 🚀 Tech Stack

| Technology | Purpose |
|---|---|
| Java 11+ | Programming language |
| REST Assured | API automation |
| TestNG | Test execution and assertions |
| Maven | Build and dependency management |
| Jackson | JSON serialization/deserialization |
| Extent Reports | Test reporting |
| Log4j2 | Framework logging |
| GitHub Actions | CI/CD |
| IntelliJ IDEA | Development IDE |

## 📌 Framework Features

- REST API automation using REST Assured
- Service Object Model architecture
- Reusable API request methods
- Centralized Base Service
- Authentication service abstraction
- POJO-based request models
- POJO-based response models
- JSON serialization/deserialization
- Request and response logging
- Custom REST Assured logging filter
- TestNG listeners
- Extent HTML reporting
- Log4j2 logging
- Maven-based test execution
- TestNG suite execution
- GitHub Actions CI/CD integration
- Separation of test logic and API implementation

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
│   │           └── Main.java
│   │
│   └── test
│       ├── java
│       │   └── com.api
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
│       │       └── utility
│       │
│       └── resources
│           └── log4j2.xml
│
├── .gitignore
├── pom.xml
└── suite.xml
```

## 🔧 Design Pattern

The framework uses a **Service Object Model**.

Instead of writing REST Assured request implementation directly inside every test, API operations are encapsulated inside service classes.

Example:

```java
AuthService authService = new AuthService();
Response response = authService.login(loginRequest);
```

The test focuses on **what needs to be tested**, while the service class handles **how the API request is created and executed**.

## 🔹 BaseService

`BaseService` is the core abstraction layer of the framework.

It is responsible for:

- Base URL configuration
- Creating REST Assured request specifications
- GET requests
- POST requests
- PUT requests
- Authentication headers
- Common request configuration
- Logging filters

Example:

```java
public class BaseService {

    private static final String BASE_URL =
            "http://64.227.160.186:8080";

    private RequestSpecification requestSpecification;

    public BaseService() {
        requestSpecification =
                RestAssured.given().baseUri(BASE_URL);
    }

    protected Response getRequest(String endPoint) {
        return requestSpecification.get(endPoint);
    }

    protected Response postRequest(Object body, String endPoint) {
        return requestSpecification
                .contentType(ContentType.JSON)
                .body(body)
                .post(endPoint);
    }

    protected Response putRequest(Object body, String endPoint) {
        return requestSpecification
                .contentType(ContentType.JSON)
                .body(body)
                .put(endPoint);
    }

    protected void setAuthToken(String token) {
        requestSpecification.header(
                "Authorization",
                "Bearer " + token
        );
    }
}
```

## 🔐 Authentication Service

`AuthService` contains authentication-related API operations.

Example:

```java
public class AuthService extends BaseService {

    private static final String BASE_PATH = "/api/auth/";

    public Response login(LoginRequest body) {
        return postRequest(body, BASE_PATH + "login");
    }

    public Response signUp(SignupRequest body) {
        return postRequest(body, BASE_PATH + "signup");
    }

    public Response forgotPassword(String emailAddress) {

        HashMap<String, String> body =
                new HashMap<>();

        body.put("email", emailAddress);

        return postRequest(
                body,
                BASE_PATH + "forgot-password"
        );
    }
}
```

## 📦 Request Models

The framework uses POJO classes to represent API request payloads.

Example:

```java
LoginRequest loginRequest =
        new LoginRequest(
                "username",
                "password"
        );
```

This avoids maintaining large JSON strings directly inside test cases.

Instead of:

```java
.body(
    "{\"username\":\"user\",\"password\":\"password\"}"
)
```

the framework can use:

```java
.body(loginRequest)
```

REST Assured and Jackson handle serialization.

## 📥 Response Models

API responses are deserialized into Java objects.

Example:

```java
LoginResponse loginResponse =
        response.as(LoginResponse.class);
```

Response fields can then be accessed through Java methods:

```java
loginResponse.getToken();
loginResponse.getEmail();
loginResponse.getId();
loginResponse.getType();
loginResponse.getUsername();
loginResponse.getRoles();
```

This provides structured and type-safe access to API responses.

## 🧪 Example API Test

Example login test:

```java
@Test(description = "Verify if login API is working")
public void loginTest() {

    LoginRequest loginRequest =
            new LoginRequest(
                    "uday1234",
                    "uday1234"
            );

    AuthService authService =
            new AuthService();

    Response response =
            authService.login(loginRequest);

    LoginResponse loginResponse =
            response.as(LoginResponse.class);

    System.out.println(
            response.asPrettyString()
    );

    System.out.println(
            loginResponse.getToken()
    );

    Assert.assertEquals(
            response.getStatusCode(),
            200
    );
}
```

## 🔄 Request Flow

```text
Test Class
    |
    v
Service Class
    |
    v
BaseService
    |
    v
REST Assured
    |
    v
API Endpoint
    |
    v
Response
    |
    v
Response POJO
    |
    v
Assertions
```

For example:

```text
LoginApiTest
     ↓
AuthService.login()
     ↓
BaseService.postRequest()
     ↓
REST Assured
     ↓
/api/auth/login
     ↓
Response
     ↓
LoginResponse
     ↓
TestNG Assertions
```

## 📝 Logging

The framework includes a custom REST Assured logging filter.

The filter can be used to capture:

- HTTP method
- Request URL
- Request headers
- Request body
- Response status
- Response headers
- Response body

This makes API failures easier to investigate and debug.

## 📊 Test Reporting

The framework integrates **Extent Reports** for HTML-based test reporting.

Reports can provide information such as:

- Passed tests
- Failed tests
- Test execution details
- Failure information
- Request/response debugging information

## 🪵 Log4j2

Log4j2 is used for framework logging.

Configuration:

```text
src/test/resources/log4j2.xml
```

Logs can be maintained separately from test execution output to make troubleshooting easier.

## 🎧 TestNG Listener

The framework includes a custom TestNG listener:

```text
TestListener.java
```

The listener can be used for:

- Test start events
- Test success events
- Test failure events
- Test skip events
- Reporting integration
- Additional execution logging

Tests can use the listener with:

```java
@Listeners({
    com.api.listeners.TestListener.class
})
```

## 🧩 Maven Configuration

The framework uses Maven for:

- Dependency management
- Compilation
- Test execution
- CI/CD execution

The project is configured with Java 11 source and target compatibility:

```xml
<maven.compiler.source>11</maven.compiler.source>
<maven.compiler.target>11</maven.compiler.target>
```

The framework targets Java 11 bytecode while Maven may be executed using a newer JDK, provided the configured Maven plugins and dependencies support that JDK.

## ▶️ Running Tests

### Run all tests

```bash
mvn clean test
```

### Run a specific TestNG suite

The framework supports passing the TestNG suite through a Maven property:

```bash
mvn clean test -Dsuite=suite
```

This executes:

```text
suite.xml
```

## 🧪 TestNG Suite

Example:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE suite SYSTEM
        "https://testng.org/testng-1.0.dtd">

<suite name="API Automation Suite">

    <test name="API Tests">

        <packages>
            <package name="com.api.tests"/>
        </packages>

    </test>

</suite>
```

The suite can be expanded as additional API test packages are added.

## 🔄 CI/CD

The repository contains a GitHub Actions workflow:

```text
.github/workflows/maven.yml
```

The workflow can execute Maven tests automatically when changes are pushed to the repository.

Typical CI/CD flow:

```text
Developer
    |
    v
Git Push
    |
    v
GitHub Repository
    |
    v
GitHub Actions
    |
    v
Maven
    |
    v
TestNG
    |
    v
REST Assured Tests
    |
    v
Test Results
```

## 🔍 API Coverage

The framework currently demonstrates API automation around areas such as:

### Authentication

- Login
- Signup
- Forgot Password

### User/Profile

- User profile operations
- Profile request/response handling

The framework structure allows additional API services to be added without changing the existing test architecture.

## ➕ Adding a New API

### 1. Create request model

```text
models/requests/CreateUserRequest.java
```

### 2. Create response model

```text
models/response/CreateUserResponse.java
```

### 3. Create service class

```text
base/UserService.java
```

### 4. Extend BaseService

```java
public class UserService extends BaseService {
}
```

### 5. Implement the API operation

```java
public Response createUser(
        CreateUserRequest request) {

    return postRequest(
            request,
            "/api/users"
    );
}
```

### 6. Create the test

```java
@Test
public void createUserTest() {

    CreateUserRequest request =
            new CreateUserRequest();

    UserService userService =
            new UserService();

    Response response =
            userService.createUser(request);

    Assert.assertEquals(
            response.getStatusCode(),
            201
    );
}
```

## 🎯 Why This Architecture?

The framework separates responsibilities:

```text
Test Layer
    ↓
Business/API Service Layer
    ↓
Request/Response Model Layer
    ↓
REST Assured Layer
```

This provides:

- Reusability
- Maintainability
- Better separation of concerns
- Reduced code duplication
- Easier debugging
- Easier API expansion
- Cleaner test classes
- Better CI/CD integration

## 🛠️ Future Enhancements

The framework can be further enhanced with:

- Environment-based configuration
- External test data
- Data-driven testing
- JSON schema validation
- Contract testing
- Database validation
- API chaining
- Token management
- Dynamic test data generation
- Parallel execution
- Retry mechanism
- Allure reporting
- Docker execution
- API performance testing
- OpenAPI/Swagger validation
- Advanced CI/CD pipelines
- Secrets management
- Test execution by environment

## 📁 Configuration Strategy

For production-style usage, environment-specific configuration can be introduced:

```text
config/
    dev.properties
    qa.properties
    staging.properties
    prod.properties
```

The framework can then select the environment during execution:

```bash
mvn clean test -Denv=qa
```

This avoids hardcoding environment-specific values throughout the test code.

## 🔒 Security Considerations

Sensitive information should **never be committed to GitHub**.

Avoid storing:

```text
Passwords
API keys
Access tokens
Client secrets
Database passwords
Private credentials
```

Instead, use:

- Environment variables
- GitHub Secrets
- CI/CD secret stores
- External configuration

Example:

```text
API_USERNAME
API_PASSWORD
API_TOKEN
```

## 💡 Framework Philosophy

The primary goal of this project is to demonstrate how API automation can evolve from simple REST Assured tests into a maintainable automation framework.

The framework emphasizes:

```text
Abstraction
    +
Reusability
    +
Maintainability
    +
Logging
    +
Reporting
    +
CI/CD
```

## 👨‍💻 Author

**Ankit Sharma**

QA Automation / SDET Engineer

13+ years of experience in software testing and automation.

Areas of experience include:

- API Automation
- UI Automation
- Selenium
- Playwright
- REST Assured
- Java
- TestNG
- Cucumber
- CI/CD
- GitHub Actions
- AI/LLM Testing
- Agent Testing
- Event-driven testing

## ⭐ Repository

GitHub:

https://github.com/ankit19apr/DemoAPIAutomationFramework

## 📜 License

This project is intended for learning, demonstration, and portfolio purposes.
