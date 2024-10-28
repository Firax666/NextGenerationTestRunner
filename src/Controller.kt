import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val classpath = System.getProperty("java.class.path") ?: throw IllegalStateException("Classpath is not set")

const val PROGRAM_A_NAME = "PseudoRngKt"
const val NUMBERS_COUNT = 100

class VerificationFailedException(message: String) : Exception(message)

fun main() {    var process: Process? = null

    try {

        val processBuilder = ProcessBuilder("java", "-cp", classpath, PROGRAM_A_NAME)
        process = processBuilder.start()

        val writer = BufferedWriter(OutputStreamWriter(process.outputStream))
        val reader = BufferedReader(InputStreamReader(process.inputStream))

        fun writeToProcess(input: String) {
            writer.write(input)
            writer.newLine()
            writer.flush()
        }

        writeToProcess("Hi")
        if (reader.readLine() == "Hi")
            println("Correct Response. PseudoRng program Verified.")
        else {
            throw VerificationFailedException("Wrong response. The pseudoRng program failed verification")
        }

        val randomNumbers: MutableList<Int> = ArrayList(100)
        repeat(NUMBERS_COUNT) {
            writeToProcess("GetRandom")
            val randomNumber = reader.readLine().toInt()
            randomNumbers.add(randomNumber)
        }

        writeToProcess("Shutdown")
        writer.close()
        reader.close()

        randomNumbers.sortWith( compareBy { it } )
        var sum = 0
        for (i in randomNumbers) {
            println(i)
            sum += i
        }

        val average = sum / NUMBERS_COUNT
        println("Average of numbers: $average")

        val median = if (randomNumbers.size % 2 == 1) {
            randomNumbers[NUMBERS_COUNT/2 + 1]
        } else {
            (randomNumbers[NUMBERS_COUNT/2] + randomNumbers[NUMBERS_COUNT/2 + 1]) / 2.0
        }
        println("Median of numbers: $median")
        process.waitFor()

    } catch (e: Exception) {
        process?.destroy()
        e.printStackTrace()
    }

}