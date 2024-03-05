import PearlTeethDB.MainD.*
import PearlTeethDB.MainD.Companion.conecta
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinx.coroutines.delay

fun main() = application {
    System.setProperty("skiko.renderApi", "OPENGL")
    var showSplashScreen by remember { mutableStateOf(true) }
    var isConnectionReady by remember { mutableStateOf(false) }
    val icon = painterResource("drawable/PearlTeethIcon.png")
    Window(
        onCloseRequest = ::exitApplication,
        resizable = false,
        undecorated = true,
        transparent = true,
        icon = icon,
        visible = showSplashScreen,
        state = WindowState(position = WindowPosition.Aligned(Alignment.Center), width = 400.dp, height = 400.dp)
    ) {
        AnimatedSplashScreen()
    }
    // Iniciar la conexión de manera asíncrona
    LaunchedEffect(Unit) {
        delay(2000)
        conecta(
            onSuccess = {
                isConnectionReady = true
                showSplashScreen = false
            },
            onError = {
                showSplashScreen = false
            }
        )
    }
    if (isConnectionReady) {
        LoginPage(onClose = ::exitApplication)
    }
}
@Composable
fun AnimatedSplashScreen() {
    val scale = remember { Animatable(0.1f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = LinearEasing
            )
        )
    }
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 0.4f,
            animationSpec = tween(
                durationMillis = 1600,
                easing = FastOutSlowInEasing
            )
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource("drawable/PearlTeethIcon.png"),
            contentDescription = "Splash Icon",
            modifier = Modifier.graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value,
                alpha = alpha.value
            )
        )
    }
}

// Función para validar los campos del formulario
fun validateFieldsLogin(username: String, password: String): Boolean {
    return username.isNotBlank() && password.isNotBlank()
}