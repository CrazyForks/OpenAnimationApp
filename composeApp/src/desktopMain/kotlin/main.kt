import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.osg.openanimation.getBaseApp


fun main() = application {
    val basePreviewApp = getBaseApp()
    Window(
        onCloseRequest = ::exitApplication,
        title = "OpenAnimation",
    ) {
        basePreviewApp.AppEntry()
    }
}