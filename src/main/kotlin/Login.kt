import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.application
import androidx.compose.ui.window.Popup
fun main() = application{
    val icon = painterResource("drawable/PearlTeethIcon.png")
    LoginPage(
        onClose = ::exitApplication
    )
    Tray(
        icon = icon, menu = {}
    )
}
// Función para validar los campos del formulario
fun validateFields(username: String, password: String): Boolean {
    // Puedes agregar reglas de validación aquí
    // Por ejemplo, asegurarte de que ambos campos no estén vacíos
    return username.isNotBlank() && password.isNotBlank()
}