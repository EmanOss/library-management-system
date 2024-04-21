# Library Management System
An API to assit librarians manage books, patrons, and borrowing records.

# Technology Used
- Java Spring Boot
- JUnit and Mockito for testing

# Getting Started
1. clone the repo
2. Navigate into the cloned repository directory:
3. Using inteliij
  - Open the pom.xml file in the root directory of the Spring Boot project.
  - IntelliJ IDEA will recognize the project as a Maven project and automatically import dependencies.
  - Run the main class of the Spring Boot application. (This class should have the @SpringBootApplication annotation.)
4. The app runs on port `8080`


# Features
- CRUD operations for Book entity.
  <br/>Example Book Entity:
  ```
  {
      "id": 502,
    "title": "The Kite Runner",
    "author": "Khaled El Hosseiny",
    "publicationYear": 2003,
    "isbn": "1234112121",
    "available": true,
    "borrowRecordsIds": [
            352
        ]
  }
  ```
- CRUD operations for Patron entity.
  <br/>Example Patron Entity:
  ```
  {
    "id": 402,
    "name": "Eman Osama",
    "email": "eman.osama222@gmail.com",
    "phone": "+201099992222",
    "canBorrow": true,
    "borrowRecordsIds": [
            352
        ]
  }
  ```
- CRUD operations for Record entity.
  <br/>Example Patron Entity:
  ```
   {
      "id": 352,
      "borrowDate": "2024-04-21",
      "returnDate": "2024-04-22",
      "book": {
          "id": 502,
          "title": "The Kite Runner",
          "author": "Khaled El Hosseiny",
          "publicationYear": 2003,
          "isbn": "1234112121",
          "available": false,
          "borrowRecordsIds": [
                    352
                ]
            },
          "patron": {
              "id": 402,
              "name": "Eman Osama",
              "email": "eman.osama222@gmail.com",
              "phone": "+201099992222",
              "canBorrow": false,
              "borrowRecordsIds": [
                  352
              ]
          }
      }
  ```

# Endpoints
● GET /api/books: Retrieve a list of all books.
● GET /api/books/{id}: Retrieve details of a specific book by ID.
● POST /api/books: Add a new book to the library.
● PUT /api/books/{id}: Update an existing book's information.
● DELETE /api/books/{id}: Remove a book from the library.
● Patron management endpoints:
● GET /api/patrons: Retrieve a list of all patrons.
● GET /api/patrons/{id}: Retrieve details of a specific patron by ID.
● POST /api/patrons: Add a new patron to the system.
● PUT /api/patrons/{id}: Update an existing patron's information.
● DELETE /api/patrons/{id}: Remove a patron from the system.
● Borrowing endpoints:
● POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to
borrow a book.
● PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.

# Future Features
- Integration testing
- End-to-end testing
- Caching
- Security and authentication

