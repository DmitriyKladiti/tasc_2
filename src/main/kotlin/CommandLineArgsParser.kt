import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option

// класс для разбора аргументов командной стоки
class CommandLineArgsParser(args: Array<String>) {
    @Option(name = "-l", usage = "List files in long format")
    var longFormat: Boolean = false

    @Option(name = "-h", usage = "List files in human-readable format")
    var humanReadableFormat: Boolean = false

    @Option(name = "-r", usage = "Change file sort order")
    var reverseSortOrder: Boolean = false

    @Option(name = "-o", usage = "Write list of files to an output file")
    var outputFileName: String? = null

    @Argument
    var dir: String? = null

    init {
        val parser = CmdLineParser(this)
        parser.parseArgument(*args)
        if (this.dir == null) {
            // если позиционный параметр 'dir' не задан, работаем с текущей директорией
            this.dir = "."
        }
    }
}