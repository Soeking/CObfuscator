import data.FunctionToken
import kotlinx.cinterop.CPointer
import obf.*
import platform.posix.*
import util.*

fun main(args: Array<String>) {
    val fileList = mutableListOf<CPointer<FILE>>()
    val outFileList = mutableListOf<CPointer<FILE>>()

    if (args.isEmpty()) {
        println("no file")
        return
    }

    if (fileCheck(args)) {
        args.forEach {
            fopen(it, "r")?.let { f ->
                fileList.add(f)
            }
            fopen(
                when (it.last()) {
                    in listOf('c', 'C') -> it.dropLast(2) + "_obf.c"
                    in listOf('h', 'H') -> it.dropLast(2) + "_obf.h"
                    else -> return
                },
                "w"
            )?.let { f ->
                outFileList.add(f)
            }
        }
    } else {
        println("not c file")
        return
    }

    val allTokenList = mutableListOf<MutableList<String>>()

    fileList.forEach {
        allTokenList.addAll(splitSpace(it))
    }

    allTokenList.forEach {
        functionList.add(FunctionToken(it))
    }

    if (allTokenList.count { it.count { t -> t == "main" } == 1 } == 1)
        recursiveMain()
    nameChane()
    functionList.forEach {
        if (it.isFunction) it.addBlock()
    }
    stringCheck()

    outFileList.forEach {
        writeFile(it)
    }

    (fileList + outFileList).forEach {
        fclose(it)
    }
}

fun fileCheck(list: Array<String>): Boolean {
    list.forEach {
        if (!it.endsWith(".c") && !it.endsWith(".C") && !it.endsWith(".h") && !it.endsWith(".H"))
            return false
    }
    return true
}
