import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.window.CanvasBasedWindow
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.bindToNavigation
import com.osg.core.ui.theme.commonFontResource
import com.osg.openanimation.basePreviewApp
import kotlinx.browser.document
import org.jetbrains.skiko.wasm.onWasmReady
import kotlinx.browser.window
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import openanimationapp.composeapp.generated.resources.Res
import openanimationapp.composeapp.generated.resources.noto_color_emoji
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.preloadFont
import org.w3c.dom.HTMLDivElement
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow(canvasElementId = "ComposeTarget") {
            PreLoadFallback{
                fadeOutElement()
                basePreviewApp.AppEntry {
                    window.bindToNavigation(it)
                }
            }
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun fadeOutElement(
    elementId: String = "splash",
    transitionDuration: Duration = 1.seconds,
    keepDuration: Duration = 100.milliseconds
) {
    val divElement = document.getElementById(elementId)
    val element = divElement as HTMLDivElement
    GlobalScope.launch {
        delay(keepDuration)
        val fadeOutMs = transitionDuration.inWholeMilliseconds
        element.style.transition = "opacity ${fadeOutMs}ms ease-out"
        element.style.opacity = "0"
        delay(transitionDuration)
        element.style.zIndex = "-1"
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PreLoadFallback(
    content: @Composable () -> Unit
) {
    val fontsResource =  listOf(commonFontResource, Res.font.noto_color_emoji)

    val fontStates = fontsResource.map{
        preloadFont(it).value
    }

    val fontFamilyResolver = LocalFontFamilyResolver.current
    var isPreloadComplete by remember { mutableStateOf(false) }
    LaunchedEffect(fontFamilyResolver, fontStates) {
        fontStates.forEach { fontState ->
            fontState?.let { font ->
                fontFamilyResolver.preload(FontFamily(font))
            }
        }

        if (fontStates.any{it != null}) {
            isPreloadComplete = true
        }
    }
    if (isPreloadComplete) {
        content()
    }
}
