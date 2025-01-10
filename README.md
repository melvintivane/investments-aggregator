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
1. Clone the project
```bash
git clone https://github.com/melvintivane/investment_aggregator.git
```

2. Select the folder containing the project
```bash
cd /investments_agregator
```

3. Build the docker image
```bash
cd /docker

docker-compose up
```

4. Create account in [Brapi](https://brapi.dev/dashboard)
5. Generate TOKEN in Brapi
> Your token must be kept in secret, so is not a good idea to write it anywhere.
6. Add Brapi TOKEN to Environment Variables in IntelliJ
![one](/assets/one.png)
![two](/assets/two.png)
![three](/assets/three.png)
![four](/assets/four.png)
![five](/assets/five.png)

7.Start the Application

Run the application from IntelliJ or using the following Maven command:

```bash
mvn spring-boot:run
```

## Contribution Guidelines
Feel free to contribute by submitting pull requests or reporting issues in the repository.

## Contact

For further assistance, contact the maintainer at [melvinshuster47@gmail.com]().
