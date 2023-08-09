val moduleName = "desktop"

val jarFile = "$projectDir/../generator/build/c++/desktop/box2d-natives.jar"

tasks.jar {
    from(zipTree(jarFile))
}

tasks.register<Jar>("platformAll") {
    from(zipTree(jarFile))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = moduleName
            from(components["java"])
        }
    }
}