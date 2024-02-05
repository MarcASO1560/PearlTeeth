import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.awt.Color
import java.lang.module.ModuleFinder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

val showOverlay = mutableStateOf(false)
@Composable
fun Filiar() {
	var selectedDate by remember { mutableStateOf(LocalDate.now()) }
	var isOverlayVisible by remember { mutableStateOf(false) }
	Box(
		modifier = Modifier.fillMaxSize()
			.padding(10.dp,10.dp,10.dp,10.dp)
			.background(color = White)
	) {

		val state = rememberLazyListState()

		LazyColumn(
			modifier = Modifier.padding(10.dp).fillMaxSize(),
			state = state
		) {
			item {
				DatosAdministrativos(
					onCalendarClick = {
						isOverlayVisible = true
					},
					selectedDate = selectedDate,
					onDateSelected = { date ->
						selectedDate = date
					}
				)
			}
			item {
				DatosDomicilio()
			}
			item {
				DatosBanco()
			}
		}
		VerticalScrollbar(
			modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
			adapter = rememberScrollbarAdapter(
				scrollState = state
			)
		)
	}
}
var selectedDateState by mutableStateOf(DateState("16", "7", "2003"))
@Composable
fun DatosAdministrativos(onCalendarClick: () -> Unit, selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit) {
	//Datos administrativos
	var nombre = remember { mutableStateOf("") }
	var apellidos = remember { mutableStateOf("") }
	var NumIdentificacion = remember { mutableStateOf("") }

	Card(
		backgroundColor = White,
		modifier = Modifier
			.padding(8.dp,20.dp,20.dp,8.dp)
			.fillMaxWidth()
			.wrapContentSize(align = Alignment.Center)
			.clip(shape = RoundedCornerShape(20.dp))
	) {
		Column {
			Box(modifier = Modifier.fillMaxWidth().height(35.dp).shadow(10.dp).background(Turquoise)){
				Text(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center), text = "Datos Administrativos", style = MaterialTheme.typography.h6,color = White,fontWeight = FontWeight.Bold)
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
									) },
								onValueChange = {
									nombre.value = it },
								modifier = Modifier
									.padding(20.dp,20.dp,10.dp,10.dp)
									.weight(0.8f)
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
									fontSize = 18.sp
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
									) },
								modifier = Modifier
									.padding(10.dp,20.dp,10.dp,10.dp)
									.weight(0.8f)
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
									fontSize = 18.sp
								)
							)
							tipoId()
							TextField(
								value = NumIdentificacion.value,
								onValueChange = { NumIdentificacion.value = it },
								label = {
									Text(
										text = "N°Identificación",
										fontSize = 12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp,20.dp,20.dp,10.dp)
									.weight(0.5f)
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
									fontSize = 18.sp
								)
							)
						}
						Row {
							DatePicker(
								title = "Selecciona una fecha",
								selectedDateState = selectedDateState,
								onDateSelected = { dateState ->
									selectedDateState = dateState
									// Puedes agregar lógica adicional si es necesario
								}
							)
							sexo()
						}
					}
				}
			}
		}
	}
}
class DateState(var day: String, var month: String, var year: String)
@Composable
fun DatePicker(
	title: String,
	selectedDateState: DateState,
	onDateSelected: (DateState) -> Unit
) {
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
				.padding(horizontal = 8.dp)
		) {
			IconButton(onClick = {
				showOverlay.value = true
			}) {
				Icon(
					Icons.Filled.DateRange,
					tint = Turquoise,
					modifier = Modifier.background(White),
					contentDescription = "CalendarDatePicker"
				)
			}

			TextField(
				value = "${selectedDateState.day}/${selectedDateState.month}/${selectedDateState.year}",
				onValueChange = { newInput ->
					try {
						val onlyNumbersAndSlashes = newInput.filter { char ->
							char.isDigit() || char == '/'
						}

						// Contar las barras presentes en la entrada
						val slashCount = onlyNumbersAndSlashes.count { it == '/' }

						if (slashCount <= 2) {
							val components = onlyNumbersAndSlashes.split("/")

							selectedDateState.day = components.getOrNull(0) ?: ""
							selectedDateState.month = components.getOrNull(1) ?: ""
							selectedDateState.year = components.getOrNull(2) ?: ""

							// Validar los componentes sin lanzar excepciones
							if (isValidDate(
									selectedDateState.day,
									selectedDateState.month,
									selectedDateState.year
								)
							) {
								onDateSelected(selectedDateState)
							}
						}
					} catch (e: Exception) {
						// Manejar el error sin cerrar el programa
						// Puedes mostrar un mensaje de error al usuario o realizar otras acciones
						println("Error al procesar la fecha: ${e.message}")
					}
				},
				label = {
					Text(
						text = title,
						fontSize = 12.sp,
						fontWeight = FontWeight.Bold,
						color = Turquoise
					)
				},
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
				),
				keyboardOptions = KeyboardOptions.Default.copy(
					keyboardType = KeyboardType.Number
				),
				keyboardActions = KeyboardActions(
					onDone = {
						// Puedes manejar la acción "Done" si es necesario
					}
				)
			)
		}
	}
}
private fun isValidDate(day: String, month: String, year: String): Boolean {
	val dayInt = day.toIntOrNull()
	val monthInt = month.toIntOrNull()
	val yearInt = year.toIntOrNull()

	// Verificar si day, month y year son enteros no nulos
	if (dayInt != null && monthInt != null && yearInt != null) {
		// Verificar rangos válidos para día, mes y año
		if (dayInt in 1..31 && monthInt in 1..12 && yearInt > 0) {
			// Verificar si el día es válido para el mes específico (ten en cuenta años bisiestos)
			val daysInMonth = YearMonth.of(yearInt, monthInt).lengthOfMonth()
			return dayInt <= daysInMonth
		}
	}

	return false
}

@Composable
fun DatosDomicilio() {
	//Datos de Domicilio
	var Direccion = remember { mutableStateOf("") }
	var CP = remember { mutableStateOf("") }
	var Poblacion = remember { mutableStateOf("") }
	var Provincia = remember { mutableStateOf("") }
	var Pais = remember { mutableStateOf("") }
	var TelefonoParticular = remember { mutableStateOf("") }
	var TelefonoTrabajo = remember { mutableStateOf("") }
	var Movil = remember { mutableStateOf("") }
	var Fax = remember { mutableStateOf("") }
	var Email = remember { mutableStateOf("") }
	var Web = remember { mutableStateOf("") }
	Card(
		backgroundColor = White,
		modifier = Modifier
			.padding(8.dp,8.dp,20.dp,8.dp)
			.fillMaxWidth()
			.wrapContentSize(align = Alignment.Center)
			.clip(shape = RoundedCornerShape(20.dp))
	) {
		Column {
			Box(modifier = Modifier.fillMaxWidth().height(35.dp).shadow(10.dp).background(Turquoise)){
				Text(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center), text = "Datos de domicilio", style = MaterialTheme.typography.h6,color = White,fontWeight = FontWeight.Bold)
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
										fontSize = 14.sp,
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
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
							TextField(
								value = Poblacion.value,
								onValueChange = { Poblacion.value = it },
								label = {
									Text(
										text = "Pueblo o Ciudad",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp,20.dp,10.dp,10.dp)
									.weight(1f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
							TextField(
								value = Provincia.value,
								onValueChange = { Provincia.value = it },
								label = {
									Text(
										text = "Provincia",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp,20.dp,10.dp,10.dp)
									.weight(0.8f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
							TextField(
								value = Pais.value,
								onValueChange = { Pais.value = it },
								label = {
									Text(
										text = "País",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp,20.dp,10.dp,10.dp)
									.weight(0.5f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
							TextField(
								value = CP.value,
								onValueChange = { CP.value = it },
								label = {
									Text(
										text = "CP",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp,20.dp,10.dp,10.dp)
									.weight(0.5f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
						}
						// telefono particular | telefono trabajo | movil | fax
						Row {
							TextField(
								value = TelefonoParticular.value,
								onValueChange = { TelefonoParticular.value = it },
								label = {
									Text(
										text = "Teléfono Particular",
										fontSize = 14.sp,
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
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
							TextField(
								value = TelefonoTrabajo.value,
								onValueChange = { TelefonoTrabajo.value = it },
								label = {
									Text(
										text = "Teléfono Trabajo",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp)
									.weight(1.5f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
							TextField(
								value = Movil.value,
								onValueChange = { Movil.value = it },
								label = {
									Text(
										text = "Móvil",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp)
									.weight(1f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
							TextField(
								value = Fax.value,
								onValueChange = { Fax.value = it },
								label = {
									Text(
										text = "Fax",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp)
									.weight(1f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
						}
						// e-mai | web
						Row {
							TextField(
								value = Email.value,
								onValueChange = { Email.value = it },
								label = {
									Text(
										text = "Correo",
										fontSize = 14.sp,
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
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
							TextField(
								value = Web.value,
								onValueChange = { Web.value = it },
								label = {
									Text(
										text = "Web",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(10.dp)
									.weight(1.5f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
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
fun DatosBanco() {
	//Datos Personales
	var fechaNacimiento = remember { mutableStateOf("DD-MM-AAAA") }
	var fechaAlta = remember { mutableStateOf("") }
	var profesion = remember { mutableStateOf("") }
	var hijos = remember { mutableStateOf("") }
	var comodin = remember { mutableStateOf("") }
	var pacientesDependientes = remember { mutableStateOf("") }
	Card(
		backgroundColor = White,
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
					text = "Datos personales",
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
								value = fechaNacimiento.value,
								onValueChange = { fechaNacimiento.value = it },
								leadingIcon = {Icon(Icons.Filled.DateRange, "calendar", tint = Turquoise)},
								label = {
									Text(
										text = "Nacido en",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									)
								},
								modifier = Modifier
									.padding(20.dp, 20.dp, 10.dp, 10.dp)
									.width(160.dp)
									.fillMaxWidth()
									.shadow(elevation = 20.dp, spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
							TextField(
								value = fechaAlta.value,
								onValueChange = { fechaAlta.value = it },
								leadingIcon = {Icon(Icons.Filled.DateRange, "calendar", tint = Turquoise)},
								label = {
									Text(
										text = "Fecha Alta",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									)
								},
								modifier = Modifier
									.padding(10.dp, 20.dp, 10.dp, 10.dp)
									.width(160.dp)
									.fillMaxWidth()
									.shadow(elevation = 20.dp, spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
							sexo()
							grupoSanguineo()
							estadoCivil()
							TextField(
								value = profesion.value,
								onValueChange = { profesion.value = it },
								label = {
									Text(
										text = "Profesión",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									)
								},
								modifier = Modifier
									.padding(10.dp, 20.dp, 20.dp, 10.dp)
									.weight(0.2f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp, spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
						}
						Row {
							TextField(
								value = hijos.value,
								onValueChange = { hijos.value = it },
								label = {
									Text(
										text = "Hijos",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									)
								},
								modifier = Modifier
									.padding(20.dp, 10.dp, 10.dp, 20.dp)
									.weight(1f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp, spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
							TextField(
								value = comodin.value,
								onValueChange = { comodin.value = it },
								label = {
									Text(
										text = "Comodín",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									)
								},
								modifier = Modifier
									.padding(10.dp, 10.dp, 10.dp, 20.dp)
									.weight(4f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp, spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
							TextField(
								value = pacientesDependientes.value,
								onValueChange = { pacientesDependientes.value = it },
								label = {
									Text(
										text = "Pacientes Dependientes",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									)
								},
								modifier = Modifier
									.padding(10.dp, 10.dp, 20.dp, 20.dp)
									.weight(2f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp, spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Black,
									unfocusedIndicatorColor = White
								)
							)
						}
					}
				}
			}
		}
	}
}
@Composable
fun tipoId() {
	val options = listOf("DNI", "NIE", "Pasaporte")
	val expanded = remember { mutableStateOf(false) }
	val selectedOptionText = remember { mutableStateOf(options[0]) }

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
fun sexo() {
	val options = listOf("Hombre", "Mujer", "Otro")
	val expanded = remember { mutableStateOf(false) }
	val selectedOptionText = remember { mutableStateOf("Sexo") }

	Box(
		contentAlignment = Alignment.CenterStart,
		modifier = Modifier
			.padding(10.dp,10.dp,10.dp,10.dp)
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
						color = Dark2)
				}
			}
		}
	}
}
@Composable
fun grupoSanguineo() {
	val options = listOf("0-", "0+", "A-", "A+", "B-", "B+", "AB-", "AB+")
	val expanded = remember { mutableStateOf(false) }
	val selectedOptionText = remember { mutableStateOf("Grupo Sanguíneo") }

	Box(
		contentAlignment = Alignment.CenterStart,
		modifier = Modifier
			.padding(10.dp, 20.dp, 10.dp, 10.dp)
			.width(200.dp)
			.height(50.dp)
			.shadow(elevation = 20.dp, spotColor = Turquoise)
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
			onDismissRequest = { expanded.value = false },
			modifier = Modifier
				.verticalScroll(rememberScrollState())
				.width(200.dp)
				.heightIn(0.dp, 200.dp) // Ajusta la altura máxima según tus necesidades
		) {
			options.forEach { selectionOption ->
				DropdownMenuItem(
					onClick = {
						selectedOptionText.value = selectionOption
						expanded.value = false
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
	val LOPD = remember { mutableStateOf(false) }
	val aceptInfo = remember { mutableStateOf(false) }
	val includeMail = remember { mutableStateOf(false) }
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
	val SMS = remember { mutableStateOf(false) }
	val correoElectronico = remember { mutableStateOf(false) }
	val EnvioPostal = remember { mutableStateOf(false) }
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
fun estadoCivil() {
	val options = listOf("Soltero/a","Cónyuge sobreviviente que reúne los re-quisitos", "Casado presentando conjuntamente", "Jefe de familia", "Casado presentando por separado")
	val expanded = remember { mutableStateOf(false) }
	val selectedOptionText = remember { mutableStateOf("Estado Civil") }

	Box(
		contentAlignment = Alignment.CenterStart,
		modifier = Modifier
			.padding(10.dp,20.dp,10.dp,10.dp)
			.width(260.dp)
			.height(50.dp)
			.shadow(elevation = 20.dp,spotColor = Turquoise)
			.clip(shape = RoundedCornerShape(10.dp))
			.background(White)
			.clickable { expanded.value = !expanded.value },
	) {
		Row {
			Text(
				text = selectedOptionText.value,
				fontWeight = FontWeight.Bold,
				fontSize = 14.sp,
				color = Turquoise,
				modifier = Modifier.fillMaxSize().padding(10.dp).wrapContentSize(align = Alignment.CenterStart).weight(7f)
			)
			Icon(
				Icons.Filled.ArrowDropDown, "contentDescription",
				Modifier.fillMaxSize().wrapContentSize(align = Alignment.CenterEnd).weight(1f),
				tint = Turquoise
			)
		}
		DropdownMenu(
			expanded = expanded.value,
			modifier = Modifier.width(260.dp),
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
fun addingUserButtons(){
	Row(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.CenterEnd)){
		Button(
			onClick = {},
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
			onClick = {},
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