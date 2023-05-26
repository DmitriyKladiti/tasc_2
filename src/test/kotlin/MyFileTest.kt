import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.io.path.*

class MyFileTest {
    @OptIn(ExperimentalPathApi::class)
    @Test
    fun testListFiles() {
        // создаем временную папку и кладем туда файл известоного размера
        val tmpdir = createTempDirectory()
        try {
            val fil = File(tmpdir.pathString + "/file.txt")
            fil.createNewFile()
            fil.writeText("test123")

            val myfile = MyFile(tmpdir.pathString)
            var files = myfile.listFilesSorted(false)
            val pattern = Regex("""^rw[-x]\s[-0-9: ]+\s7[,.]00\sB\sfile.txt${'$'}""")
            // проверяем, что этот файл правильно отображается при выводе
            assertEquals(files!!.size, 1)
            assertTrue(pattern.matches(files[0].toFormattedString(true, true)))
        }
        finally {
            // всегда удаляем временную папку в конце
            tmpdir.deleteRecursively()
        }
    }
}