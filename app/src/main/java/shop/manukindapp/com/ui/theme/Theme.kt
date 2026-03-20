package shop.manukindapp.com.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PrimaryDark,
    onPrimary = White,
    primaryContainer = AccentGold,
    onPrimaryContainer = PrimaryDark,
    secondary = AccentGold,
    onSecondary = PrimaryDark,
    secondaryContainer = AccentGoldLight,
    onSecondaryContainer = PrimaryDark,
    tertiary = AccentGoldDark,
    onTertiary = White,
    background = Background,
    onBackground = TextPrimary,
    surface = SurfaceLight,
    onSurface = TextPrimary,
    surfaceVariant = White,
    onSurfaceVariant = TextSecondary,
    outline = AccentGold,
    outlineVariant = AccentGoldLight,
    error = ErrorRed,
    onError = White,
)

private val DarkColorScheme = darkColorScheme(
    primary = AccentGold,
    onPrimary = Black,
    primaryContainer = AccentGoldDark,
    onPrimaryContainer = White,
    secondary = AccentGoldLight,
    onSecondary = Black,
    secondaryContainer = DarkSurfaceVariant,
    onSecondaryContainer = AccentGoldLight,
    tertiary = AccentGold,
    onTertiary = Black,
    background = DarkBackground,
    onBackground = OnDarkSurface,
    surface = DarkSurface,
    onSurface = OnDarkSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = OnDarkSurface,
    outline = AccentGoldDark,
    outlineVariant = DarkSurfaceVariant,
    error = ErrorRed,
    onError = White,
)

@Composable
fun ManukindAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
