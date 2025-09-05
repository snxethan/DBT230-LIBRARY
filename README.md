# SimplePersistence - Employee Management System

A Java-based employee management system with GUI interface and multiple persistence options including MongoDB and local database storage.

## Features

- **Employee Management**: Add, view, update, and manage employee records
- **Multiple Storage Options**: Support for both MongoDB and local database persistence
- **GUI Interface**: User-friendly graphical interface for easy interaction
- **Data Serialization**: JSON and GSON support for data interchange
- **MVC Architecture**: Clean separation of Model, View, and Controller components

## Requirements

- Java 21 or higher
- Maven 3.6+ for build management
- MongoDB (optional, for MongoDB persistence)

## Dependencies

- **org.json** (20210307) - JSON processing
- **gson** (2.8.8) - Google's JSON library
- **mongodb-driver-sync** (5.1.2) - MongoDB Java driver
- **slf4j-api** and **slf4j-simple** (2.1.0-alpha1) - Logging framework

## Project Structure

```
src/main/java/org/example/
├── Main.java                    # Application entry point
├── MODEL/
│   └── EmployeeClass.java       # Employee data model
├── VIEW/
│   └── GUI.java                 # Graphical user interface
└── CONTROLLER/
    ├── Controller.java          # Main application controller
    ├── ConsoleTimer.java        # Console utilities
    ├── ConsoleWrite.java        # Console output utilities
    └── EMPLOYEE/                # Employee-specific controllers
        ├── EmployeeDatabase.java # Database persistence
        └── EmployeeMongo.java   # MongoDB persistence
```

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/snxethan/DBT230-LIBRARY.git
cd DBT230-LIBRARY
```

### 2. Build the Project

```bash
mvn clean compile
```

### 3. Run the Application

```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

Or compile and run directly:

```bash
mvn clean package
java -cp target/classes org.example.Main
```

## Usage

1. **Start the Application**: Run the main class to launch the GUI interface
2. **Choose Persistence Method**: Select between local database or MongoDB storage
3. **Manage Employees**: Use the GUI to add, view, update, or delete employee records
4. **Data Persistence**: Employee data is automatically saved using your chosen persistence method

## Employee Data Fields

- **ID**: Unique employee identifier (integer, cannot be negative)
- **First Name**: Employee's first name
- **Last Name**: Employee's last name  
- **Hire Year**: Year the employee was hired

## MongoDB Setup (Optional)

If you want to use MongoDB persistence:

1. Install and start MongoDB on your system
2. The application will connect to the default MongoDB instance (localhost:27017)
3. Employee data will be stored in the configured MongoDB database

## Development

This project follows the MVC (Model-View-Controller) pattern:

- **Model**: `EmployeeClass` represents the employee data structure
- **View**: `GUI` provides the user interface
- **Controller**: `Controller` manages application logic and coordinates between model and view

## Building for Production

```bash
mvn clean package
```

This will create a JAR file in the `target/` directory that you can distribute and run.

## License

This project is part of the DBT230 course curriculum.

## Author(s)

- [**Ethan Townsend (snxethan)**](www.ethantownsend.dev)
- Victor Keeler
- Jacob Brincefield

---

*This is a course project for database and programming fundamentals.*
