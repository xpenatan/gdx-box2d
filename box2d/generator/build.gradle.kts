import de.undercouch.gradle.tasks.download.Download
import org.gradle.kotlin.dsl.support.unzipTo

plugins {
    id("net.freudasoft.gradle-cmake-plugin") version("0.0.2")
    id("de.undercouch.download") version("5.4.0")
}

val mainClassName = "Main"

dependencies {
    implementation(project(":box2d:base"))
    implementation("com.github.xpenatan.jParser:jParser-core:${LibExt.jParserVersion}")
    implementation("com.github.xpenatan.jParser:jParser-teavm:${LibExt.jParserVersion}")
    implementation("com.github.xpenatan.jParser:jParser-cpp:${LibExt.jParserVersion}")
    implementation("com.github.xpenatan.jParser:jParser-idl:${LibExt.jParserVersion}")
}

tasks.register<JavaExec>("build_project") {
    dependsOn("classes")
    group = "box2d"
    description = "Generate and build native project"
    mainClass.set(mainClassName)
    classpath = sourceSets["main"].runtimeClasspath
}

val zippedPath = "$buildDir/box2d-source.zip"
val sourcePath = "$buildDir/box2d-source"
val unzippedFolder = "$sourcePath/box2d-2.4.1/"
val sourceDestination = "$buildDir/box2d/box2d/"

cmake {
    generator.set("MinGW Makefiles")

    sourceFolder.set(file("$buildDir/box2d/"))

    buildConfig.set("Release")
    buildTarget.set("install")
    buildClean.set(true)
}

tasks.register("copy_emscripten_files") {
    copy {
        from("$projectDir/src/main/cpp/")
        into("$sourceDestination/../")
    }
}

tasks.register("build_emscripten") {
    dependsOn("copy_emscripten_files", "cmakeBuild")
    tasks.findByName("cmakeBuild")?.mustRunAfter("copy_emscripten_files")

    group = "box2d"
    description = "Generate javascript"

    doLast {
        copy{
            from(
                "$buildDir/cmake/box2d.js",
                "$buildDir/cmake/box2d.wasm.js"
            )
            into("$projectDir/../teavm/src/main/resources")
        }
    }
}

tasks.register<Download>("download_source") {
    group = "box2d"
    description = "Download box2d source"
    src("https://github.com/erincatto/box2d/archive/refs/tags/v2.4.1.zip")
    dest(File(zippedPath))
    doLast {
        unzipTo(File(sourcePath), dest)
        copy{
            from(unzippedFolder)
            into(sourceDestination)
        }
        delete(sourcePath)
        delete(zippedPath)
    }
}