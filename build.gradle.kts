plugins {
    id("java")
    id("application") // Add this line
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

// Define the main class for running the application
application {
    mainClass.set("org.example.Main") // Change to your actual main class
}
