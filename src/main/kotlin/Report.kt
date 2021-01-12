class Report (
    val id: Long,
    val ownerId: Long,
    val commentId: Long,
    reasonId: Int = 1
) {
    var reasonId: Int = if (reasonId in 1..3) reasonId else throw ReasonNotFoundException("Reason not correct")
}

class ReasonNotFoundException(message: String) : Exception(message)