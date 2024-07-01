import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("kotlin")
    `maven-publish`
}

java {
    sourceCompatibility = JavaVersion.VERSION_22
    targetCompatibility = JavaVersion.VERSION_22
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.8")
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
    from("LICENCE.md") {
        into("META-INF")
    }
}

group = ProjectSetting.PROJECT_LIB_ID_KOTLIN
version = ProjectSetting.PROJECT_VERSION_NAME

publishing {

    publications {

        // Creates a Maven publication called "release".
        register("release", MavenPublication::class) {

            // Library Package Name (Example : "com.frogobox.androidfirstlib")
            // NOTE : Different GroupId For Each Library / Module, So That Each Library Is Not Overwritten
            groupId = ProjectSetting.PROJECT_LIB_ID_KOTLIN

            // Library Name / Module Name (Example : "androidfirstlib")
            // NOTE : Different ArtifactId For Each Library / Module, So That Each Library Is Not Overwritten
            artifactId = ProjectSetting.LIBRARY_KOTLIN

            // Version Library Name (Example : "1.0.0")
            version = ProjectSetting.PROJECT_VERSION_NAME

            from(components["java"])

            artifact(sourcesJar)

            pom {
                packaging = "jar"
                name.set(ProjectSetting.LIBRARY_KOTLIN)
                description.set(ProjectSetting.LIBRARY_KOTLIN)
            }

        }

    }

    repositories {
        maven { url = uri("https://jitpack.io") }
    }

}