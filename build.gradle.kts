plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation(gradleTestKit())
    implementation(kotlin("stdlib-jdk8"))
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.12.2")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}