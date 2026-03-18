package explain.it.im10.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import explain.it.im10.ui.composables.CustomButton
import explain.it.im10.ui.composables.InputField
import explain.it.im10.ui.composables.ResponseCard
import explain.it.im10.ui.composables.SuggestionsView
import explain.it.im10.viewmodels.ExplanationViewModel

@Composable
fun HomeView(
    viewModel: ExplanationViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var question by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(viewModel.displayedAnswer) {
        if (viewModel.displayedAnswer.isNotEmpty()) {
            listState.animateScrollToItem(listState.layoutInfo.totalItemsCount - 1)
        }
    }

    fun onAskQuestion() {
        keyboardController?.hide()
        viewModel.ask(question)
        question = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .imePadding()
    ) {

        // ── Content area ─────────────────────────────────────────
        if (viewModel.answer.isEmpty() && !viewModel.isLoading) {

            // ── Empty state ──────────────────────────────────────
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "🤖", fontSize = 48.sp)
                        Text(
                            text = "Explain Anything",
                            color = White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Ask any question and get a simple,\nclear explanation instantly.",
                            color = White.copy(alpha = 0.45f),
                            fontSize = 14.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            lineHeight = 20.sp
                        )
                    }
                    // Suggestions centered in empty state
                    SuggestionsView(onClick = { question = it; onAskQuestion() })
                }
            }

        } else {

            // ── Response area ────────────────────────────────────
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(top = 30.dp, bottom = 16.dp)
            ) {
                item {
                    Text(
                        text = "Explain Anything",
                        color = White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                item {
                    ResponseCard(viewModel)
                }
            }
        }

        // ── Suggestions above input — shown after answer ─────────
        // Gives quick follow-up prompts without cluttering the response area
        if (viewModel.isAnswered) {
            SuggestionsView(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Black)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = { question = it; onAskQuestion() }
            )
        }

        // ── Fixed input bar ──────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Black)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputField(
                input = question,
                onChange = { question = it },
                modifier = Modifier.weight(1f),
                onSend = {
                    if (question.isNotEmpty() && !viewModel.isLoading) onAskQuestion()
                }
            )
            CustomButton(
                isLoading = viewModel.isLoading,
                enabled = question.isNotEmpty() && !viewModel.isLoading,
                onClick = { onAskQuestion() }
            )
        }
    }
}