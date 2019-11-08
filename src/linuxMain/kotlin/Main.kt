import kotlinx.cinterop.refTo
import kotlinx.cinterop.toKString
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen
import platform.posix.fprintf

fun main(args: Array<String>) {
    val fileName = args[0]
    var fileLength = fileName.length
    if ((fileName[--fileLength] != 'c' && fileName[fileLength] != 'C') || fileName[--fileLength] != '.') {
        println("this is not c file")
        return
    }
    val file = fopen(fileName, "r")
    val out = fopen("out.c", "w")
    val bufferLength = 64 * 1024
    val buffer = ByteArray(1000)
    while (true) {
        var line = fgets(buffer.refTo(1), bufferLength, file)?.toKString() ?: break
        line = line.replace("\n", "")
        if (line.isEmpty()) continue
        if (line[0] == '#') fprintf(out, "%s\n", line)
        else {
            val words = line.split(" ").map { it }
            words.forEach {
                fprintf(out,"%s ",it)
                print("$it,")
            }
            println()
        }
    }
    fclose(file)
    fclose(out)
}