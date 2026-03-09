package explain.it.im10.repositories

import explain.it.im10.models.ChatRequest
import explain.it.im10.models.Message
import explain.it.im10.network.ApiInterface
import javax.inject.Inject

class ExplainRepository @Inject constructor(
    private val api: ApiInterface
) {

    suspend fun explain(question: String): String {

        val request = ChatRequest(
            messages = listOf(
                Message(
                    role = "user",
                    content = "Explain like I'm 10 years old: $question"
                )
            )
        )

        val response = api.explain(request)

        return response.choices.first().message.content
    }
}