package explain.it.im10.ui.composables

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun CustomButton(isLoading: Boolean, onClick: () -> Unit, enabled: Boolean) {
    Button(
        modifier = Modifier.wrapContentWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Magenta.copy(0.5f),
            disabledContainerColor = Color.DarkGray
        ),
        shape = RoundedCornerShape(10.dp),
        enabled = enabled && !isLoading,
        onClick = onClick
    ) {
        if (isLoading) {
            // Compact dots instead of tall multi-line animation
            DotsAnimation()
        } else {
            Text("Explain", color = if (enabled) White else Color.Black.copy(0.5f))
        }
    }
}

@Composable
private fun DotsAnimation() {
    var dotCount by remember { mutableStateOf(1) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(400)
            dotCount = if (dotCount == 3) 1 else dotCount + 1
        }
    }
    Text(
        text = ".".repeat(dotCount),
        color = White,
        modifier = Modifier.width(24.dp)   // fixed width so button doesn't resize
    )
}