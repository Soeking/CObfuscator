import data.FunctionToken
import obf.*
import platform.posix.*
import util.*

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("no file")
        return
    }
    val fileName = args[0]
    if (!fileName.endsWith(".c") && !fileName.endsWith(".C")) {
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

    functionList.forEach {
        if (it.isFunction) it.addBlock()
    }
    if (allTokenList.count { it.count { t -> t == "main" } == 1 } == 1)
        recursiveMain()
    nameChane()

    writeFile(out)

    fclose(file)
    fclose(out)
}
