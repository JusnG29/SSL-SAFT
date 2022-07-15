# SSL-SAFT

Sternsteins-Accounting-Facilitation-Tool

## Local development

### Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)

- [JDK17](https://www.oracle.com/java/technologies/downloads/#jdk17-windows) --> download + let `JAVA_HOME` environment variable point to `C:\Program Files\Java\jdk-17.x.x`

- An IDE of your choice

### Getting started

1. Start Docker Desktop

2. Navigate into `/src/docker/local` and run `docker compose up`

3. Build and run the project via IDE

### Accessing local database

On database startup, `saft-dev` database is already created.

There are two ways to get insights into the local dev database:

- Connecting via Adminer
  - Web-app which is started alongside the database itself
  - Open `localhost:8080`
  - Server: `saft-db`
  - User and password: `postgres`
  - Database: `saft-db`
- Connect via IDE or dedicated tool such as [pgAdmin](https://www.pgadmin.org/)
  - Use same credentials as above
