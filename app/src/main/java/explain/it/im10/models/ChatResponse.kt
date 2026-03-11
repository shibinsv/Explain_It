package explain.it.im10.models

import com.google.gson.annotations.SerializedName

data class ChatResponse(
    val id: String,
    val choices: List<Choice>,
    val usage: Usage?,
    @SerializedName("created")
    val createdAt: Long?,
    val model: String?
)

data class Choice(
    val index: Int,
    val message: Message,
    @SerializedName("finish_reason")
    val finishReason: String?
)

data class Usage(
    @SerializedName("prompt_tokens")
    val promptTokens: Int,
    @SerializedName("completion_tokens")
    val completionTokens: Int,
    @SerializedName("total_tokens")
    val totalTokens: Int
)