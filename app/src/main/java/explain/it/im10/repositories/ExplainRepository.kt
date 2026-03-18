package explain.it.im10.repositories

import explain.it.im10.BuildConfig
import explain.it.im10.models.ChatRequest
import explain.it.im10.models.Message
import explain.it.im10.network.ApiInterface
import javax.inject.Inject

class ExplainRepository @Inject constructor(
    private val api: ApiInterface
) {

    companion object {
        private const val SYSTEM_MESSAGE =
            "You are a helpful assistant that explains complex topics simply and clearly to children."

        private const val USER_MESSAGE_PREFIX =
            "Explain like I'm 10 years old in simple words with examples: "
    }

    suspend fun explain(question: String): String {
        return try {

            val messages = listOf(
                Message(role = "system", content = SYSTEM_MESSAGE),
                Message(role = "user", content = USER_MESSAGE_PREFIX + question)
            )

            val request = ChatRequest(
                model = "deepseek-chat",
                messages = messages,
                stream = false
            )

            val apiKey = "Bearer ${BuildConfig.DEEPSEEK_API_KEY}"

            val response = api.getChatCompletion(apiKey, request)

            if (response.isSuccessful) {
                response.body()?.choices?.firstOrNull()?.message?.content
                    ?: "No explanation received."
            } else {
                response.errorBody()?.string() ?: "API error"
            }

        } catch (e: Exception) {
            e.printStackTrace()
            "Something went wrong: ${e.message}"
        }
    }}