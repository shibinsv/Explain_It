package explain.it.im10.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shibin.spendly.ui.theme.AccentBlue
import com.shibin.spendly.ui.theme.BgField
import com.shibin.spendly.ui.theme.BorderColor
import com.shibin.spendly.ui.theme.TextMuted
import com.shibin.spendly.ui.theme.TextWhite


@Composable
fun InputField(
    input: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,      // added so HomeView can pass weight(1f)
    onSend: (() -> Unit)? = null        // optional — lets user submit via keyboard
) {
    val maxChars = 150                  // increased from 50 — questions can be longer
    val isNearLimit = input.length >= maxChars - 20

    OutlinedTextField(
        value = input,
        onValueChange = { if (it.length <= maxChars) onChange(it) },
        modifier = modifier
            .clip(RoundedCornerShape(14.dp)),
        placeholder = {
            Text(
                "Ask anything...",
                color = TextMuted,
                fontSize = 14.sp
            )
        },
        // char counter as suffix
        suffix = {
            Text(
                text = "${input.length}/$maxChars",
                fontSize = 11.sp,
                color = if (isNearLimit) AccentBlue else TextMuted
            )
        },
        trailingIcon = {
            // Clear button inside field
            if (input.isNotEmpty()) {
                Text(
                    text = "✕",
                    color = TextMuted,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .clickable { onChange("") }
                        .padding(end = 4.dp)
                )
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
        keyboardActions = KeyboardActions(
            onSend = { onSend?.invoke() }
        ),
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = TextWhite,
            unfocusedTextColor = TextWhite,
            focusedBorderColor = AccentBlue.copy(alpha = 0.5f),
            unfocusedBorderColor = BorderColor,
            cursorColor = AccentBlue,
            focusedContainerColor = BgField,
            unfocusedContainerColor = BgField,
        ),
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = TextWhite
        ),
        singleLine = true
    )
}