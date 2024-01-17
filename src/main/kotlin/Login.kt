import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.application
fun main() = application{
    val icon = painterResource("drawable/PearlTeethIcon.png")
    LoginPage(
        onClose = ::exitApplication
    )
    Tray(
        icon = icon, menu = {}
    )
}