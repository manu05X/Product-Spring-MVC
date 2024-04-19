# ProductService

## Overview
ProductService is a Spring Boot application that serves as an example of interacting with the FakeStore API to fetch product data. The application fetches product information from the FakeStore API and exposes endpoints to retrieve all products or fetch a specific product by its ID.

## Features
- Fetches product data from the FakeStore API.
- Provides endpoints to:
  - Get all products.
  - Get a product by its ID.

## Technologies Used
- Java
- Spring Boot
- RestTemplate
- Lombok

## External API
- **FakeStore API:** A mock e-commerce API that provides fake product data for testing and development purposes.

## Setup
1. Clone the repository: `git clone <repository_url>`
2. Navigate to the project directory: `cd ProductServiceJanBatch`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`
5. Access the application at: [http://localhost:8080](http://localhost:8080)

## Usage
- To fetch all products: `GET http://localhost:8080/products`
- To fetch a product by its ID: `GET http://localhost:8080/products/{id}`

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
