# INVESTMENTS AGGREGATOR

## Description
The Investment Aggregator API is a backend application designed to manage users, investment accounts, and assets (stocks). The primary objective is to provide functionalities that allow users to register, update, and retrieve information related to their accounts and financial assets.

## Technologies
- Spring Boot with JAVA 21
- MySQL (configured via Docker)
- JUnit, Mockito
- Maven, Docker


## Requirements
- JAVA 21
- Docker and Docker Compose
- Recommended IDE (IntelliJ)


## Getting Started
### 1. Clone the project

To start, clone the repository to your local environment:
```bash
git clone https://github.com/melvintivane/investment_aggregator.git
```

### 2. Navigate to the Project Directory

Change to the project directory:
```bash
cd /investments_agregator
```

### 3. Set Up Docker Containers

Build and run the Docker containers for the application and database:
```bash
cd /docker

docker-compose up
```

### 4. Create a Brapi Account

Register for an account at [Brapi](https://brapi.dev/dashboard)
### 5. Generate a Brapi TOKEN
> Generate a Brapi Token from your dashboard. Ensure you keep this token secure and do not expose it in your codebase.
### 6. Add Brapi TOKEN to Environment Variables in IntelliJ
- Open your project in IntelliJ.

- Go to Run > Edit Configurations.

- Select the appropriate configuration.

- Add the following environment variable:

```bash
BRAPI_TOKEN=your_generated_token_here
```
> SEE THE ILLUSTRATION BELLOW

<div align="center" width="100%">
    <img width="87%" src="/assets/one.png">
    <img width="87%" src="/assets/two.png">
    <img width="87%" src="/assets/three.png">
    <img width="87%" src="/assets/four.png">
    <img width="87%" src="/assets/five.png">
</div>

### 7.Start the Application

Run the application from IntelliJ or using the following Maven command:

```bash
mvn spring-boot:run
```

## Contribution Guidelines
Feel free to contribute by submitting pull requests or reporting issues in the repository.

## Contact

For further assistance, contact the maintainer at [melvinshuster47@gmail.com]().
