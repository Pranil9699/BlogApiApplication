# BlogApiApplication

This is a simple blog application developed in Java using the Spring Boot framework. The application includes features such as user management, commenting, file handling, and post management.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup](#setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

1. **User Management:**
   - Create, update, and delete user profiles.
   - User registration with password encoding and role assignment.

2. **Commenting:**
   - Create and delete comments associated with posts.

3. **File Handling:**
   - Upload images for posts.
   - Retrieve and manage resources.

4. **Post Management:**
   - Create, update, and delete blog posts.
   - Retrieve posts based on various criteria (e.g., category, user, search).

## Technologies Used

- **Java:** Programming language.
- **Spring Boot:** Framework for building Java-based enterprise applications.
- **Hibernate:** Object-relational mapping for database interaction.
- **ModelMapper:** Object mapping library.
- **Spring Security:** Security framework for authentication and authorization.
- **Thymeleaf:** Server-side Java template engine.

## Project Structure

The project is structured into several packages, each responsible for specific functionalities.

- `com.pranil.blog.app.controllers`: Contains controllers for handling HTTP requests.
- `com.pranil.blog.app.entities`: Defines JPA entities for the database.
- `com.pranil.blog.app.payloads`: Contains DTOs (Data Transfer Objects) for request and response payloads.
- `com.pranil.blog.app.repositorys`: Includes Spring Data JPA repositories for database interactions.
- `com.pranil.blog.app.security`: Manages security-related components, such as authentication and authorization.
- `com.pranil.blog.app.services`: Implements service interfaces for business logic.
- `com.pranil.blog.app.services.impl`: Provides service implementations.
- `resources`: Contains configuration files, templates, and static resources.

## Setup

To run the application locally, follow these steps:

1. Clone the repository:

   ```bash
   git clone <repository-url>
2. Navigate to the project directory:

   ```bash
   cd blog-application
3. Build the project using Maven:

   ```bash
   mvn clean install
4. Run the application:

   ```bash
   mvn spring-boot:run
 - The application will be accessible at [http://localhost:8080](#http://localhost:8080).
5. Database Configuration:
   - The application uses an embedded H2 database by default.
   - You can access the H2 console at http://localhost:8080/h2-console with the following credentials:
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: (leave it blank)
6. Test the Endpoints:

    Once the application is running, you can test the various
    endpoints using tools like Postman or by accessing them  
    through a web browser.

    Example API endpoint: http://localhost:8080/api/posts.

7. Explore the Application:
  - Visit http://localhost:8080 in your browser.
  - Register a new user or log in if you already have an account.
  - Explore and use the features, such as creating posts, commenting, and managing user profiles.

8. Contributing:
  - If you would like to contribute to this project, please follow the standard procedures for contributing and creating pull requests.

9. License:

   - This project is licensed under the [MIT License](#MIT-License).
