# Task Tracker

A simple command-line task tracking application built in Java using Maven.

## Features

- Add new tasks with descriptions
- Update task descriptions
- Delete tasks by ID
- Mark tasks as in-progress or done
- List all tasks or filter by status (todo, in-progress, done)
- Persistent storage using JSON file
- Command-line interface with help

## Prerequisites

- Java 21 or higher
- Maven 3.6+

## Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/tasktracker.git
   cd tasktracker
   ```

2. Build the project:
   ```
   mvn clean package
   ```

3. Run the application:
   ```
   java -jar target/tasktracker-0.0.1-SNAPSHOT-jar-with-dependencies.jar <command>
   ```

## Usage

### Commands

- `add <description>` - Add a new task
- `update <id> <description>` - Update a task's description
- `delete <id>` - Delete a task by ID
- `mark-in-progress <id>` - Mark a task as in-progress
- `mark-done <id>` - Mark a task as done
- `list` - List all tasks
- `list <status>` - List tasks by status (todo, in-progress, done)
- `help` - Display usage instructions

### Examples

Add a task:
```
java -jar target/tasktracker-0.0.1-SNAPSHOT-jar-with-dependencies.jar add "Buy groceries"
```

List all tasks:
```
java -jar target/tasktracker-0.0.1-SNAPSHOT-jar-with-dependencies.jar list
```

Mark a task as done:
```
java -jar target/tasktracker-0.0.1-SNAPSHOT-jar-with-dependencies.jar mark-done 1
```

## Dependencies

- Jackson Databind for JSON serialization
- JUnit Jupiter for testing

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## License

This project is open source. Please check the license file for details.