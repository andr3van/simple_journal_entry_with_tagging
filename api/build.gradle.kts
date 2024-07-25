import com.expediagroup.graphql.plugin.gradle.graphql
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath("org.flywaydb:flyway-mysql:10.16.0")
    }
}

plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    id("com.expediagroup.graphql") version "7.1.1"
    id("org.jooq.jooq-codegen-gradle") version "3.19.10"
    id("org.flywaydb.flyway") version "10.15.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"

    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
}

group = "com.okeicalm"
version = "0.0.1-SNAPSHOT"

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

val graphqlKotlinVersion = "7.1.1"

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-jooq:3.3.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools:3.3.0")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // GraphQL
    implementation("com.expediagroup", "graphql-kotlin-hooks-provider", graphqlKotlinVersion)
    implementation("com.expediagroup", "graphql-kotlin-spring-server", graphqlKotlinVersion)
    implementation("com.expediagroup", "graphql-kotlin-schema-generator", graphqlKotlinVersion)
    implementation("com.graphql-java:graphql-java-extended-scalars:17.0")

    // DB
    runtimeOnly("com.mysql:mysql-connector-j:8.4.0")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    implementation("org.jooq:jooq-codegen:3.19.10")
    jooqCodegen("com.mysql:mysql-connector-j:8.4.0")
    runtimeOnly("jakarta.validation:jakarta.validation-api:3.1.0")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.3.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.3.0")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
}

object DB {
    const val USERNAME = "mysqluser"
    const val PASSWORD = "password"
    const val URL = "jdbc:mysql://127.0.0.1:3306/simple_journal_entry_db?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true"
}

jooq {
    configuration {
        logging = org.jooq.meta.jaxb.Logging.WARN
        jdbc {
            username = DB.USERNAME
            password = DB.PASSWORD
            driver = "com.mysql.cj.jdbc.Driver"
            url = DB.URL
        }
        generator {
            name = "org.jooq.codegen.KotlinGenerator"
            database {
                name = "org.jooq.meta.mysql.MySQLDatabase"
                inputSchema = "simple_journal_entry_db"
                excludes = "flyway_schema_history"
            }
            generate {
                isDeprecated = false
            }
            target {
                packageName = "com.okeicalm.simpleJournalEntry.infra.db"
                directory = "${project.rootDir}/src/main/generated"
            }
        }
    }
}

flyway {
    url = DB.URL
    user = DB.USERNAME
    password = DB.PASSWORD
}

graphql {
    schema {
        packages = listOf("com.okeicalm.simpleJournalEntry")
    }
}

ktlint {
    filter {
        exclude { it.file.path.contains("generated") }
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

sourceSets {
    main {
        kotlin {
            srcDir("src/main/generated")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val graphqlGenerateSDL by tasks.getting(com.expediagroup.graphql.plugin.gradle.tasks.GraphQLGenerateSDLTask::class) {
    packages.set(listOf("com.okeicalm.simpleJournalEntry"))
    schemaFile.set(file("${project.projectDir}/../graphql/schema.graphql"))
}
