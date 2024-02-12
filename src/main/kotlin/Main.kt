import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.window.*
import java.awt.Dimension
import java.time.LocalDate

var textoTituloPanel = mutableStateOf("Menu principal")
var selectedDateGlobal = mutableStateOf(LocalDate.now())
@Composable
fun menu(onClose: () -> Unit, updateSelectedDate: (LocalDate) -> Unit) {
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
                        TopBar(onMenuClick = { showOverlay2.value = true })
                        Row {
                            SideBar { section ->
                                currentSection = section
                            }
                            myPage(currentSection,updateSelectedDate)
                        }
                    }
                }
            },
        )
        // Mostrar el overlay cuando showOverlay es true
        if (showOverlay.value) {
            CalendarOverlay(
                initialSelectedDate = selectedDateGlobal.value,
                onOverlayDismiss = { showOverlay.value = false },
                dateSelectionListener = object : DateSelectionListener {
                    override fun onDateSelected(newDate: LocalDate) {
                        selectedDateGlobal.value = newDate // Actualiza el estado global
                    }
                }
            )
        }
    }
}
@Composable
fun myPage(currentSection: Section, updateSelectedDate: (LocalDate) -> Unit) {
    when (currentSection) {
        Section.HOME -> Content(currentSection = Section.HOME, updateSelectedDate = updateSelectedDate)
        Section.CALENDAR -> Content(currentSection = Section.CALENDAR, updateSelectedDate = updateSelectedDate)
        Section.FILIAR -> Content(currentSection = Section.FILIAR, updateSelectedDate = updateSelectedDate)
        Section.PATIENTS -> Content(currentSection = Section.PATIENTS, updateSelectedDate = updateSelectedDate)
    }
}
enum class Section {
    HOME, CALENDAR, FILIAR, PATIENTS
}