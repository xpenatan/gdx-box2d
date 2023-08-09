plugins {
    id("org.gretty") version("3.1.0")
}

gretty {
    contextPath = "/"
    extraResourceBase("build/dist/webapp")
}

val mainClassName = "com.github.xpenatan.gdx.examples.box2d.Build"

dependencies {
    implementation(project(":examples:basic:core"))
    implementation("com.badlogicgames.gdx:gdx:${LibExt.gdxVersion}")
    implementation("com.github.xpenatan.gdx-teavm:backend-teavm:${LibExt.gdxTeaVMVersion}")

    implementation(project(":box2d:teavm"))
}

tasks.register<JavaExec>("buildExampleBox2D") {
    dependsOn("classes")
    group = "teavm"
    description = "Build Box2D example"
    mainClass.set(mainClassName)
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.register("runBox2D") {
    group = "examples-teavm"
    description = "Run Box2D example"
    val list = arrayOf(
        "clean",
        "buildExampleBox2D",
        "jettyRun"
    )
    dependsOn(list)
    tasks.findByName("buildExampleBox2D")?.mustRunAfter("clean")
    tasks.findByName("jettyRun")?.mustRunAfter("buildExampleBox2D")
}
