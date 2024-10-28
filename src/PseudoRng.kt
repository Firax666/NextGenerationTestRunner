import kotlin.random.Random

fun main() {

    while (true) {
        val input = readlnOrNull()
        when(input) {
            "Hi" -> println("Hi")
            "GetRandom" -> println(Random.nextInt())
            "Shutdown" -> break
        }
    }

}