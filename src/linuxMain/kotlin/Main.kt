import kotlinx.cinterop.CPointer
import kotlinx.cinterop.refTo
import kotlinx.cinterop.toKString
import platform.posix.*
import kotlin.random.Random

fun main(args: Array<String>) {
    val fileName = args[0]
    var fileLength = fileName.length
    if ((fileName[--fileLength] != 'c' && fileName[fileLength] != 'C') || fileName[--fileLength] != '.') {
        println("this is not c file")
        return
    }

    val file = fopen(fileName, "r")
    val out = fopen("out.c", "w")
    val functionList = mutableListOf<MutableList<String>>()

    if (file != null && out != null) functionList.addAll(splitSpace(file, out))

    fclose(file)
    fclose(out)
}

fun splitSpace(file: CPointer<FILE>, out: CPointer<FILE>): MutableList<MutableList<String>> {
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
            if (blockCount == 0) functionList.add(mutableListOf())
            blockCount += line.count { it == '{' } - line.count { it == '}' }
            val words = line.split(" ").map { it }
            var isNotIndent = false
            var isNotLineComment = true
            var string = ""
            var isString = false
            words.forEach {
                if (it.isNotEmpty()) isNotIndent = true
                if (it.length >= 2 && it.startsWith("//")) isNotLineComment = false
                else if (it.length >= 2 && it.startsWith("/*")) isNotBlockComment = false
                else if (it.count { c -> c == '"' } == 1) {
                    if (isString) {
                        string += " $it"
                        isString = false
                        functionList.last().addAll(splitToken(string))
                    } else {
                        string += it
                        isString = true
                    }
                } else if (isString) string += " $it"
                else if (isNotIndent && isNotBlockComment && isNotLineComment && !isString)
                    functionList.last().addAll(splitToken(it))
                if (it.length >= 2 && it.endsWith("*/")) isNotBlockComment = true
            }
        }
    }
    return functionList
}

fun splitToken(word: String): MutableList<String> {
    val tokens = mutableListOf<String>()
    var token = ""
    var string = false
    word.forEach {
        if (nameChars.contains(it) || it == '.' || it == ' ' || it == '\\') {
            token += it
        } else if (it == '"') {
            if (string) {
                tokens.add(token + it)
                token = ""
                string = false
            } else {
                if (token.isNotEmpty()) tokens.add(token)
                token = "$it"
                string = true
            }
        } else {
            if (token.isNotEmpty()) tokens.add(token)
            tokens.add(it.toString())
            token = ""
        }
    }
    if (token.isNotEmpty()) tokens.add(token)
    return tokens
}

fun createName(): String {
    val length = abs(Random.nextInt() % 10) + 10
    var name = ""
    name += alphabets.random()
    while (name.length < length) name += nameChars.random()
    return name
}