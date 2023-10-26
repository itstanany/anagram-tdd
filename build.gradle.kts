plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.0.0")
    implementation("io.github.bonigarcia:webdrivermanager:5.2.3")
    implementation("com.madgag:animated-gif-lib:1.4")
    testImplementation(kotlin("test"))
    testImplementation("com.oneeyedmen:okeydoke:1.3.3")
//    testImplementation(kotlin("test"))
//    testImplementation("com.oneeyedmen:okeydoke:1.3.3")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}