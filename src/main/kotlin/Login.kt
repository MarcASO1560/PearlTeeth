import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.application

import PearlTeethDB.*
fun main() = application{
    val icon = painterResource("drawable/PearlTeethIcon.png")
    MainD()
    LoginPage(
        onClose = ::exitApplication
    )
    Tray(
        icon = icon, menu = {}
    )
}
// Función para validar los campos del formulario
fun validateFieldsLogin(username: String, password: String): Boolean {
    return username.isNotBlank() && password.isNotBlank()
}