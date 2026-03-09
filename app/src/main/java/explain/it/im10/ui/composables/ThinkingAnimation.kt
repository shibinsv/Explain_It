package explain.it.im10.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ThinkingAnimation() {

    val steps = listOf(
        "🤔 Understanding question...",
        "🔍 Searching knowledge...",
        "📚 Gathering information...",
        "🧠 Analyzing data...",
        "⚙️ Processing results...",
        "💡 Generating explanation...",
        "✍️ Preparing answer..."
    )

    var index by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            if (index < steps.lastIndex) {
                index++
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = steps[index], color = White)

        Spacer(modifier = Modifier.height(10.dp))
    }
}