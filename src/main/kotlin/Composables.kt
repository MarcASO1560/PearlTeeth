import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*

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
                    .wrapContentSize(align = Alignment.CenterStart)
            )

        }
    }
}

@Composable
fun TopBar() {
    MaterialTheme {
        Box(modifier = Modifier
            .shadow(10.dp)
            .fillMaxWidth()
            .height(60.dp)
            .background(Turquoise)
        ){
            Row(modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentSize(Alignment.CenterStart)) {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        tint = White,
                        contentDescription = "arrow1"
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.ArrowForward,
                        tint = White,
                        contentDescription = "arrow2"
                    )
                }
            }
            Row(modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentSize(Alignment.CenterEnd)) {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.Person,
                        tint = White,
                        contentDescription = "person"
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.Home,
                        tint = White,
                        contentDescription = "home"
                    )
                }
            }
        }
    }
}