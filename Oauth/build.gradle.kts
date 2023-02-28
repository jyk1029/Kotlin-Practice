import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version PluginVersion.SPRING_BOOT_FRAMEWORK_VERSION
    id("io.spring.dependency-management") version PluginVersion.SPRING_DEPENDENCY_MANAGEMENT_VERSION
    kotlin("jvm") version PluginVersion.JVM_VERSION
    kotlin("plugin.spring") version PluginVersion.PLUGIN_SPRING_VERSION
    kotlin("plugin.jpa") version PluginVersion.PLUGIN_JPA_VERSION
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation(Dependency.JPA)
    implementation(Dependency.SECURITY)
    implementation(Dependency.VALIDATION)
    implementation(Dependency.WEB)
    implementation(Dependency.JACKSON)
    implementation(Dependency.REFLECT)
    implementation(Dependency.JDK8)
    implementation(Dependency.JWT)
    runtimeOnly(Dependency.MYSQL)
    implementation(Dependency.REDIS)
    implementation(Dependency.OAUTH2)
    implementation(Dependency.QUERYDSL)
    implementation("org.springframework.security.oauth.boot", Dependency.OAUTH2_AUTOCONFIGURE, DependencyVersion.OAUTH2_AUTOCONFIGURE)
    implementation("org.springframework.security", Dependency.OAUTH2_CLIENT, DependencyVersion.OAUTH2_CLIENT)
    annotationProcessor(Dependency.CONFIGURATION_PROCESSOR)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}
