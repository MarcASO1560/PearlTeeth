import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import androidx.compose.runtime.mutableStateOf
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


var nombre = mutableStateOf("")
var apellidos = mutableStateOf("")
var NumIdentificacion = mutableStateOf("")

val options = listOf("DNI", "NIE", "Pasaporte")
val expanded = mutableStateOf(false)
val selectedOptionText = mutableStateOf("NIF")

val optionsSex = listOf("Hombre", "Mujer", "Otro")
val expandedSex = mutableStateOf(false)
val selectedOptionTextSex = mutableStateOf("Sexo")

var Alergias = mutableStateOf("")
var Enfermedades = mutableStateOf("")
var Medicaciones = mutableStateOf("")

var optionsBlood = listOf("0-", "0+", "A-", "A+", "B-", "B+", "AB-", "AB+")
var expandedBlood = mutableStateOf(false)
var selectedOptionTextBlood = mutableStateOf("Grupo Sanguíneo")

var Direccion = mutableStateOf("")
var CP = mutableStateOf("")
var Poblacion = mutableStateOf("")
var Provincia = mutableStateOf("")
var Pais = mutableStateOf("")
var TelefonoPrincipal = mutableStateOf("")
var TelefonoAuxiliar = mutableStateOf("")
var Email = mutableStateOf("")

val LOPD = mutableStateOf(false)
val aceptInfo = mutableStateOf(false)
val includeMail = mutableStateOf(false)

val SMS = mutableStateOf(false)
val correoElectronico = mutableStateOf(false)
val EnvioPostal = mutableStateOf(false)
@Composable
fun Filiar() {
	val scrollState = rememberScrollState()

	var showCalendarOverlay by remember { mutableStateOf(false) }
	var showLoginOverlay by remember { mutableStateOf(false) }
	if (showCalendarOverlay) {
		menu(onClose = {})
	}
	if (showLoginOverlay) {
		LoginOverlay(
			onOverlayDismiss = { showLoginOverlay = false },
			onOverlayAction = { /* Acción al aplicar */ }
		)
	}
	Box(
		modifier = Modifier.fillMaxSize()
			.padding(10.dp)
			.background(White)
	) {
		// Usamos Column con el modificador verticalScroll
		Column(modifier = Modifier.verticalScroll(scrollState)) {
			DatosAdministrativos(
				setShowOverlay = { showOverlayCalendar.value = it },
				nombre = nombre,
				apellidos = apellidos,
				NumIdentificacion = NumIdentificacion,
				options = options,
				expanded = expanded,
				selectedOptionText = selectedOptionText,
				optionsSex = optionsSex,
				expandedSex = expandedSex,
				selectedOptionTextSex = selectedOptionTextSex
			)
			DatosPaciente()
			DatosDomicilio()
			// Agrega aquí más composables si es necesario
		}
		// Si aún quieres usar la barra de desplazamiento:
		VerticalScrollbar(
			modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
			adapter = rememberScrollbarAdapter(scrollState = scrollState)
		)
	}
}

@Composable
fun DatosAdministrativos(
	setShowOverlay: (Boolean) -> Unit,
	nombre: MutableState<String>,
	apellidos: MutableState<String>,
	NumIdentificacion: MutableState<String>,
	options: List<String>,
	expanded: MutableState<Boolean>,
	selectedOptionText: MutableState<String>,
	optionsSex: List<String>,
	expandedSex: MutableState<Boolean>,
	selectedOptionTextSex: MutableState<String>
) {
	Card(
		backgroundColor = Bright1,
		modifier = Modifier
			.padding(8.dp, 20.dp, 20.dp, 8.dp)
			.fillMaxWidth()
			.wrapContentSize(align = Alignment.Center)
			.clip(shape = RoundedCornerShape(20.dp))
	) {
		Column {
			Box(
				modifier = Modifier.fillMaxWidth().height(35.dp).shadow(10.dp).background(Turquoise)
			) {
				Text(
					modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center),
					text = "Datos administrativos",
					style = MaterialTheme.typography.h6,
					color = White,
					fontWeight = FontWeight.Bold
				)
			}
			Column {
				Row {
					Column(modifier = Modifier.weight(2f)) {
						// Nombre | Apellidos | IDtipo | DNInumero
						Row {
							TextField(
								value = nombre.value,
								label = {
									Text(
										text = "Nombre",
										fontSize = 12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									)
								},
								onValueChange = { nombre.value = it },
								modifier = Modifier
									.padding(20.dp, 20.dp, 10.dp, 10.dp)
									.weight(0.8f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp, spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
							TextField(
								value = apellidos.value,
								onValueChange = { apellidos.value = it },
								label = {
									Text(
										text = "Apellidos",
										fontSize = 12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									)
								},
								modifier = Modifier
									.padding(10.dp, 20.dp, 10.dp, 10.dp)
									.weight(0.8f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp, spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
							tipoId( options = options, expanded = expanded, selectedOptionText = selectedOptionText)
							TextField(
								value = NumIdentificacion.value,
								onValueChange = { NumIdentificacion.value = it },
								label = {
									Text(
										text = "N°Identificación",
										fontSize = 12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									)
								},
								modifier = Modifier
									.padding(10.dp, 20.dp, 20.dp, 10.dp)
									.weight(0.5f)
									.shadow(elevation = 20.dp, spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
						}
						Row {
							DatePicker(
								title = "Fecha de nacimiento",
								setShowOverlay = { show -> setShowOverlay(show) }
							)
							sexo(
								optionsSex = optionsSex,
								expandedSex = expandedSex,
								selectedOptionTextSex = selectedOptionTextSex
							)
						}
					}
				}
			}
		}
	}
}

@Composable
fun DatePicker(
	title: String,
	setShowOverlay: (Boolean) -> Unit
) {
	// Estado para el texto mostrado en el TextField, inicializado con la fecha actual formateada
	var textDate by remember { mutableStateOf(selectedDateGlobal.value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))) }
	LaunchedEffect(selectedDateGlobal.value) {
		textDate = selectedDateGlobal.value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
	}
	// UI para el DatePicker
	Box(
		modifier = Modifier
			.padding(20.dp, 10.dp, 10.dp, 20.dp)
			.width(300.dp)
			.shadow(elevation = 20.dp, spotColor = Turquoise)
			.clip(shape = RoundedCornerShape(10.dp))
			.background(White)
			.height(50.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
		) {
			IconButton(onClick = { setShowOverlay(true) }) {
				Icon(
					Icons.Filled.DateRange,
					tint = Turquoise,
					modifier = Modifier.background(White),
					contentDescription = "Select Date"
				)
			}
			TextField(
				value = textDate,
				onValueChange = { newValue ->
					// Permite solo caracteres numéricos y el separador '/'
					if (newValue.matches("^\\d{0,2}/?\\d{0,2}/?\\d{0,4}\$".toRegex())) {
						textDate = newValue
						// Intenta actualizar la fecha solo si el formato es completo y correcto
						if (newValue.matches("^\\d{2}/\\d{2}/\\d{4}\$".toRegex())) {
							try {
								val parsedDate = LocalDate.parse(newValue, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
								selectedDateGlobal.value = parsedDate
							} catch (e: DateTimeParseException) {
								// Manejar el error de formato, si es necesario
							}
						}
					}
				},
				label = {
					Text(
						text = title,
						fontSize = 12.sp,
						fontWeight = FontWeight.Bold,
						color = Turquoise
					) },
				modifier = Modifier.weight(1f),
				colors = TextFieldDefaults.textFieldColors(
					backgroundColor = White,
					focusedIndicatorColor = Turquoise,
					unfocusedIndicatorColor = White,
					cursorColor = Grey,
					textColor = Dark1
				),
				textStyle = TextStyle(
					fontWeight = FontWeight.Bold,
					fontSize = 14.sp
				),
				keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
				singleLine = true
			)
		}
	}
}
@Composable
fun DatosDomicilio() {
	//Datos de Domicilio
	Card(
		backgroundColor = Bright1,
		modifier = Modifier
			.padding(8.dp,8.dp,20.dp,8.dp)
			.fillMaxWidth()
			.wrapContentSize(align = Alignment.Center)
			.clip(shape = RoundedCornerShape(20.dp))
	) {
		Column {
			Box(modifier = Modifier.fillMaxWidth().height(35.dp).shadow(10.dp).background(Turquoise)){
				Text(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center), text = "Domicilio", style = MaterialTheme.typography.h6,color = White,fontWeight = FontWeight.Bold)
			}
			Column {
				Row {
					Column(modifier = Modifier.weight(2f)) {
						// direccion | poblacion | provincia | pais | cp.
						Row {
							TextField(
								value = Direccion.value,
								label = {
									Text(
										text = "Dirección",
										fontSize =  12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								onValueChange = {
									Direccion.value = it },
								modifier = Modifier
									.padding(20.dp,20.dp,10.dp,10.dp)
									.weight(1f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
							TextField(
								value = Poblacion.value,
								onValueChange = { Poblacion.value = it },
								label = {
									Text(
										text = "Pueblo o Ciudad",
										fontSize = 12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp,20.dp,20.dp,10.dp)
									.weight(1f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
						}
						Row {
							TextField(
								value = Provincia.value,
								onValueChange = { Provincia.value = it },
								label = {
									Text(
										text = "Provincia",
										fontSize = 12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(20.dp,10.dp,10.dp,10.dp)
									.weight(0.7f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
							TextField(
								value = Pais.value,
								onValueChange = { Pais.value = it },
								label = {
									Text(
										text = "País",
										fontSize = 12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp,10.dp,10.dp,10.dp)
									.weight(0.5f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
							TextField(
								value = CP.value,
								onValueChange = { CP.value = it },
								label = {
									Text(
										text = "CP",
										fontSize =  12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp,10.dp,20.dp,10.dp)
									.weight(0.2f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
						}
						// telefono particular | telefono trabajo | movil | fax
						Row {
							TextField(
								value = TelefonoPrincipal.value,
								onValueChange = { TelefonoPrincipal.value = it },
								label = {
									Text(
										text = "Teléfono Principal",
										fontSize =  12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(20.dp,10.dp,10.dp,10.dp)
									.weight(1.5f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
							TextField(
								value = TelefonoAuxiliar.value,
								onValueChange = { TelefonoAuxiliar.value = it },
								label = {
									Text(
										text = "Teléfono Auxiliar",
										fontSize =  12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp,10.dp,20.dp,10.dp)
									.weight(1.5f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
						}
						Row {
							TextField(
								value = Email.value,
								onValueChange = { Email.value = it },
								label = {
									Text(
										text = "Correo Electronico",
										fontSize = 12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(20.dp,10.dp,20.dp,20.dp)
									.weight(1f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
						}
						// checkboxes
						Column {
							Row {
								Column {
									Card(
										backgroundColor = Turquoise,
										modifier = Modifier
											.padding(20.dp,10.dp,10.dp,0.dp)
											.width(165.dp)
											.shadow(elevation = 20.dp,spotColor = Turquoise)
											.clip(shape = RoundedCornerShape(10.dp))
											.height(30.dp),
									){
										Text(
											text = "Protección de Datos",
											fontSize = 14.sp,
											fontWeight = FontWeight.Bold,
											color = White,
											modifier = Modifier.wrapContentSize(align = Alignment.Center)
										)
									}
									Card(
										backgroundColor = White,
										modifier = Modifier
											.padding(20.dp,10.dp,10.dp,10.dp)
											.width(600.dp)
											.shadow(elevation = 20.dp,spotColor = Turquoise)
											.clip(shape = RoundedCornerShape(10.dp))
											.height(50.dp),
									){
										Row {
											ProteccionDatos()
										}
									}
								}
								Column {
									Card(
										backgroundColor = Turquoise,
										modifier = Modifier
											.padding(10.dp,10.dp,10.dp,0.dp)
											.width(300.dp)
											.shadow(elevation = 20.dp,spotColor = Turquoise)
											.clip(shape = RoundedCornerShape(10.dp))
											.height(30.dp),
									){
										Text(
											text = "Medios de contrato que el cliente acepta",
											fontSize = 14.sp,
											fontWeight = FontWeight.Bold,
											color = White,
											modifier = Modifier.wrapContentSize(align = Alignment.Center)
										)
									}
									Card(
										backgroundColor = White,
										modifier = Modifier
											.padding(10.dp,10.dp,20.dp,20.dp)
											.width(500.dp)
											.shadow(elevation = 20.dp,spotColor = Turquoise)
											.clip(shape = RoundedCornerShape(10.dp))
											.height(50.dp),
									){
										Row{
											medioContratoClienteAcepta()
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
@Composable
fun DatosPaciente() {
	Card(
		backgroundColor = Bright1,
		modifier = Modifier
			.padding(8.dp,8.dp,20.dp,8.dp)
			.fillMaxWidth()
			.wrapContentSize(align = Alignment.Center)
			.clip(shape = RoundedCornerShape(20.dp))
	) {
		Column {
			Box(modifier = Modifier.fillMaxWidth().height(35.dp).shadow(10.dp).background(Turquoise)) {
				Text(
					modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center),
					text = "Datos del paciente",
					style = MaterialTheme.typography.h6,
					color = White,
					fontWeight = FontWeight.Bold
				)
			}
			Column {
				Row {
					Column(modifier = Modifier.weight(1f)) {
						Row {
							TextField(
								value = Alergias.value,
								onValueChange = { Alergias.value = it },
								label = {
									Text(
										text = "Alergias",
										fontSize = 12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(20.dp,20.dp,20.dp,10.dp)
									.weight(1f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
						}
						Row {
							TextField(
								value = Enfermedades.value,
								onValueChange = { Enfermedades.value = it },
								label = {
									Text(
										text = "Enfermedades",
										fontSize = 12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(20.dp,10.dp,20.dp,10.dp)
									.weight(1f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
						}
						Row {
							TextField(
								value = Medicaciones.value,
								onValueChange = { Medicaciones.value = it },
								label = {
									Text(
										text = "Medicación",
										fontSize = 12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(20.dp,10.dp,20.dp,10.dp)
									.weight(1f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Dark2,
									unfocusedIndicatorColor = White
								),
								textStyle = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp
								)
							)
						}
						Row {
							grupoSanguineo()
						}
					}
				}
			}
		}
	}
}
@Composable
fun tipoId(options: List<String>, expanded: MutableState<Boolean>, selectedOptionText: MutableState<String>) {
	Box(
		contentAlignment = Alignment.CenterStart,
		modifier = Modifier
			.padding(10.dp,20.dp,10.dp,10.dp)
			.width(112.dp)
			.height(50.dp)
			.shadow(elevation = 20.dp,spotColor = Turquoise)
			.clip(shape = RoundedCornerShape(10.dp))
			.background(White)
			.clickable { expanded.value = !expanded.value },
	) {
		Text(
			text = selectedOptionText.value,
			fontWeight = FontWeight.Bold,
			fontSize = 14.sp,
			color = Turquoise,
			modifier = Modifier.padding(start = 10.dp)
		)
		Icon(
			Icons.Filled.ArrowDropDown, "contentDescription",
			Modifier.align(Alignment.CenterEnd),
			tint = Turquoise
		)
		DropdownMenu(
			expanded = expanded.value,
			onDismissRequest = { expanded.value = false }
		) {
			options.forEach { selectionOption ->
				DropdownMenuItem(
					onClick = {
						selectedOptionText.value = selectionOption
						expanded.value = false
					}
				) {
					Text(text = selectionOption,
						fontWeight = FontWeight.Bold,
						fontSize = 14.sp,
						color = Turquoise)
				}
			}
		}
	}
}
@Composable
fun sexo(optionsSex: List<String>, expandedSex: MutableState<Boolean>, selectedOptionTextSex: MutableState<String>) {
	Box(
		contentAlignment = Alignment.CenterStart,
		modifier = Modifier
			.padding(10.dp,10.dp,10.dp,10.dp)
			.width(112.dp)
			.height(50.dp)
			.shadow(elevation = 20.dp,spotColor = Turquoise)
			.clip(shape = RoundedCornerShape(10.dp))
			.background(White)
			.clickable { expandedSex.value = !expandedSex.value },
	) {
		Text(
			text = selectedOptionTextSex.value,
			fontWeight = FontWeight.Bold,
			fontSize = 14.sp,
			color = Turquoise,
			modifier = Modifier.padding(start = 10.dp)
		)
		Icon(
			Icons.Filled.ArrowDropDown, "contentDescription",
			Modifier.align(Alignment.CenterEnd),
			tint = Turquoise
		)
		DropdownMenu(
			expanded = expandedSex.value,
			onDismissRequest = { expandedSex.value = false }
		) {
			optionsSex.forEach { selectionOption ->
				DropdownMenuItem(
					onClick = {
						selectedOptionTextSex.value = selectionOption
						expandedSex.value = false
					}
				) {
					Text(text = selectionOption,
						fontWeight = FontWeight.Bold,
						fontSize = 14.sp,
						color = Turquoise)
				}
			}
		}
	}
}
@Composable
fun grupoSanguineo() {
	Box(
		contentAlignment = Alignment.CenterStart,
		modifier = Modifier
			.padding(20.dp, 10.dp, 10.dp, 20.dp)
			.width(200.dp)
			.height(50.dp)
			.shadow(elevation = 20.dp, spotColor = Turquoise)
			.clip(shape = RoundedCornerShape(10.dp))
			.background(White)
			.clickable { expandedBlood.value = !expandedBlood.value },
	) {
		Text(
			text = selectedOptionTextBlood.value,
			fontWeight = FontWeight.Bold,
			fontSize = 14.sp,
			color = Turquoise,
			modifier = Modifier.padding(start = 10.dp)
		)
		Icon(
			Icons.Filled.ArrowDropDown, "contentDescription",
			Modifier.align(Alignment.CenterEnd),
			tint = Turquoise
		)
		DropdownMenu(
			expanded = expandedBlood.value,
			onDismissRequest = { expandedBlood.value = false },
			modifier = Modifier
				.verticalScroll(rememberScrollState())
				.width(200.dp)
				.heightIn(0.dp, 200.dp) // Ajusta la altura máxima según tus necesidades
		) {
			optionsBlood.forEach { selectionOption ->
				DropdownMenuItem(
					onClick = {
						selectedOptionTextBlood.value = selectionOption
						expandedBlood.value = false
					}
				) {
					Text(
						text = selectionOption,
						fontWeight = FontWeight.Bold,
						fontSize = 14.sp,
						color = Turquoise
					)
				}
			}
		}
	}
}
@Composable
fun ProteccionDatos() {
	Row(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)) {
		Box(modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.Center).weight(1f)){
			Row(modifier = Modifier.fillMaxSize().padding(5.dp).wrapContentWidth(align = Alignment.CenterHorizontally)) {
				Checkbox(
					checked = LOPD.value,
					modifier = Modifier.padding(5.dp).size(40.dp).wrapContentSize(align = Alignment.Center),
					onCheckedChange = { LOPD.value = it },
					colors = CheckboxDefaults.colors(
						checkedColor = Turquoise,
						uncheckedColor = Grey,
						checkmarkColor = White
					)
				)
				Text(
					text = "Acepta L.O.P.D",
					fontSize = 14.sp,
					fontWeight = FontWeight.Bold,
					color = Grey,
					modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.CenterStart)
				)
			}
		}
		Box(modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.Center).weight(1.2f)) {
			Row(modifier = Modifier.fillMaxSize().padding(5.dp).wrapContentWidth(align = Alignment.CenterHorizontally)) {
				Checkbox(
					checked = aceptInfo.value,
					modifier = Modifier.padding(5.dp).size(40.dp).wrapContentSize(align = Alignment.Center),
					onCheckedChange = { aceptInfo.value = it },
					colors = CheckboxDefaults.colors(
						checkedColor = Turquoise,
						uncheckedColor = Grey,
						checkmarkColor = White
					)
				)
				Text(
					text = "Acepta Información",
					fontSize = 14.sp,
					fontWeight = FontWeight.Bold,
					color = Grey,
					modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.CenterStart)
				)
			}
		}
		Box(modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.Center).weight(1.1f)) {
			Row(modifier = Modifier.fillMaxSize().padding(5.dp).wrapContentWidth(align = Alignment.CenterHorizontally)) {
				Checkbox(
					checked = includeMail.value,
					modifier = Modifier.padding(5.dp).size(40.dp).wrapContentSize(align = Alignment.Center),
					onCheckedChange = { includeMail.value = it },
					colors = CheckboxDefaults.colors(
						checkedColor = Turquoise,
						uncheckedColor = Grey,
						checkmarkColor = White
					)
				)
				Text(
					text = "Incluir en Mailing",
					fontSize = 14.sp,
					fontWeight = FontWeight.Bold,
					color = Grey,
					modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.CenterStart)
				)
			}
		}
	}
}
@Composable
fun medioContratoClienteAcepta() {
	Row(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)) {
		Box(modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.Center).weight(0.5f)){
			Row(modifier = Modifier.fillMaxSize().padding(5.dp).wrapContentWidth(align = Alignment.CenterHorizontally)) {
				Checkbox(
					checked = SMS.value,
					modifier = Modifier.padding(5.dp).size(40.dp).wrapContentSize(align = Alignment.Center),
					onCheckedChange = { SMS.value = it },
					colors = CheckboxDefaults.colors(
						checkedColor = Turquoise,
						uncheckedColor = Grey,
						checkmarkColor = White
					)
				)
				Text(
					text = "SMS",
					fontSize = 14.sp,
					fontWeight = FontWeight.Bold,
					color = Grey,
					modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.CenterStart)
				)
			}
		}
		Box(modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.Center).weight(0.85f)) {
			Row(modifier = Modifier.fillMaxSize().padding(5.dp).wrapContentWidth(align = Alignment.CenterHorizontally)) {
				Checkbox(
					checked = correoElectronico.value,
					modifier = Modifier.padding(5.dp).size(40.dp).wrapContentSize(align = Alignment.Center),
					onCheckedChange = { correoElectronico.value = it },
					colors = CheckboxDefaults.colors(
						checkedColor = Turquoise,
						uncheckedColor = Grey,
						checkmarkColor = White
					)
				)
				Text(
					text = "Correo Elctrónico",
					fontSize = 14.sp,
					fontWeight = FontWeight.Bold,
					color = Grey,
					modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.CenterStart)
				)
			}
		}
		Box(modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.Center).weight(0.8f)) {
			Row(modifier = Modifier.fillMaxSize().padding(5.dp).wrapContentWidth(align = Alignment.CenterHorizontally)) {
				Checkbox(
					checked = EnvioPostal.value,
					modifier = Modifier.padding(5.dp).size(40.dp).wrapContentSize(align = Alignment.Center),
					onCheckedChange = { EnvioPostal.value = it },
					colors = CheckboxDefaults.colors(
						checkedColor = Turquoise,
						uncheckedColor = Grey,
						checkmarkColor = White
					)
				)
				Text(
					text = "Envío Postal",
					fontSize = 14.sp,
					fontWeight = FontWeight.Bold,
					color = Grey,
					modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.CenterStart)
				)
			}
		}
	}
}
@Composable
fun addingUserButtons(){
	Row(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.CenterEnd)){
		Button(
			onClick = {
				findOutPatientName()
			},
			modifier = Modifier
				.width(200.dp)
				.fillMaxHeight()
				.padding(15.dp)
				.shadow(elevation = 30.dp,spotColor = LightBlue)
				.clip(shape = RoundedCornerShape(15.dp)),
			colors = ButtonDefaults.buttonColors(
				backgroundColor = LightBlue)
		){
			Text(
				text = "Guardar",
				fontSize = 16.sp,
				color = White,
				fontWeight = FontWeight.Bold,
				modifier = Modifier.fillMaxWidth()
					.wrapContentSize(Alignment.Center)
			)
		}
	}
	Row(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.CenterStart)){
		Button(
			onClick = {
				showOverlaySureClean.value = true
			},
			modifier = Modifier
				.width(250.dp)
				.fillMaxHeight()
				.padding(15.dp)
				.shadow(elevation = 30.dp,spotColor = Lilac)
				.clip(shape = RoundedCornerShape(15.dp)),
			colors = ButtonDefaults.buttonColors(
				backgroundColor = Lilac)
		){
			Text(
				text = "Limpiar datos",
				fontSize = 16.sp,
				color = White,
				fontWeight = FontWeight.Bold,
				modifier = Modifier.fillMaxWidth()
					.wrapContentSize(Alignment.Center)
			)
		}
	}
}