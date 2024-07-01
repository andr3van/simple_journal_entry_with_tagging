# SimpleJournalEntry

## Getting Started

```shell
docker-compose up -d

cd api

./gradlew flywayMigrate

./gradlew bootRun
```

## DB migration

1. Add migration SQL file to `api/src/main/resources/db/migration`.
2. Run `api/gradlew flywayMigrate` to migrate DB.
3. Run `api/gradlew jooqCodegen` to generate Kotlin files from DB.

## Hot to test GraphQL server

You can use GraphiQL to test the GraphQL server.  
Open your browser and go to http://localhost:8080/graphiql
