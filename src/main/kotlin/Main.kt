import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
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
                                AnimatedPage(currentSection)
                            }
                            if (showOverlayCreateDate.value) {
                                createDatesOverlay(
                                    onOverlayDismiss = {
                                        showOverlayCreateDate.value = false
                                    },
                                    onOverlayAction = {},
                                )
                            }
                            if (isSidePanelVisible.value) {
                                CourtineOverlay(
                                    onOverlayDismiss = {
                                        showOverlayProfile.value = false
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
                        selectedDateGlobal.value = selectedDate
                    }
                }
            )
        }
        if (showOverlaySureClean.value) {
            sureDeleteOverlay(
                onOverlayDismiss = {
                    showOverlaySureClean.value = false
                },
                onOverlayAction = {},
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
        if (showOverlayEditProfile.value) {
            editProfileOverlay(
                onOverlayDismiss = {
                    showOverlayProfile.value = false
                },
                onOverlayAction = {},
            )
        }
        if (showOverlayLogOut.value) {
            logOutOverlay(
                onOverlayDismiss = {
                    showOverlayProfile.value = false
                },
                onOverlayAction = {},
            )
        }
        if (showErrorOverlay.value) {
            errorOverlay(
                onOverlayDismiss = {
                    showErrorOverlay.value = false
                },
                onOverlayAction = {},
                showError = showError.value
            )
        }
    }
}
enum class Section {
    HOME, CALENDAR, FILIAR, PATIENTS, DENTAL
}
@Composable
fun AnimatedPage(currentSection: Section) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AnimatedContent(
            targetState = currentSection,
            transitionSpec = {
                if (targetState.ordinal > initialState.ordinal) {
                    slideInVertically(initialOffsetY = { fullWidth -> fullWidth }, animationSpec = tween(600)) togetherWith
                            slideOutVertically(targetOffsetY = { fullWidth -> -fullWidth }, animationSpec = tween(600))
                } else {
                    slideInVertically(initialOffsetY = { fullWidth -> -fullWidth }, animationSpec = tween(600)) togetherWith
                            slideOutVertically(targetOffsetY = { fullWidth -> fullWidth }, animationSpec = tween(600))
                }
            }
        ) { targetSection ->
            when (targetSection) {
                Section.HOME -> Content(Section.HOME)
                Section.CALENDAR -> Content(Section.CALENDAR)
                Section.FILIAR -> Content(Section.FILIAR)
                Section.PATIENTS -> Content(Section.PATIENTS)
                Section.DENTAL -> Content(Section.DENTAL)
            }
        }
    }
}
fun cleanDataPatient(){
    nombre.value = ""
    apellidos.value = ""
    NumIdentificacion.value = ""
    expanded.value = false
    selectedDateGlobal.value = LocalDate.now()
    selectedOptionText.value = "NIF"
    expandedSex.value = false
    selectedOptionTextSex.value = "Sexo"
    Alergias.value = ""
    Enfermedades.value = ""
    Medicaciones.value = ""
    selectedOptionTextBlood.value = "Grupo Sanguíneo"
    Direccion.value = ""
    CP.value = ""
    Poblacion.value = ""
    Provincia.value = ""
    Pais.value = ""
    TelefonoPrincipal.value = ""
    TelefonoAuxiliar.value = ""
    Email.value = ""
    LOPD.value = false
    aceptInfo.value = false
    includeMail.value = false
    SMS.value = false
    correoElectronico.value = false
    EnvioPostal.value = false
}