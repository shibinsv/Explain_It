package com.shibin.spendly.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val SpendlyColorScheme = darkColorScheme(
    primary             = Purple,
    onPrimary           = TextPrimary,
    primaryContainer    = BgHeader,
    onPrimaryContainer  = TextPrimary,
    background          = BgDeep,
    onBackground        = TextPrimary,
    surface             = BgCard,
    onSurface           = TextPrimary,
    surfaceVariant      = BgSurface,
    onSurfaceVariant    = TextMuted,
    outline             = BorderDefault,
    outlineVariant      = BorderStrong,
    error               = ExpenseRed,
    onError             = TextPrimary,
)

@Composable
fun Im10Theme(content: @Composable () -> Unit) {

    MaterialTheme(
        colorScheme = SpendlyColorScheme,
        content = content
    )
}