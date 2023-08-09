include(":box2d:core")
include(":box2d:base")
include(":box2d:generator")
include(":box2d:teavm")
include(":box2d:desktop")

include(":examples:basic:core")
include(":examples:basic:desktop")
include(":examples:basic:teavm")

//includeBuild("E:\\Dev\\Projects\\java\\jParser") {
//    dependencySubstitution {
//        substitute(module("com.github.xpenatan.jParser:jParser-base")).using(project(":jParser:base"))
//        substitute(module("com.github.xpenatan.jParser:jParser-core")).using(project(":jParser:core"))
//        substitute(module("com.github.xpenatan.jParser:jParser-cpp")).using(project(":jParser:cpp"))
//        substitute(module("com.github.xpenatan.jParser:jParser-idl")).using(project(":jParser:idl"))
//        substitute(module("com.github.xpenatan.jParser:loader-core")).using(project(":jParser:loader:loader-core"))
//        substitute(module("com.github.xpenatan.jParser:loader-teavm")).using(project(":jParser:loader:loader-teavm"))
//        substitute(module("com.github.xpenatan.jParser:jParser-teavm")).using(project(":jParser:teavm"))
//    }
//}

// #########