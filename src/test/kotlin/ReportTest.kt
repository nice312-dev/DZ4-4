import org.junit.Assert.*
import org.junit.Test

class ReportTest {
    @Test
    fun report_correct_reason() {
        val expected = 1
        val actual = Report(id = 0L, ownerId = 0L, commentId = 0L, reasonId = 1).reasonId

        assertEquals("Ожидалось, что будет создан рапорт", expected, actual)
    }

    @Test(expected = ReasonNotFoundException::class)
    fun report_incorrect_reason() {
        Report(id = 0L, ownerId = 0L, commentId = 0L, reasonId = 0)
    }
}