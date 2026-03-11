package explain.it.im10.repositories

import explain.it.im10.BuildConfig
import explain.it.im10.models.ChatRequest
import explain.it.im10.models.Message
import explain.it.im10.network.ApiInterface
import javax.inject.Inject

class ExplainRepository @Inject constructor(
    private val api: ApiInterface
) {

    suspend fun explain(question: String): String {
        return try {

            val messages = listOf(
                Message(
                    role = "system",
                    content = "You are a helpful assistant that explains complex topics simply."
                ),
                Message(
                    role = "user",
                    content = "Explain like I'm 10 years old: $question"
                )
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