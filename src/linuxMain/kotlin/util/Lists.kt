package util

import data.FunctionToken

val alphabets = ('A'..'Z').map { it } + ('a'..'z').map { it }
val numbers = ('0'..'9').map { it }
val nameChars = alphabets + numbers + '_'
val typeList = mutableListOf("int", "double", "long", "float", "void", "char")
val controlList = listOf("for", "if", "while", "else", "do", "return", "goto", "typedef", "struct")
val bracketList = listOf('{', '}', '(', ')', '[', ']', ';', ',')
val optionList = listOf('+', '-', '*', '/', '%', '=', '&', '|', '^', '<', '>')
val includeSet = mutableSetOf("stdio", "math", "stdlib", "string", "stdarg", "float", "time")
val defList = mutableListOf<String>()

val functionList = mutableListOf<FunctionToken>()
val functionNames = mutableMapOf<String, String>()
val globalVars = mutableMapOf<String, String>()
