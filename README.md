# Coll@HBRS

## Set up Test Database for Local Development:

### Prerequisite:
- **Docker Desktop** download here: [Docker Desktop](https://www.docker.com/products/docker-desktop/)

---

1. run this command:

```bash
docker run --name collhbrs_test -e POSTGRES_USER=collhbrs -e POSTGRES_PASSWORD=collhbrs -d -p 5432:5432 postgres
```

2. Create a `./resources/application-dev.properties` file.

```properties
spring.application.name=collhbrs
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=collhbrs
spring.datasource.password=collhbrs
spring.jpa.hibernate.ddl-auto=update
```

3. Setup dev profile in intellij

- Click on the Run menu.
- Select Edit Configurations.
- Active profile: write `dev` into the text field.

#### Please avoid staging the `application-dev.properties` file when commiting your changes!