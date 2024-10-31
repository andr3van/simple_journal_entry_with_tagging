# SimpleJournalEntry

## Getting Started

```shell
docker-compose up -d

cd api

./gradlew flywayMigrate

./gradlew bootRun
```

## DB Migration

1. Add migration SQL file to `api/src/main/resources/db/migration`.
2. Run `api/gradlew flywayMigrate` to migrate DB.
3. Run `api/gradlew jooqCodegen` to generate Kotlin files from DB.

## How to Test GraphQL Server

You can use GraphiQL to test the GraphQL server.  
Open your browser and go to http://localhost:8080/graphiql

## Sample GraphQL Queries
- ### Create account and journal entry
  ```
  // Account
  mutation {
    createAccount(input: {
      code: "Foo",
      name: "Bar",
      elementType: ASSETS
    }) {
      id
      name
      code
      elementType
    }
  }

  // Journal
  mutation {
    createJournal(input: {
      incurredOn: 20240907
      createJournalEntryInput: [
        { side: 1, accountID: "1", value: 500 },
        { side: 2, accountID: "1", value: 500, tags: ["Foo Tag", "Bar Tag"] }
      ]
    }) {
      id
      incurredOn
      journalEntries {
        id
        side
        value
        account {
          id
          code
          name
          elementType
        }
      }
    }
  }
  ```

- ### Read all accounts
  ```
  query {
    allAccounts {
      id
      code
      name
      elementType
    }
  }

  // Using curl
  curl -X POST http://localhost:8080/graphql \
    -H "Content-Type: application/json" \
    -d '{"query": "query { allAccounts { id code name elementType } }"}'
  ```

- ### Read all journals

  ```
  query {
    allJournals {
      id
      incurredOn
      journalEntries {
        id
        side
        value
        account {
          id
          code
          name
          elementType
        }
      }
    }
  }
  ```

- ### Find all journals entries by tag name
  ```
  query {
    findJournalEntriesByTagName(tagName: "Foo Tag") {
        value
    }
  }
  ```

- ### Update an account
  ```
  mutation {
    updateAccount(input: {
      id: "1",
      code: "UpdatedCode",
      name: "UpdatedName",
      elementType: ASSETS
    }) {
      id
      code
      name
      elementType
    }
  }
  ```

- ### Delete an account
  ```
  mutation {
    deleteAccount(input: { id: "1" }) {
      deletedAccount {
        id
      }
    }
  }
  ```