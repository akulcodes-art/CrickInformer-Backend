CrickInformer Backend
Overview
CrickInformer is a robust backend service built with Spring Boot that provides real-time and comprehensive cricket match information. The application fetches live match data, scores, and tournament point tables by scraping the web, processes the information, and exposes it through a clean RESTful API. This service can be used as the backbone for any frontend application (web or mobile) that needs up-to-date cricket data.

The project demonstrates a strong understanding of backend development principles, including API design, web scraping, data persistence with a relational database, and Spring Boot architecture.

Features
Get Live Matches: Provides a list of all currently live cricket matches with detailed information like scores, venue, and live status text.

Get All Matches: Fetches a complete list of matches stored in the database.

Get World Cup Point Table: Scrapes and returns the latest points table for major tournaments like the ICC Cricket World Cup.

Data Persistence: Automatically saves and updates match data into a MySQL database to maintain a history and reduce redundant scraping.

Tech Stack
Framework: Spring Boot 3

Language: Java 17

Database: MySQL

Data Access: Spring Data JPA / Hibernate

Web Scraping: Jsoup

Build Tool: Apache Maven

API Endpoints
The following are the main endpoints exposed by the application.

Base URL: http://localhost:8080

Method	Endpoint	Description
GET	/match/live	Fetches a list of all live cricket matches.
GET	/match	Retrieves all matches that have been stored in the database.
GET	/match/point-table	Returns the current tournament points table.

Export to Sheets
Example Responses
GET /match/live
This endpoint returns a JSON array of live match objects. The application was successfully tested and returns a 200 OK status with the expected data.

JSON

[
    {
        "matchId": 0,
        "teamHeading": "Australia U19 vs India U19",
        "matchNumberVenue": "Only Youth Test, Day 2 - Youth Test Today at Tony Ireland Stadium, Townsville",
        "battingTeam": "IND19",
        "battingTeamScore": "144-7",
        "bowlTeam": "AUS19",
        "bowlTeamScore": "335",
        "liveText": "Stumps: Day 2 - India U19 trail by 191 runs",
        "matchLink": "/live-cricket-scores/33528/aus19-vs-ind19-2nd-youth-test-india-under-19-tour-of-australia-2025",
        "textComplete": "",
        "status": "LIVE",
        "date": "2025-10-07T15:44:37.599+00:00"
    },
    ...
]
GET /match/point-table
This endpoint returns a JSON array of arrays, where the first inner array contains the table headers and subsequent arrays contain team data. The application was successfully tested and returns a 200 OK status.

JSON

[
    ["Teams", "Mat", "Won", "Lost", "Tied", "N/R", "Pts", "NRR"],
    ["India (Q)", "9", "9", "0", "0", "0", "18", "+2.570"],
    ["South Africa (Q)", "9", "7", "2", "0", "0", "14", "+1.261"],
    ...
]
Code Structure Highlights
entities/Match.java: The core JPA entity representing a cricket match. It includes fields for team names, scores, status, and other details. It also contains logic to automatically set the match status based on whether the textComplete field is blank.

services/impl/MatchServiceImpl.java: The heart of the business logic.

getLiveMatches(): Uses the Jsoup library to connect to and parse live score information from "cricbuzz.com". It iterates through HTML elements, extracts the relevant data, and populates Match objects.

updateMatchInDb(): A smart helper method that checks if a match already exists in the database based on its teamHeading. If it exists, it updates the record; otherwise, it saves a new one. This prevents duplicate entries.

getPointTable(): Connects to the Cricbuzz points table URL and parses the HTML table to build a List<List<String>> representing the data.

controllers/MatchController.java: The REST controller that exposes the service logic as API endpoints. It uses @GetMapping to map HTTP requests to the appropriate service methods and wraps the responses in ResponseEntity for proper HTTP status codes.

repositories/MatchRepo.java: A Spring Data JPA repository that provides an abstraction layer over the database, including a custom query method findByTeamHeading to facilitate match lookups.

application.properties: Configuration file for setting the application name, database URL, credentials, and JPA/Hibernate properties like ddl-auto=update.

How to Set Up and Run the Project
Prerequisites:

Java 17 or higher

Maven

MySQL Server

Database Setup:

Create a new database in MySQL named crickinformer.

You don't need to create any tables; Hibernate will do this automatically because of the spring.jpa.hibernate.ddl-auto=update property.

Configuration:

Open the src/main/resources/application.properties file.

Update the spring.datasource.username and spring.datasource.password properties with your MySQL credentials.

Run the Application:

Clone the repository.

Open the project in your IDE (like IntelliJ IDEA).

Run the CrickInformerBackendApplication.java file. The server will start on port 8080.

Test the Endpoints:

Use an API client like Postman to send GET requests to the endpoints listed above (e.g., http://localhost:8080/match/live).
