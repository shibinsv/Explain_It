package explain.it.im10.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import explain.it.im10.ui.composables.AnswerUI
import explain.it.im10.ui.composables.CustomButton
import explain.it.im10.ui.composables.InputField
import explain.it.im10.ui.composables.SuggestionsView
import explain.it.im10.viewmodels.ExplanationViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeView(
    viewModel: ExplanationViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var question by remember { mutableStateOf("") }

    fun onAskQuestion() {
        keyboardController?.hide()
        viewModel.ask(question)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "Explain Anything", color = White, fontSize = 28.sp)
        SuggestionsView(onClick = {
            question = it; onAskQuestion()
        })
        InputField(input = question, onChange = { question = it })
        CustomButton(isLoading = viewModel.isLoading, enabled = question.isNotEmpty(), onClick = {
            onAskQuestion()
        })
        AnswerUI(viewModel)
    }
}