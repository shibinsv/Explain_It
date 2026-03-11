package explain.it.im10.models

data class ChatRequest(
    val model: String = "deepseek-chat",
    val messages: List<Message>,
    val stream: Boolean = false
)
