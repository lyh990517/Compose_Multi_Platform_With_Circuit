package compose.project.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import compose.project.demo.ui.AdaptiveLayout

private val localAdaptiveLayoutType =
    compositionLocalOf { mutableStateOf(AdaptiveLayoutType.Compact) }
private val localContentType = compositionLocalOf { mutableStateOf(ContentType.Single) }
private val localThemeIsDark = compositionLocalOf { mutableStateOf(true) }

object AppTheme {
    val adaptiveLayoutType: AdaptiveLayoutType
        @Composable @ReadOnlyComposable get() = localAdaptiveLayoutType.current.value

    val contentType: ContentType
        @Composable @ReadOnlyComposable get() = localContentType.current.value

    val isDark: MutableState<Boolean>
        @Composable @ReadOnlyComposable get() = localThemeIsDark.current
}

@Composable
fun DemoTheme(content: @Composable () -> Unit) {
    val adaptiveLayoutType = remember { mutableStateOf(AdaptiveLayoutType.Compact) }
    val contentType = remember { mutableStateOf(ContentType.Single) }
    val isDarkState = remember { mutableStateOf(true) }

    AdaptiveLayout(adaptiveLayoutType, contentType)
    SystemAppearance(isDarkState.value)

    CompositionLocalProvider(
        localAdaptiveLayoutType provides adaptiveLayoutType,
        localContentType provides contentType,
        localThemeIsDark provides isDarkState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            content()
        }
    }
}

enum class AdaptiveLayoutType {
    Compact, Medium, Expanded
}

enum class ContentType {
    Single, Dual
}

@Composable
expect fun SystemAppearance(isDark: Boolean)