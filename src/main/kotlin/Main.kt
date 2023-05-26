import java.io.File

// -l -h -r -o output.txt
fun main(args: Array<String>) {
    val cmdArgs = CommandLineArgsParser(args)
    val givenFile = MyFile(cmdArgs.dir!!)
    var files = givenFile.listFilesSorted(cmdArgs.reverseSortOrder)
    // Если метод вернул null, то переданный аргумент - не папка. Обернем его в массив для единообразия.
    if (files == null) files = arrayOf(givenFile)

    if (cmdArgs.outputFileName == null) {
        // печать на экран
        printToScreen(files, cmdArgs.humanReadableFormat, cmdArgs.longFormat)
    } else {
        // вывод в файл
        printToFile(files, cmdArgs.humanReadableFormat, cmdArgs.longFormat, cmdArgs.outputFileName!!)
    }
}

// печать данного списка файлов на экран
fun printToScreen(files: Array<MyFile>, humanReadable: Boolean, longFormat: Boolean) {
    for (file in files) {
        println(file.toFormattedString(humanReadable, longFormat))
    }
}

// вывод данного списка файлов в файл
fun printToFile(files: Array<MyFile>, humanReadable: Boolean, longFormat: Boolean, outFile: String) {
    val writer = File(outFile).printWriter()
    for (file in files) {
        writer.println(file.toFormattedString(humanReadable, longFormat))
    }
    writer.close()
}