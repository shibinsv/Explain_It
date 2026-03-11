package explain.it.im10.models

data class Message(
    val role: String,  // "system", "user", or "assistant"
    val content: String
)