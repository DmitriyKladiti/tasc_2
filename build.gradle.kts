import groovy.xml.dom.DOMCategory.attributes

plugins {
    kotlin("jvm") version "1.8.0"
    application
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
//    implementation("org.kohsuke:args4j:2.33")
    implementation("args4j:args4j:2.33")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}

tasks.jar {
    manifest {
        attributes(mapOf("Main-Class" to "MainKt"))
    }
}