Overview
It is a Spring Boot application that serves as an example of interacting with the FakeStore API to fetch product data. The application fetches product information from the FakeStore API and exposes endpoints to retrieve all products or fetch a specific product by its ID.

Features
Fetches product data from the FakeStore API.
Provides endpoints to:
Get all products.
Get a product by its ID.
Technologies Used
Java
Spring Boot
RestTemplate
Lombok
External API
FakeStore API: A mock e-commerce API that provides fake product data for testing and development purposes.
Setup
Clone the repository: git clone <repository_url>
Navigate to the project directory: cd ProductServiceJanBatch
Build the project: mvn clean install
Run the application: mvn spring-boot:run
Access the application at: http://localhost:8080
Usage
To fetch all products: GET http://localhost:8080/products
To fetch a product by its ID: GET http://localhost:8080/products/{id}
