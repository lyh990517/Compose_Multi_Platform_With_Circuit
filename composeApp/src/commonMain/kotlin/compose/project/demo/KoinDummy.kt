package compose.project.demo

class KoinDummy {
    fun get() = "hello world: ${getPlatform().name}"
}