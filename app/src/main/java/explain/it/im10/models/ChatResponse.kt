package explain.it.im10.models

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)