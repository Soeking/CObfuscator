package util

import data.FunctionToken

val alphabets = ('A'..'Z').map { it } + ('a'..'z').map { it }
val numbers = ('0'..'9').map { it }
val nameChars = alphabets + numbers + '_'
val controlList = listOf("for", "if", "while", "else", "do", "return", "goto", "typedef", "struct", "const")
val bracketList = listOf('{', '}', '(', ')', '[', ']')
val optionList = listOf('+', '-', '*', '/', '%', '=', '&', '|', '^', '<', '>')
