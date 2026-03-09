package explain.it.im10.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import explain.it.im10.viewmodels.ExplanationViewModel

@Composable
fun AnswerUI(viewModel: ExplanationViewModel) {
    val clipboardManager = LocalClipboardManager.current
    if (viewModel.answer.isNotEmpty()) {
        Column {
            Row(
                horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Copy", color = Color.Cyan, modifier = Modifier.clickable {
                        clipboardManager.setText(AnnotatedString(viewModel.answer))
                    })
            }
            Spacer(Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E1E1E)
                ), shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(text = viewModel.answer, color = White)
                }
            }
        }
    } else {
        Text(
            text = "Ask me anything and I'll explain it like you're 10! 🤖",
            color = White.copy(0.6f),
            fontSize = 14.sp
        )
    }
}