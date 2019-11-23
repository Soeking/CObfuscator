package util

import data.FunctionToken

val alphabets = ('A'..'Z').map { it } + ('a'..'z').map { it }
val numbers = ('0'..'9').map { it }
val nameChars = alphabets + numbers + '_'
val typeList = mutableListOf("int", "double", "struct", "long", "float", "void", "char")
val controlList = listOf("for", "if", "while", "else", "do", "return", "goto", "typedef")
val bracketList = listOf('{', '}', '(', ')', '[', ']', ';', ',')

val functionList = mutableListOf<FunctionToken>()
val functionNames = mutableMapOf<String, String>()
val globalVars = mutableMapOf<String, String>()
