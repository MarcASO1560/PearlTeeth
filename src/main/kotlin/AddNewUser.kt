import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

val showOverlay = mutableStateOf(false)
val showOverlay2 = mutableStateOf(false)

fun updateSelectedDate(newDate: String) {
	// Convierte la cadena de fecha en LocalDate
	val date = LocalDate.parse(newDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
	// Actualiza el estado que el DatePicker está observando
	selectedDate = date
}
@Composable
fun Filiar(updateSelectedDate: (LocalDate) -> Unit) {
	var showCalendarOverlay by remember { mutableStateOf(false) }
	var showLoginOverlay by remember { mutableStateOf(false) }

	if (showCalendarOverlay) {
		menu(onClose = {}, updateSelectedDate = updateSelectedDate)
	}
	if (showLoginOverlay) {
		LoginOverlay(
			onOverlayDismiss = { showLoginOverlay = false },
			onOverlayAction = { /* Acción al aplicar */ }
		)
	}
	Box(
		modifier = Modifier.fillMaxSize()
			.padding(10.dp, 10.dp, 10.dp, 10.dp)
			.background(color = White)
	) {

		val state = rememberLazyListState()

		LazyColumn(
			modifier = Modifier.padding(10.dp).fillMaxSize(),
			state = state
		) {
			item {
				DatosAdministrativos(
					setShowOverlay = { showOverlay.value = it }
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

@Composable
fun DatosAdministrativos(
	setShowOverlay: (Boolean) -> Unit,
) {
	//Datos administrativos
	var nombre = remember { mutableStateOf("") }
	var apellidos = remember { mutableStateOf("") }
	var NumIdentificacion = remember { mutableStateOf("") }

	Card(
		backgroundColor = White,
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
					text = "Datos Administrativos",
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
								title = "Seleccione una fecha",
								setShowOverlay = { show -> setShowOverlay(show) }
							)
							sexo()
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
				.padding(horizontal = 8.dp)
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
					fontSize = 16.sp
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
	var Direccion = remember { mutableStateOf("") }
	var CP = remember { mutableStateOf("") }
	var Poblacion = remember { mutableStateOf("") }
	var Provincia = remember { mutableStateOf("") }
	var Pais = remember { mutableStateOf("") }
	var TelefonoPrincipal = remember { mutableStateOf("") }
	var TelefonoAuxiliar = remember { mutableStateOf("") }
	var Email = remember { mutableStateOf("") }
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
fun DatosBanco() {
	//Datos Personales
	var IBAN = remember { mutableStateOf("") }
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
					text = "Datos Bancarios",
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
								value = IBAN.value,
								onValueChange = { IBAN.value = it },
								label = {
									Text(
										text = "IBAN",
										fontSize = 12.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									)
								},
								modifier = Modifier
									.padding(20.dp, 20.dp, 20.dp, 20.dp)
									.weight(0.2f)
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
						color = Turquoise)
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