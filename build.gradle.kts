plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("plugin.jpa") version "1.9.25"
    kotlin("plugin.serialization") version "1.9.22"
}

group = "org.bong"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.mysql:mysql-connector-j")

    // Spring Boot Starter WebFlux (WebClient 사용을 위해 추가)
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // Kotlin Coroutines Core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    // Kotlin Coroutines Reactor (WebClient와 코루틴 통합을 위해)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")

    // WebClient와 kotlinx.serialization을 연동하기 위한 추가 라이브러리
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")



    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.mockito")
    }
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // kotest dependencies
    testImplementation("io.kotest:kotest-runner-junit5:5.5.4") // 최신 버전으로 업데이트
    testImplementation("io.kotest:kotest-assertions-core:5.5.4") // 최신 버전으로 업데이트

    // mockk dependencies
    testImplementation("io.mockk:mockk:1.12.0")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
