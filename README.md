# Golf Club Tournament & Membership API

This project is a RESTful web application designed to manage golf club members and tournaments. It allows users to perform CRUD operations, associate members with tournaments, and run custom search queries. The application uses Spring Boot, MySQL (RDS-compatible), and Docker for containerization.

## Features

### Members

* Create, read, update, and delete members
* Search by name, email, or phone number
* Store member details:

    * ID (auto-generated)
    * Name
    * Address
    * Email
    * Phone number
    * Membership start date
    * Membership duration (months)

### Tournaments

* Create, read, update, and delete tournaments
* Search by start date or location
* Associate members with tournaments
* Store tournament details:

    * ID (auto-generated)
    * Start date
    * End date
    * Location
    * Entry fee
    * Cash prize

## API Endpoints

### Member Endpoints

* `GET /members` - Retrieve all members
* `GET /members/{id}` - Retrieve a specific member by ID
* `POST /members` - Create a new member
* `PUT /members/{id}` - Update an existing member
* `DELETE /members/{id}` - Delete a member
* `GET /members/member_search?member_name=...&member_email=...&member_phone=...` - Search members

### Tournament Endpoints

* `GET /tournaments` - Retrieve all tournaments
* `GET /tournaments/{id}` - Retrieve a specific tournament by ID
* `POST /tournaments` - Create a new tournament
* `PUT /tournaments/{id}` - Update a tournament
* `DELETE /tournaments/{id}` - Delete a tournament
* `POST /tournaments/{tournamentId}/members/{memberId}` - Add a member to a tournament
* `GET /tournaments/tournament_search?tournament_date=...&tournament_location=...` - Search tournaments

## Docker Setup

### Prerequisites

* Docker and Docker Compose installed

### Run Locally with Docker Compose

1. Clone the repository:

```bash
git clone <repo-url>
cd <project-directory>
```

2. Build and run the containers:

```bash
docker compose up --build
```

3. The API will be available at `http://localhost:8080`

> **Note:** This project is configured to connect to a remote AWS RDS MySQL database. If you wish to run it with a local MySQL instance, update `SPRING_DATASOURCE_URL` in the Docker Compose environment variables accordingly.

## AWS RDS Setup

* The application is configured to connect to an Amazon RDS MySQL instance.
* Update the following environment variables in `docker-compose.yml`:

```yaml
SPRING_DATASOURCE_URL: jdbc:mysql://<your-RDS-endpoint>:3306/QAP3_API_DB
SPRING_DATASOURCE_USERNAME: <your-username>
SPRING_DATASOURCE_PASSWORD: <your-password>
```

## Testing

* API endpoints can be tested using Postman
* Screenshots of Postman tests have been provided for validation of core functionality

## Technologies Used

* Java 21
* Spring Boot
* MySQL 
* AWS RDS
* Docker