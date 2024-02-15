import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.window.*
import java.awt.Dimension
import java.time.LocalDate

var textoTituloPanel = mutableStateOf("PearlTeeth")
var selectedDateGlobal = mutableStateOf(LocalDate.now())
@Composable
fun menu(onClose: () -> Unit) {
    var currentSection by remember { mutableStateOf(Section.HOME) }
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
                        TopBar(onMenuClickProfile = { showOverlayProfile.value = true })
                        Box{
                            Row {
                                SideBar { section ->
                                    currentSection = section
                                }
                                myPage(currentSection)
                            }
                            if (showOverlayCreateDate.value) {
                                createDatesOverlay(
                                    onOverlayDismiss = {
                                        showOverlayCreateDate.value = false
                                    },
                                    onOverlayAction = {},
                                )
                            }
                        }
                    }
                }
            },
        )
        if (showOverlayCalendar.value) {
            CalendarOverlay(
                initialSelectedDate = selectedDateGlobal.value,
                onOverlayDismiss = { showOverlayCalendar.value = false },
                dateSelectionListener = object : DateSelectionListener {
                    override fun onDateSelected(selectedDate: LocalDate) {
                        selectedDateGlobal.value = selectedDate // Actualiza el estado global
                    }
                }
            )
        }
        if (showOverlayProfile.value) {
            ProfileOverlay(
                onOverlayDismiss = {
                    showOverlayProfile.value = false
                },
                onOverlayAction = {},
            )
        }
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