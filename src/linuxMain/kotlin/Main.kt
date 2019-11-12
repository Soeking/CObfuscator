import kotlinx.cinterop.refTo
import kotlinx.cinterop.toKString
import platform.posix.*
import kotlin.random.Random

val alphabets = ('A'..'Z').map { it } + ('a'..'z').map { it }
val numbers = ('0'..'9').map { it }
val nameChars = alphabets + numbers
val functionNames = mutableSetOf<String>()

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

    val functionList = mutableListOf<MutableList<String>>()
    var blockCount = 0
    var isNotBlockComment = true

    while (true) {
        var line = fgets(buffer.refTo(1), bufferLength, file)?.toKString() ?: break
        line = line.replace("\n", "")
        if (line.isEmpty()) continue
        if (line.first() == '#') fprintf(out, "%s\n", line)
        else {
            if (blockCount == 0) {
                functionList.add(mutableListOf())
                println()
            }
            blockCount += line.count { it == '{' } - line.count { it == '}' }
            val words = line.split(" ").map { it }
            var isNotIndent = false
            var isNotLineComment = true
            words.forEach {
                if (it.isNotEmpty()) isNotIndent = true
                if (it.length >= 2 && it.startsWith("//")) isNotLineComment = false
                if (it.length >= 2 && it.startsWith("/*")) isNotBlockComment = false
                if (isNotIndent && isNotBlockComment && isNotLineComment) functionList.last().add(it)
                if (it.length >= 2 && it.endsWith("*/")) isNotBlockComment = true
                print(it)
            }
        }
    }
    println()

    fclose(file)
    fclose(out)
}

fun createName(): String {
    val length = abs(Random.nextInt() % 10) + 10
    var name = ""
    name += alphabets.random()
    while (name.length < length) name += nameChars.random()
    return name
}