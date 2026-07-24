# sty_backend_def

Backend for the Sty App, a workouts application already available in production on the Google Play Store.

This repository contains the API responsible for the application's business rules, authentication, data persistence, and integration with external services. The backend is still under active development and receives new implementations every week.

## Technologies used

- Java 17
- Spring Boot
- Spring Web / Spring MVC
- Spring Data JPA
- Hibernate
- Spring Security
- Bean Validation
- Flyway
- PostgreSQL
- OpenAPI / Swagger
- Lombok
- Maven Wrapper
- Cloudinary

## Project structure

The project is organized in layers to keep maintenance simpler and the backend evolution more predictable:

- `controllers`: exposes the REST endpoints.
- `services`: contains the business rules.
- `repositories`: handles database access.
- `domains/models`: application entities and DTOs.
- `infra`: security, documentation, and error handling configuration.

## Main features

- User registration and authentication.
- User profile management.
- Workout creation and retrieval.
- Exercise creation and retrieval.
- History and performance tracking.
- Image storage integration through Cloudinary.
- API documentation with OpenAPI.

## Project status

The Sty App backend is under active development. New features, business rule adjustments, and infrastructure improvements are added weekly.

## Running locally

1. Configure the environment variables and database credentials in `src/main/resources/application.properties`.
2. Make sure PostgreSQL is running and available.
3. Start the application with Maven Wrapper:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

## Notes

- Database migrations are managed with Flyway.
- The API documentation can be accessed through Swagger/OpenAPI when the application is running.
- The project uses Maven Wrapper to make execution easier in any environment.
