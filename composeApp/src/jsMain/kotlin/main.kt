import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.ExperimentalBrowserHistoryApi
import com.osg.core.ui.webApp
import com.osg.openanimation.basePreviewApp

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    webApp(
        baseApp = basePreviewApp
    )
}

