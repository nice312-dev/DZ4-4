object WallService {
    private var posts = emptyArray<Post>()
    var comments = emptyArray<Comment>()
    var reports = emptyArray<Report>()

    fun createComment(comment: Comment): Comment {
        val postId = comment.postId;

        for (post in posts) {
            if(post.id == postId) {
                val commentId = (comments.size + 1).toLong()

                comments += comment.copy(id = commentId)
                return comments.last()
            }
        }

        throw PostNotFoundException("Post not found: $postId")
    }

    fun createReport(comment: Comment, reason: Int): Report {
        for (_comment in comments) {
            if (_comment.id == comment.id) {
                val reportId = (reports.size + 1).toLong()
                val report = Report(reportId, comment.fromId, comment.id, reason)

                reports += report
                return reports.last()
            }
        }

        throw CommentNotFoundException("Comment not found: ${comment.id}")
    }

    fun add(post: Post): Post {
        val count = posts.size + 1L
        val item = post.copy(id = count)

        posts += item
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, existPost) in posts.withIndex()) {
            if (existPost.id == post.id) {
                posts[index] = post.copy(id = existPost.id, ownerId = existPost.ownerId, createdBy = existPost.createdBy)
                return true
            }
        }

        return false
    }
}

class CommentNotFoundException(message: String) : Exception(message)

class PostNotFoundException(message: String) : Exception(message)