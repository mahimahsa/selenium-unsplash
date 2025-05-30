# 🧪 Unsplash Selenium Test Suite

Automated UI testing project for [Unsplash](https://unsplash.com), using **Selenium**, **JUnit 5**, **Docker**, and **Gradle**.  
Supports local and CI/CD test execution via GitHub Actions and Docker Compose.

---

## ✅ Features

- Page Object Model (POM) structure
- Tests for:
    - Login, logout, email validation
    - Responsive layout (mobile vs. desktop)
    - Form input (textarea, dropdown)
    - File upload (profile image)
    - Cookie manipulation (consent banner)
    - Navigation and browser history
- Explicit waits and complex XPath
- Test data and driver config from environment
- GitHub Actions CI with Selenium Grid

---

## 🚀 Getting Started

### 📦 Prerequisites

- Java 17+ (Java 21 tested)
- Docker & Docker Compose
- Gradle (optional, wrapper is included)

---

### ▶️ Local Run (with ChromeDriver)

```bash
./gradlew clean test
```

### ▶️ Run with Docker Compose 
Runs tests using selenium/standalone-chrome container
```bash
docker compose up --build
```
---
## 🔧 Project Structure
```
.
├── src/test/java/
│   ├── base/           → BasePage and utilities
│   ├── pages/          → Page Object classes (LoginPage, HomePage, AccountPage, etc.)
│   ├── tests/          → Test classes using @Test and @ParameterizedTest
│   └── utils/          → ViewMode, CookieHelper, Config, etc.
├── Dockerfile
├── docker-compose.yml
├── wait-for-selenium.sh
├── build.gradle
└── README.md
```
---
## 🧪 Example Test Cases

✅ loginPageEmailValidation()

✅ shouldUpdateBioAndUploadImage()

✅ headerShouldChangeBetweenScreenModes()

✅ staticPagesShouldHaveCorrectTitles()

✅ manipulateConsentCookie()

✅ testBrowserHistoryNavigation()

---

## ⚙️ Configuration
Environment variable used for remote execution (Docker Compose and GitHub Actions):
````css
SELENIUM_HOST=http://localhost:4444/wd/hub
````
---
## 🛠 CI/CD with GitHub Actions
### Test workflow:

- Uses Ubuntu runner

- Pulls Selenium container

- Waits for grid to be ready

- Runs tests headlessly

See ``` .github/workflows/test.yml ```

---


## 📄 License
MIT License — for educational use.
This project is not affiliated with Unsplash.com.
```
Let me know if you want a badge section (build status, Java version, etc.) or contribution instructions.
```
