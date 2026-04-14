
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.ExperimentalBrowserHistoryApi
import com.osg.openanimation.PreviewApplication
import com.osg.openanimation.core.ui.webApp
import org.koin.plugin.module.dsl.startKoin

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    startKoin<PreviewApplication>()
    webApp()
}

