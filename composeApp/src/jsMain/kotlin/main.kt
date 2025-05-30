import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.ExperimentalBrowserHistoryApi
import com.osg.openanimation.core.ui.webApp
import com.osg.openanimation.getBaseApp

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    webApp(
        baseApp = getBaseApp()
    )
}

