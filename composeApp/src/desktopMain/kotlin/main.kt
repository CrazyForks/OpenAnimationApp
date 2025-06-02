import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.osg.openanimation.core.ui.util.icons.brandingpack.LogoVector
import com.osg.openanimation.getBaseApp


fun main() = application {
    val basePreviewApp = getBaseApp()
    Window(
        icon = rememberVectorPainter(image = LogoVector),
        onCloseRequest = ::exitApplication,
        title = "OpenAnimation",
    ) {

        window.background = java.awt.Color(Color.Green.toArgb())

        basePreviewApp.AppEntry()
    }
}