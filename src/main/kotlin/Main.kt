import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
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

@Composable
fun SideBar() {
    MaterialTheme {
       Box(modifier = Modifier
           .shadow(10.dp)
           .fillMaxHeight()
           .width(100.dp)
           .background(Bright2)
           .padding(end = 10.dp)
       )
    }
}
@Composable
fun Logo() {
    val image = painterResource("drawable/PearlTeethIcon.png")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),

    ) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = image,
                alpha = 0.75F,
                contentDescription = "Icon",
                modifier = Modifier
                    .size(140.dp)
                    .padding(20.dp)
                    .align(Alignment.CenterVertically)
            )
            Text("PearlTeeth",
                color = Grey,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize(align = CenterStart)
            )

        }
    }
}
@Composable
fun TopBar() {
    TopAppBar(
        modifier = Modifier
            .shadow(10.dp),
        backgroundColor = Turquoise,
        contentColor = White,
        title = {},
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "arrow1"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.KeyboardArrowRight,
                    contentDescription = "arrow2"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "home"
                )
            }
        },
    )
}