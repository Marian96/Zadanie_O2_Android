package marian.puskas.o2zadanie.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val surfaceHigh = Color(0xFF8C8C9A)
val surfaceLow = Color(0xFFFFFFFF)
val surfaceBrand = Color(0xFF0050FF)
val surfaceDanger = Color(0xFFDC2828)
val surfaceDangerVariant = Color(0xFFFFDCDC)
val surfaceWarning = Color(0xFFA56315)
val surfaceWarningVariant = Color(0xFFFAF1B6)

val contentHigh = Color(0xFF2C2C31)
val contentMedium = Color(0xFF8C8C9A)
val contentLow = Color(0xFFC9C9CE)
val contentDanger = Color(0xFFDC2828)
val contentWarning = Color(0xFFA56315)

val hoverState = Color(0x0F1A1A1A)
val focusState = Color(0xCC1A1A1A)

val CustomColorScheme = lightColorScheme(
    primaryContainer = surfaceHigh,
    primary = surfaceBrand,
    onPrimary = Color.White,
    error = contentDanger,
    onError = Color.White,
    errorContainer = surfaceDanger,
    onErrorContainer = surfaceDangerVariant,
    surface = surfaceHigh,
    surfaceVariant = surfaceLow,
    onSurface = contentHigh,
    onSurfaceVariant = contentMedium,
    outlineVariant = contentMedium,
)