plugins {
    kotlin("jvm") version "1.9.22"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

kotlin {
    jvmToolchain(21) 
}

repositories { mavenCentral() }

dependencies {
    implementation("io.ktor:ktor-client-core:2.3.7")
    implementation("io.ktor:ktor-client-cio:2.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")
}

application { mainClass.set("NotSoBuggyClientKt") }