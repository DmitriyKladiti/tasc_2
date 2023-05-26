import java.io.File
import java.text.SimpleDateFormat
// Класс-потомок java.io.File, который добавляет форматированный вывод информации о файле
class MyFile(pathname: String) : File(pathname) {

    // Возвращает представление файла в виде строки
    fun toFormattedString(humanReadable: Boolean, longFormat: Boolean): String {
        if (!longFormat) return this.name

        val canRead = if (this.canRead()) 1 else 0
        val canWrite = if (this.canWrite()) 1 else 0
        val canExecute = if (this.canExecute()) 1 else 0
        var perms = "${canRead}${canWrite}${canExecute}"
        var size = this.length().toString()
        if (humanReadable) {
            // приводим права и размер файла к человекочитаемому виду (111 -> rwx)
            perms = (if (canRead == 1) "r" else "-") +
                    (if (canWrite == 1) "w" else "-") +
                    (if (canExecute == 1) "x" else "-")
            size = this.formatSize(this.length())
        }

        val dateFormat = SimpleDateFormat("yyyy-dd-MM HH:mm:ss")
        val lastModifiedDate = dateFormat.format(this.lastModified())

        return "${perms}\t${lastModifiedDate}\t${this.formatSize(this.length())}\t${this.name}"
    }

    // Преобразует заданное число байт в человеко-читаемую строку
    private fun formatSize(bytes: Long): String {
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        var size = bytes.toDouble()
        var unitIndex = 0

        while (size >= 1024 && unitIndex < units.size - 1) {
            size /= 1024
            unitIndex++
        }

        return String.format("%.2f %s", size, units[unitIndex])
    }

    // Возвращает отсортированный массив файлов данной директории
    fun listFilesSorted(reverseOrder: Boolean = false): Array<MyFile>? {
        val files = super.list()  // массив с именами файлов в нашей папке
        if (files == null) return null

        if (reverseOrder) {
            files.sortDescending()
        } else {
            files.sort()
        }
        val res = mutableListOf<MyFile>()
        for (f in files) {
            res.add(MyFile(this.path + "/" + f))
        }
        return res.toTypedArray()
    }
}