val alphabets = ('A'..'Z').map { it } + ('a'..'z').map { it }
val numbers = ('0'..'9').map { it }
val nameChars = alphabets + numbers
val typeList = listOf("int", "double", "struct", "long", "float", "void", "char")
val functionNames = mutableSetOf<String>()
