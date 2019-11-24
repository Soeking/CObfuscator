package data

class Expression(l: Expression?, r: Expression?, o: String?, var value: Int) {
    val text: String

    init {
        value = calcValue(l?.value, r?.value, o) ?: value
        text = setText(l, r, o) ?: "$value"
    }

    private fun calcValue(l: Int?, r: Int?, o: String?): Int? {
        if (l == null || r == null) return null
        return when (o) {
            "+" -> l + r
            "-" -> l - r
            "*" -> l * r
            "/" -> l / r
            "%" -> l % r
            "&" -> l and r
            "|" -> l or r
            "^" -> l xor r
            else -> null
        }
    }

    private fun setText(l: Expression?, r: Expression?, o: String?): String? {
        if (l == null || r == null) return null
        return "( ${l.text} $o ${r.text} )"
    }
}