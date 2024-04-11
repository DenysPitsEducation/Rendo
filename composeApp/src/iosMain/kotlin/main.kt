import androidx.compose.ui.window.ComposeUIViewController
import com.rendo.app.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
