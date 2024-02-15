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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

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
        SplashScreen()
    }
    // Iniciar la conexión de manera asíncrona
    LaunchedEffect(Unit) {
        conecta(
            onSuccess = {
                isConnectionReady = true
                showSplashScreen = false
            },
            onError = { sqle ->
                println("Error connecting to the database: $sqle")
                showSplashScreen = false
            }
        )
    }
    if (isConnectionReady) {
        LoginPage(onClose = ::exitApplication)
    }
}

@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // Asume que tienes un icono en los recursos llamado `drawable/PearlTeethIcon.png`
        val icon = painterResource("drawable/PearlTeethIcon.png")
        Image(painter = icon, contentDescription = "Splash Screen Icon", modifier = Modifier.size(200.dp).shadow(40.dp))
    }
}
@Composable
fun AnimatedSplashScreen(onAnimationEnd: () -> Unit) {
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 3f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
        alpha.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
        onAnimationEnd()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Asume que tienes un icono en los recursos llamado `drawable/your_icon.png`
        Image(
            painter = painterResource("drawable/your_icon.png"),
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