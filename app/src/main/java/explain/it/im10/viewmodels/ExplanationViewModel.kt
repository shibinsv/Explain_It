package explain.it.im10.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import explain.it.im10.repositories.ExplainRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExplanationViewModel @Inject constructor(
    private val repository: ExplainRepository
) : ViewModel() {

    var question by mutableStateOf("")
        private set

    var answer by mutableStateOf("")
        private set

    var isAnswered by mutableStateOf(false)
        private set

    // Streamed version of answer — animates character by character
    var displayedAnswer by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun ask(currentQuestion: String) {
        viewModelScope.launch {
            isLoading = true
            isAnswered = false        // reset on new question
            question = currentQuestion
            answer = ""
            displayedAnswer = ""

            val response = repository.explain(currentQuestion)
            answer = response
            isLoading = false
            isAnswered = true         // hide input after response

            streamAnswer(response)
        }
    }

    // Also add a reset so user can ask again
    fun reset() {
        question = ""
        answer = ""
        displayedAnswer = ""
        isAnswered = false
    }

    private suspend fun streamAnswer(fullText: String) {
        displayedAnswer = ""
        fullText.forEachIndexed { index, _ ->
            // Slightly faster for longer texts so it doesn't feel too slow
            val delayMs = when {
                fullText.length > 300 -> 8L
                fullText.length > 100 -> 15L
                else -> 25L
            }
            displayedAnswer = fullText.substring(0, index + 1)
            delay(delayMs)
        }
    }
}