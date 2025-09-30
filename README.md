# Belezenje Radnih Vremena - Backend

## About The Project

This is the backend for the "Belezenje Radnih Vremena" (Work Time Tracking) application. It provides the server-side logic and API for managing work orders, production operations, and employee time tracking in a manufacturing setting.

## Key Features

* **Work Order Management:** Create, update, and track the status of work orders.
* **Production Planning:** Define products and their associated production operations.
* **Time Tracking:** Record the time spent by employees on each operation.
* **Real-time Updates:** Utilizes WebSockets to provide live updates on production activities.
* **Resource Management:** Manage machines, tools, and other resources.
* **User Management:** Handles employees and their roles within the system.

## Built With

* [Java](https://www.java.com/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Spring WebSocket](https://spring.io/projects/spring-framework)
* [Maven](https://maven.apache.org/)

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

* Java JDK 17 or later
* Maven

### Installation

1.  **Clone the repo**
    ```sh
    git clone [https://github.com/upisivanje-radnih-vremena/upisivanje-radnih-vremena-back.git](https://github.com/upisivanje-radnih-vremena/upisivanje-radnih-vremena-back.git)
    ```

2.  **Configuration**
    * Navigate to the root directory of the project.
    * Update the `application.properties` file in `src/main/resources` with your database configuration and other environment-specific settings.

3.  **Run the application**
    * Build and run the Spring Boot application using Maven:
        ```sh
        ./mvnw spring-boot:run
        ```

## API Endpoints

The API endpoints are defined in the controller classes within the `src/main/java/com/konstil/Belezenje/radnih/vremena/controller` package. Please refer to the source code for detailed information on the available endpoints and their usage.

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request
