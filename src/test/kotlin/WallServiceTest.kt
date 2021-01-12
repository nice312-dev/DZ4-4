import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun createReport_comment_found() {
        val post = Post(
            id = 0L,
            ownerId = 50,
            fromId = 50,
            createdBy = 50,
            date = 20201225,
            text = "post text",
            donut = Donut()
        )
        val postId = WallService.add(post).id

        val comment = Comment(
            id = 0L,
            postId = postId,
            fromId = 10,
            text = "comment text",
            date = 20201225
        )

        val addedComment = WallService.createComment(comment)

        val expected = addedComment.id
        val actual = WallService.createReport(comment = addedComment, reason = 1).commentId

        assertEquals("Ожидалось, что в рапорте о нарушении будет существующий комментарий", expected, actual)
    }

    @Test(expected = CommentNotFoundException::class)
    fun createReport_comment_not_found() {
        val post = Post(
            id = 0L,
            ownerId = 50,
            fromId = 50,
            createdBy = 50,
            date = 20201225,
            text = "post text",
            donut = Donut()
        )
        val postId = WallService.add(post).id

        val comment = Comment(
            id = 0L,
            postId = postId,
            fromId = 10,
            text = "comment text",
            date = 20201225
        )

        WallService.createReport(comment = comment, reason = 1)
    }

    @Test
    fun createComment_post_found() {
        val post = Post(
            id = 0L,
            ownerId = 50,
            fromId = 50,
            createdBy = 50,
            date = 20201225,
            text = "post text",
            donut = Donut()
        )

        val id = WallService.add(post).id

        val comment = Comment(
            id = 0L,
            postId = id,
            fromId = 10,
            text = "comment text",
            date = 20201225
        )

        WallService.createComment(comment)
    }

    @Test(expected = PostNotFoundException::class)
    fun createComment_post_not_found() {
        val comment = Comment(
            id = 0L,
            postId = 0,
            fromId = 10,
            text = "comment text",
            date = 20201225
        )

        WallService.createComment(comment)
    }

    @Test
    fun add_post_id_more_then_0() {
        val post = Post(
            id = 0L,
            ownerId = 10,
            fromId = 10,
            createdBy = 10,
            date = 20201217,
            text = "post text",
            donut = Donut()
        )

        val actual = WallService.add(post).id

        assertTrue("Ожидалось, что добавленное сообщение будет иметь id отличный от 0.", actual > 0L)
    }

    @Test
    fun update_post_exists() {
        val post = Post(
            id = 0L,
            ownerId = 20,
            fromId = 20,
            createdBy = 20,
            date = 20201217,
            text = "some text",
            donut = Donut()
        )

        val addedPost = WallService.add(post)

        assertTrue("Ожидалось, что произойдёт редактирование существующего сообщения",
            WallService.update(addedPost))
    }

    @Test
    fun update_post_not_exists() {
        val addedPost = Post(
            id = 0L,
            ownerId = 30,
            fromId = 30,
            createdBy = 30,
            date = 20201217,
            text = "some text",
            donut = Donut()
        )

        val passedPost = Post(
            id = 0L,
            ownerId = 40,
            fromId = 40,
            createdBy = 40,
            date = 20201217,
            text = "some text",
            donut = Donut()
        )

        WallService.add(addedPost)

        assertFalse("Ожидалось, что редактирования несуществующего сообщения не произойдёт.",
            WallService.update(passedPost))
    }
}