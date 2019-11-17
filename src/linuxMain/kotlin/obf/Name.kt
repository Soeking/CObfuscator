package obf

import platform.posix.abs
import util.*
import kotlin.random.Random

fun createName(): String {
    val length = abs(Random.nextInt() % 10) + 10
    var name = ""
    name += alphabets.random()
    while (name.length < length) name += nameChars.random()
    return name
}