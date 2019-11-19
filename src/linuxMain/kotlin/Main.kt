import data.FunctionToken
import data.TokenType
import platform.posix.*
import util.*

fun main(args: Array<String>) {
    val fileName = args[0]
    var fileLength = fileName.length
    if ((fileName[--fileLength] != 'c' && fileName[fileLength] != 'C') || fileName[--fileLength] != '.') {
        println("this is not c file")
        return
    }
    var outName = fileName.dropLast(2)
    outName += "_obf.c"

    val file = fopen(fileName, "r")
    val out = fopen(outName, "w")
    val allTokenList = mutableListOf<MutableList<String>>()

    if (file != null && out != null) allTokenList.addAll(splitSpace(file, out))

    allTokenList.forEach {
        functionList.add(FunctionToken(it))
    }

    functionList.forEach {
        it.tokenList.forEach { t ->
            fprintf(out, "%s", t.token)
            if (t.type == TokenType.TYPE || t.type == TokenType.CONTROL) fprintf(out, " ")
        }
        fprintf(out, "\n")
    }

    fclose(file)
    fclose(out)
}
