# University-Enrolment-
COMP3005

Setup Instructions

Prerequisites
Java JDK 8 or later
PostgreSQL 12 or later
PostgreSQL JDBC Driver

The project includes the following Java files:
Database: database creation.

StudentDatabaseOperations.java: Contains methods for database operations and main function.

How to run the application
If you're using an IDE like VS Code IntelliJ IDEA, you can build the project through the IDE's build feature

For command line compilation, navigate to the project directory and run:
javac -cp path/to/postgresql-jdbc.jar;. *.java
java -cp path/to/postgresql-jdbc.jar;.StudentDatabaseOperations
