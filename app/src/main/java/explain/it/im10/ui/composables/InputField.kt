package explain.it.im10.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputField(input: String, onChange: (String) -> Unit) {
    val maxChars = 50

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Your Question", color = White.copy(0.7f), fontSize = 16.sp
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = "${input.length} / $maxChars",
            color = Color.Gray
        )
        if (input.isNotEmpty()) {
            Spacer(Modifier.width(20.dp))
            Text(
                text = "Clear", color = Magenta, fontSize = 14.sp, modifier = Modifier.clickable {
                    onChange("")
                })
        }
    }
    TextField(
        value = input,
        onValueChange = {
            if (it.length <= maxChars) {
                onChange(it)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp)),
    )
}