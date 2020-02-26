import data.File
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.refTo
import kotlinx.cinterop.toKString
import platform.posix.FILE
import platform.posix.fgets
import util.*

fun splitSpace(file: File): MutableList<MutableList<String>> {
    val bufferLength = 64 * 1024
    val buffer = ByteArray(1000)
    val functionList = mutableListOf<MutableList<String>>()
    var blockCount = 0
    var isNotBlockComment = true

    while (true) {
        var line = fgets(buffer.refTo(1), bufferLength, file.inFile)?.toKString() ?: break
        line = line.replace("\n", "")
        if (line.isEmpty()) continue
        if (line.first() == '#') {
            if (line.startsWith("#include")) {
                if (line.last()=='>') file.includeSet.add(line.dropWhile { it != '<' && it != '\"' }.drop(1).dropLast(3))
                else if (line.last()=='\"') file.includeSet.add(line.dropWhile { it != '<' && it != '\"' }.drop(1).dropLast(3))
            }
            else file.defList.add(line)
        } else {
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
    var opt = false
    word.forEach {
        if (it == '"') {
            if (string) {
                tokens.add(token + it)
                token = ""
                string = false
            } else {
                if (token.isNotEmpty()) tokens.add(token)
                token = "$it"
                string = true
            }
            opt = false
        } else if (nameChars.contains(it) || it == '.' || it == '\\' || string) {
            if (opt) {
                opt = false
                tokens.add(token)
                token = ""
            }
            token += it
        } else if (optionList.contains(it)) {
            if (opt) {
                opt = false
                token += it
                tokens.add(token)
                token = ""
            } else {
                opt = true
                tokens.add(token)
                token = it.toString()
            }
        } else {
            if (token.isNotEmpty()) tokens.add(token)
            tokens.add(it.toString())
            token = ""
            opt = false
        }
    }
    if (token.isNotEmpty()) tokens.add(token)
    return tokens
}
