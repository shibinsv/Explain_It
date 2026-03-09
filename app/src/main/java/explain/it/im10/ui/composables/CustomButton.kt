package explain.it.im10.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(isLoading: Boolean, onClick: () -> Unit, enabled: Boolean) {
    Button(
        modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
            containerColor = Magenta.copy(0.5f), disabledContainerColor = Color.DarkGray
        ), shape = RoundedCornerShape(10.dp), enabled = enabled, onClick = onClick
    ) {
        if (isLoading) {
            ThinkingAnimation()
        } else {
            Text("Explain", color = if (enabled) White else Color.Black.copy(0.5f))
        }

    }
}