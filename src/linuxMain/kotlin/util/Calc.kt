package util

import data.Expression
import kotlin.random.Random

fun createListOfExp(x: Int): List<String> {
    val list = mutableListOf<String>()

    val rand = Random
    var e = createExp()
    while (true) {
        if (e.value == x) break
        when (rand.nextInt(4)) {
            0 -> e = createExp(e, createExp())
            1 -> e = createExp(createExp(), e)
            2 -> e = createExp(e, createNumExp())
            3 -> e = createExp(createNumExp(), e)
        }
    }
    list.addAll(e.text.split(" "))
    if (list.size <= 13) return createListOfExp(x)
    return list
}

fun createRandom() = numbers.random() - '0'

fun createNumExp() = Expression(null, null, null, createRandom())

fun createExp(l: Expression = createNumExp(), r: Expression = createNumExp()): Expression {
    val opts = listOf("+", "-", "*", "/", "%", "&", "|", "^")
    var o = opts.random()
    while (r.value == 0 && (o == "/" || o == "%")) o = opts.random()
    return Expression(l, r, o, 0)
}
