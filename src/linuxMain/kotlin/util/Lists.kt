package util

import data.FunctionToken

val alphabets = ('A'..'Z').map { it } + ('a'..'z').map { it }
val numbers = ('0'..'9').map { it }
val nameChars = alphabets + numbers
val typeList = mutableListOf("int", "double", "struct", "long", "float", "void", "char", "typedef")
val controlList = listOf("for", "if", "while", "else", "do", "return", "goto")
val bracketList = listOf('{', '}', '(', ')', '[', ']', ';', ',')

val functionList = mutableListOf<FunctionToken>()
val functionNames = mutableMapOf<String, String>()
