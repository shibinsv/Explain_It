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
    var answer by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun ask(question: String) {
        viewModelScope.launch {
            isLoading = true
            answer = ""

            delay(5000)
            // API call
//            val response = repository.explain(question)

            answer = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            isLoading = false
        }

    }
}