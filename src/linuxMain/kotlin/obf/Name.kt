package obf

import data.TokenType
import platform.posix.abs
import util.*
import kotlin.random.Random

fun nameChane() {
    functionList.forEach {
        if (it.isFunction && it.name != "main") {
            var name = createName()
            while (functionNames.containsValue(name)) name = createName()
            functionNames[it.name] = name
        }
    }

    functionList.forEach {
        it.tokenList.forEach { t ->
            if (t.type == TokenType.FUNCTION && functionNames.containsKey(t.token))
                t.token = functionNames[t.token] ?: t.token
        }
    }
}

fun createName(): String {
    val length = abs(Random.nextInt() % 10) + 10
    var name = ""
    name += alphabets.random()
    while (name.length < length) name += nameChars.random()
    return name
}