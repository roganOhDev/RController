group = "me.ohdonggeun"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

project(":main") {
    dependencies{
        project(":annotation")
    }
}
