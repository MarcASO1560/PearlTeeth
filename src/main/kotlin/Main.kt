import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.window.*
fun main() = application {
    val icon = painterResource("drawable/PearlTeethIcon.png")
    Tray(
        icon = icon,
        menu = {
            Item("Quit App", onClick = ::exitApplication)
        }
    )
    Window(onCloseRequest = ::exitApplication, title = "PearlTeeth", icon = icon) {
        Scaffold(
            topBar =
            {
                Column {
                    TopBar()
                    Row {
                        SideBar()
                        Logo()
                    }
                }
            },
            content = { padding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    color = Bright1
                ){
                }
            }
        )
    }
}

