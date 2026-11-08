plugins {
    kotlin("jvm")
}

group = "ru.nsu.photoflow"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":type-system"))
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}