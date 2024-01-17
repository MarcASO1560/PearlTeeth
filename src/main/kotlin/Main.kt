import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

@Composable
fun menu(onClose: () -> Unit) {
    var currentSection by remember { mutableStateOf(Section.HOME) }
    val icon = painterResource("drawable/PearlTeethIcon.png")
    Window(onCloseRequest = { onClose.invoke() }, title = "PearlTeeth", visible = true, state = WindowState(width = 1280.dp, height = 720.dp), icon = icon) {
        Scaffold(
            topBar =
            {
                Column {
                    TopBar()

                }
            },
            content = { padding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    color = Bright1
                ){
                    Row {
                        SideBar { section ->
                            currentSection = section
                        }
                        myPage(currentSection)
                    }
                }
            }
        )
    }
}
@Composable
fun myPage(currentSection: Section) {
    when (currentSection) {
        Section.HOME -> Content(currentSection = Section.HOME)
        Section.CALENDAR -> Content(currentSection = Section.CALENDAR)
        Section.FILIAR -> Content(currentSection = Section.FILIAR)
        Section.PATIENTS -> Content(currentSection = Section.PATIENTS)
    }
}
enum class Section {
    HOME, CALENDAR, FILIAR, PATIENTS
}
