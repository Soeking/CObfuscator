import data.File
import data.FunctionToken
import kotlinx.cinterop.CPointer
import obf.*
import platform.posix.*
import util.*

fun main(args: Array<String>) {
    val fileList = mutableListOf<File>()

    if (args.isEmpty()) {
        println("no file")
        return
    }

    if (fileCheck(args)) {
        args.forEach {
            fileList.add(File(it))
        }
    } else {
        println("not c file")
        return
    }

    val allTokenList = mutableListOf<MutableList<String>>()

    fileList.forEach {
        it.setFunction(splitSpace(it))
    }

    if (allTokenList.count { it.count { t -> t == "main" } == 1 } == 1)
        recursiveMain()
    nameChange()
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
