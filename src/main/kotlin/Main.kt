import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.window.*
import java.awt.Dimension
import java.time.LocalDate

@Composable
fun menu(onClose: () -> Unit) {
    var currentSection by remember { mutableStateOf(Section.HOME) }
    var isOverlayVisible by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val state = rememberWindowState(
        placement = WindowPlacement.Maximized
    )
    val icon = painterResource("drawable/PearlTeethIcon.png")
    Window(
        onCloseRequest = {
            onClose.invoke() },
        title = "PearlTeeth",
        visible = true,
        state = state,
        icon = icon
    ) {
        window.minimumSize = Dimension(1380, 800)
        Scaffold(
            content = { padding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    color = Bright1
                ){
                    Column {
                        TopBar(onMenuClick = { isOverlayVisible = true })
                        Row {
                            SideBar { section ->
                                currentSection = section
                            }
                            myPage(currentSection)
                        }
                    }
                }
                val currentShowOverlay by rememberUpdatedState(showOverlay.value)
                if (currentShowOverlay) {
                    // Llamada a CalendarOverlay desde el main
                    CalendarOverlay(
                        selectedDateState = selectedDateState,
                        onOverlayDismiss = {
                            // Lógica que se ejecutará cuando se cierre el overlay
                            showOverlay.value = false
                        },
                        onOverlayAction = {},
                    )
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