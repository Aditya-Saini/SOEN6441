# NotiLytics - News Analytics Platform

A comprehensive web application built with Play Framework that analyzes live news feeds using advanced sentiment analysis, readability metrics, and news source profiling.

## Features

- **News Search**: Search and analyze news articles with advanced filtering
- **Sentiment Analysis**: Understand the emotional tone of news articles
- **Readability Analysis**: Calculate Flesch-Kincaid Grade Level and Flesch Reading Score
- **Word Statistics**: Display word-level statistics for search queries
- **Source Profiling**: Detailed information about news sources
- **Real-time Analytics**: Process live news feeds using Java 8+ Streams API

## Technology Stack

- **Backend**: Play Framework 3.0.0
- **Language**: Java 8+
- **Frontend**: HTML5, CSS3, JavaScript, Bootstrap 5
- **Testing**: JUnit 4, Mockito, JaCoCo
- **Build Tool**: SBT
- **API**: News API v2

## Getting Started

### Prerequisites

- Java 8 or higher
- SBT (Scala Build Tool)
- News API key (sign up at https://newsapi.org/)

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd NotiLytics
```

2. Set up your News API key:
```bash
# Windows
set NEWS_API_KEY=your_api_key_here

# Linux/Mac
export NEWS_API_KEY=your_api_key_here
```

3. Test the build (recommended first step):
```bash
# Windows
test-build.bat

# Linux/Mac
sbt clean update compile test
```

4. Run the application:
```bash
sbt run
```

5. Open your browser and navigate to `http://localhost:9000`

### Building and Testing

```bash
# Compile the project
sbt compile

# Run tests
sbt test

# Generate test coverage report
sbt jacoco

# Generate documentation
sbt javadoc

# Clean build artifacts
sbt clean
```

### Troubleshooting

If you encounter issues:

1. **SBT Server Already Running Error:**
   ```bash
   # Windows
   taskkill /f /im java.exe
   
   # Linux/Mac
   pkill -f sbt
   ```

2. **Dependency Resolution Issues:**
   ```bash
   sbt clean
   sbt update
   ```

3. **Jackson Version Conflicts:**
   - The project uses Jackson 2.14.3 for compatibility with Play Framework 3
   - If you see Jackson version errors, ensure you're using the correct version

4. **Build Issues:**
   - Ensure you have Java 8+ installed
   - Check your internet connection for dependency downloads
   - Try running the `test-app.bat` script (Windows) for automated testing

## Project Structure

```
NotiLytics/
├── app/
│   ├── controllers/          # Application controllers
│   ├── models/              # Data models and business logic
│   └── views/               # HTML templates
├── conf/
│   ├── application.conf     # Application configuration
│   └── routes              # URL routing
├── public/
│   ├── stylesheets/        # CSS files
│   ├── javascripts/        # JavaScript files
│   └── images/            # Static images
├── test/                   # Unit tests
├── build.sbt              # Build configuration
└── project/
    └── plugins.sbt        # SBT plugins
```

## Development Guidelines

- All controller actions must be asynchronous using `CompletionStage<T>`
- Use Java 8+ Streams API for data processing
- Follow MVC pattern - keep business logic in model classes
- Write comprehensive unit tests with 100% coverage
- Document all classes and methods with Javadoc

## License

This project is developed for educational purposes as part of SOEN-6441 course at Concordia University.

## Team

NotiLytics Development Team
