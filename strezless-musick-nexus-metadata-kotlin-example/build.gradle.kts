plugins {
    id("strezless-musick-nexus-metadata.kotlin")
    application
}

dependencies {
    implementation(project(":strezless-musick-nexus-metadata-kotlin-core"))
    implementation(project(":strezless-musick-nexus-metadata-kotlin-client-okhttp"))
}

application {
    // Use `./gradlew :strezless-musick-nexus-metadata-kotlin-example:run` to run `Main`
    // Use `./gradlew :strezless-musick-nexus-metadata-kotlin-example:run -Pexample=Something` to run `SomethingExample`
    mainClass = "com.strezless_musick_nexus_metadata.api.example.${
        if (project.hasProperty("example"))
            "${project.property("example")}ExampleKt"
        else
            "MainKt"
    }"
}
