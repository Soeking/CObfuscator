import data.TokenType
import kotlinx.cinterop.CPointer
import platform.posix.FILE
import platform.posix.fprintf
import util.functionList

fun writeFile(out: CPointer<FILE>) {
    functionList.forEach {
        it.tokenList.forEach { t ->
            fprintf(out, "%s", t.token)
            if (t.type == TokenType.TYPE || t.type == TokenType.CONTROL) fprintf(out, " ")
        }
        fprintf(out, "\n")
    }
}