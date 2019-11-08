import kotlinx.cinterop.refTo
import kotlinx.cinterop.toKString
import platform.posix.*
import kotlin.random.Random

val alphabets = ('A'..'Z').map { it } + ('a'..'z').map { it }
val numbers = ('0'..'9').map { it }
val nameChars = alphabets + numbers

fun main(args: Array<String>) {
    val fileName = args[0]
    var fileLength = fileName.length
    if ((fileName[--fileLength] != 'c' && fileName[fileLength] != 'C') || fileName[--fileLength] != '.') {
        println("this is not c file")
        return
    }

    val file = fopen(fileName, "r")
    val out = fopen("out.c", "w")
    val bufferLength = 64 * 1024
    val buffer = ByteArray(1000)

    while (true) {
        var line = fgets(buffer.refTo(1), bufferLength, file)?.toKString() ?: break
        line = line.replace("\n", "")
        if (line.isEmpty()) continue
        if (line.first() == '#') fprintf(out, "%s\n", line)
        else {
            val words = line.split(" ").map { it }
            words.forEach {
                fprintf(out, "%s ", it)
                print(it)
            }
            println()
        }
    }

    fclose(file)
    fclose(out)
}

fun createName(): String {
    val random = Random
    val length = abs(random.nextInt() % 10) + 10
    var name = ""
    name += alphabets.random()
    while (name.length < length) name += nameChars.random()
    return name
}