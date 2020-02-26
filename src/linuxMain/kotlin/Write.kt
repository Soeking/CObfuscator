import data.TokenType
import kotlinx.cinterop.CPointer
import platform.posix.FILE
import platform.posix.fprintf
import util.defList
import util.functionList
import util.includeSet
import kotlin.random.Random

fun writeFile(out: CPointer<FILE>) {
    val rand = Random

    includeSet.shuffled().forEach {
        fprintf(out, "#include<%s.h>\n", it)
    }

    defList.forEach {
        fprintf(out, "%s\n", it)
    }

    functionList.forEach {
        it.tokenList.forEach { t ->
            fprintf(out, "%s", t.token)
            if (t.type == TokenType.TYPE || t.type == TokenType.CONTROL) fprintf(out, " ")
            if (rand.nextInt().rem(20) == 0) fprintf(out, "\n")
        }
    }
}