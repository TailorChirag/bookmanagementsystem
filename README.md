##  Docker Deployment
- DockerHub: [book-management-system] https://hub.docker.com/repository/docker/tailorchirag/book-management-system
 	

The Book Management System is a Spring Boot-based RESTful API designed for managing books. It provides functionalities to:
	•	Add, view, search, update, and delete book records.
	•	Enforces data validation and ensures consistent availability status values.
	•	Utilizes Spring Boot, Spring Data JPA, and MySQL for persistence.
 
 Technologies Used
	•	Backend: Spring Boot, Spring Data JPA
	•	Database: MySQL
	•	ORM: Hibernate
	•	Build Tool: Maven
	•	Testing: JUnit, Postman
	•	IDE: IntelliJ IDEA
 
 Features Implemented
	1.	CRUD Operations
	•	POST /api/books: Add a new book.
	•	GET /api/books: View all books.
	•	GET /api/books/{id}: Search by ID.
	•	PUT /api/books/{id}: Update book details.
	•	DELETE /api/books/{id}: Delete a book.
	2.	Validations
	•	Book ID: Unique constraint.
	•	Title & Author: Cannot be empty (validated using @NotBlank).
	•	Availability status: Restricted to AVAILABLE or CHECKED_OUT using Enum.
	3.	Exception Handling
	•	Custom exception DuplicateBookException for duplicate titles.
	•	Global exception handler with meaningful error messages.

 Challenges Faced and Solutions
  
  1. Schema-validation: missing table [books]
	  •	Cause: The table schema was missing or not automatically created.
	  •	Solution:
	  •	Added spring.jpa.hibernate.ddl-auto=update in application.properties.
	  •	Manually created the books table in MySQL:
  2. Missing jakarta.validation Dependencies
	  •	Cause: Validation annotations like @NotBlank and @NotNull were not recognized.
	  •	Solution:
	  •	Added Jakarta Validation dependencies to pom.xml.
  3. Refactoring for Simplicity
	  •	Initially, the project had a service package, which was unnecessary.
	  •	Since Spring Data JPA handles CRUD operations directly, I:
	  •	Removed the service layer to simplify the project structure.
	  •	Directly used BookRepository in the BookController.

Improvements and Changes Made

	1.	Enum Validation for Availability Status
	  •	Only AVAILABLE or CHECKED_OUT values are allowed.
	2.	Custom Exception Handling
	  •	Added DuplicateBookException and GlobalExceptionHandler.
	3.	Validation Annotations
	  •	Added @NotBlank, @Size, and @NotNull for input validation.
	4.	Simplified Architecture
	  •	Removed the service package, using BookRepository directly.
	5.	Database Configuration
	  •	Ensured proper MySQL connection handling and table creation.

How to Run the Project:
  1. Clone the repository: git clone https://github.com/TailorChirag/bookmanagementsystem.git cd bookmanagementsystem
  2. Build the project: mvn clean install
  3. Start the application: mvn spring-boot:run
  4. API Endpoints:
	    1. Add a Book: POST http://localhost:8080/api/books
          Content-Type: application/json
{
  "title": "Java Programming",
  "author": "John Doe",
  "genre": "Technology",
  "availabilityStatus": "AVAILABLE"
}
  2. View All Books: GET http://localhost:8080/api/books
  3. Search by ID : GET http://localhost:8080/api/books/1
  4. Update a Book : PUT http://localhost:8080/api/books/1
      Content-Type: application/json
      {
        "title": "Updated Title",
        "author": "Updated Author",
        "genre": "Updated Genre",
        "availabilityStatus": "CHECKED_OUT"
      }
  5. Delete a Book: DELETE http://localhost:8080/api/books/1

Key Takeaways
	1.	Simplified architecture: Removed unnecessary service layer.
	2.	Validation and Exception Handling: Added validation and custom exceptions.
	3.	Database Optimization: Ensured proper MySQL connection and schema validation.
	4.	Error Resolution: Solved Maven issues, validation errors, and connection problems.

Potential Improvements
  1. Add Pagination and Sorting Currently, the GET /api/books endpoint returns all books at once, which is inefficient for large datasets.
      Improvements:
    	•	Add pagination and sorting using Pageable in Spring Data JPA.
    	•	This improves performance and reduces the load on the database.

  2. Enhance Search Functionality

   Currently, you can only search by ID.
    To make the system more flexible, add:
      •	Multiple search criteria: Search by title, author, or genre.
      •	Case-insensitive and partial matching.
      
3. DTO and Mapper Layer

Currently, the API directly uses the Book entity in requests and responses, which can lead to:
	•	Tight coupling between the database and API.
	•	Exposing sensitive fields unintentionally.

Improvements:
	•	Introduce DTOs (Data Transfer Objects) to decouple the database from the API.
	•	Use MapStruct or ModelMapper for automatic mapping.

