import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import java.awt.Dimension

const val MIN_HEIGHT = 200
const val MIN_WIDTH = 300

@Composable
fun menu(onClose: () -> Unit) {
    var currentSection by remember { mutableStateOf(Section.HOME) }
    val state = rememberWindowState(
        placement = WindowPlacement.Maximized
    )
    val icon = painterResource("drawable/PearlTeethIcon.png")
    Window(
        onCloseRequest = { onClose.invoke() },
        title = "PearlTeeth",
        visible = true,
        state = state,
        icon = icon
    ) {
        window.minimumSize = Dimension(800, 600)
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
        Section.DATES -> Content(currentSection = Section.DATES)
    }
}
enum class Section {
    HOME, CALENDAR, FILIAR, PATIENTS, DATES
}