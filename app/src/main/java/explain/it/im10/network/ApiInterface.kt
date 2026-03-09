package explain.it.im10.network

import explain.it.im10.models.ChatRequest
import explain.it.im10.models.ChatResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("chat/completions")
    suspend fun explain(@Body request: ChatRequest): ChatResponse
}