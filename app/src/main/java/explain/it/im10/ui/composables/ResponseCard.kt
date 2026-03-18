package explain.it.im10.ui.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shibin.spendly.ui.theme.AccentBlue
import com.shibin.spendly.ui.theme.BgAnswer
import com.shibin.spendly.ui.theme.BorderColor
import com.shibin.spendly.ui.theme.TextMuted
import com.shibin.spendly.ui.theme.TextWhite
import explain.it.im10.viewmodels.ExplanationViewModel


@Composable
fun ResponseCard(viewModel: ExplanationViewModel) {
    val clipboardManager = LocalClipboardManager.current

    // True while the answer is still streaming (displayed < full)
    val isStreaming = viewModel.displayedAnswer.length < viewModel.answer.length
            && viewModel.answer.isNotEmpty()

    if (viewModel.answer.isNotEmpty() || viewModel.displayedAnswer.isNotEmpty()) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            // ── Question bubble ──────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .widthIn(max = 280.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 16.dp, topEnd = 4.dp,
                                bottomStart = 16.dp, bottomEnd = 16.dp
                            )
                        )
                        .background(AccentBlue.copy(alpha = 0.2f))
                        .border(
                            1.dp,
                            AccentBlue.copy(alpha = 0.35f),
                            RoundedCornerShape(
                                topStart = 16.dp, topEnd = 4.dp,
                                bottomStart = 16.dp, bottomEnd = 16.dp
                            )
                        )
                        .padding(horizontal = 14.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = viewModel.question,
                        color = TextWhite,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // ── Answer bubble ────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 4.dp, topEnd = 16.dp,
                            bottomStart = 16.dp, bottomEnd = 16.dp
                        )
                    )
                    .background(BgAnswer)
                    .border(
                        1.dp,
                        BorderColor,
                        RoundedCornerShape(
                            topStart = 4.dp, topEnd = 16.dp,
                            bottomStart = 16.dp, bottomEnd = 16.dp
                        )
                    )
                    .padding(16.dp)
            ) {
                // ── "Explain It" label ───────────────────────────
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(AccentBlue)
                    )
                    Text(
                        text = "Explain It",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AccentBlue,
                        letterSpacing = 0.06.sp
                    )
                }

                // ── Streamed answer text + blinking cursor ───────
                val scrollState = rememberScrollState()

                // Auto-scroll to bottom as text streams in
                LaunchedEffect(viewModel.displayedAnswer) {
                    scrollState.animateScrollTo(scrollState.maxValue)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 320.dp)
                        .verticalScroll(scrollState)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append(viewModel.displayedAnswer)
                            // Blinking cursor shown only while streaming
                            if (isStreaming) {
                                withStyle(SpanStyle(color = AccentBlue, fontWeight = FontWeight.Bold)) {
                                    append(cursorChar())
                                }
                            }
                        },
                        color = TextWhite,
                        fontSize = 14.sp,
                        lineHeight = 22.sp
                    )
                }

                // ── Copy button — only shown when streaming is done ──
                if (!isStreaming && viewModel.displayedAnswer.isNotEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Copy",
                            color = AccentBlue,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.clickable {
                                clipboardManager.setText(AnnotatedString(viewModel.answer))
                            }
                        )
                    }
                }
            }
        }
    } else {
        // ── Empty state ──────────────────────────────────────────
        Text(
            text = "Ask me anything and I'll explain it like you're 10! 🤖",
            color = TextMuted,
            fontSize = 14.sp
        )
    }
}

// ── Blinking cursor composable ───────────────────────────────────────────────
@Composable
private fun cursorChar(): String {
    val alpha by rememberInfiniteTransition(label = "cursor").animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cursor_alpha"
    )
    // Return cursor only when visible
    return if (alpha > 0.5f) "▍" else " "
}