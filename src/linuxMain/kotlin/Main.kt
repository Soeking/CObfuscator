import data.FunctionToken
import data.TokenType
import obf.*
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
    else {
        println("file not found")
        return
    }

    allTokenList.forEach {
        functionList.add(FunctionToken(it))
    }

    nameChane()

    writeFile(out)

    fclose(file)
    fclose(out)
}
