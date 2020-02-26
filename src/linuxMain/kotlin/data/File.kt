package data

import platform.posix.fopen

class File(val name: String) {
    val inFile = fopen(name, "w")
    val outFile = fopen(
        when (name.last()) {
            in listOf('c', 'C') -> name.dropLast(2) + "_obf.c"
            in listOf('h', 'H') -> name.dropLast(2) + "_obf.h"
            else -> null
        },
        "w"
    )
    val typeList = mutableListOf("int", "double", "long", "float", "void", "char")
    val includeSet = mutableSetOf("stdio", "math", "stdlib", "string", "stdarg", "float", "time")
    val headInclude = mutableListOf<String>()
    val defList = mutableListOf<String>()

    val functionList = mutableListOf<FunctionToken>()
    val functionNames = mutableMapOf<String, String>()
    val globalVars = mutableMapOf<String, String>()

    fun setFunction(allToken: List<List<String>>) {
        allToken.forEach {
            functionList.add(FunctionToken(it, this))
        }
    }
}