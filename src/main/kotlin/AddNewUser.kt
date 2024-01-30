import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.awt.Color
import java.lang.module.ModuleFinder

@Composable
fun Filiar() {
	Box(
		modifier = Modifier.fillMaxSize()
			.background(color = White)
			.padding(10.dp)
	) {

		val state = rememberLazyListState()

		LazyColumn(
			modifier = Modifier.fillMaxSize(),
			state = state
		) {
			item {
				DatosDomicilio()
			}
			item {
				DatosPersonales()
			}
			item {
				Otros()
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
	var NumIdentificacion = remember { mutableStateOf("") }
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
				Text(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center), text = "Datos de domicilio", style = MaterialTheme.typography.h6,color = White,fontWeight = FontWeight.Bold)
			}
			Column {
				Row {
					Column(modifier = Modifier.weight(2f)) {
						// direccion | cp
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
									.weight(0.20f)
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
						// poblacion | provincia | pais
						Row {
							TextField(
								value = Poblacion.value,
								onValueChange = { Poblacion.value = it },
								label = {
									Text(
										text = "Población",
										fontSize = 14.sp,
										fontWeight = FontWeight.Bold,
										color = Turquoise
									) },
								modifier = Modifier
									.padding(20.dp,10.dp,10.dp,10.dp)
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
									.padding(10.dp)
									.weight(2f)
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
										text = "e-mail",
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
					}
					//Imagen
					Column(modifier = Modifier.weight(0.5f).fillMaxSize()) {
						Card(
							backgroundColor = TurquoiseLite,
							modifier = Modifier
								.padding(10.dp,20.dp,20.dp,10.dp)
								.fillMaxSize()
								.height(260.dp)
								.shadow(elevation = 30.dp,spotColor = Turquoise)
								.clip(shape = RoundedCornerShape(10.dp))
						){
							Text(
								text = "Foto",
								fontSize = 14.sp,
								fontWeight = FontWeight.Bold,
								color = White,
								modifier = Modifier.wrapContentSize(align = Alignment.Center)
							)
						}
					}
				}
			}
			// dropdown | numidentificacion
			Row {
				tipoId()
				TextField(
					value = NumIdentificacion.value,
					onValueChange = { NumIdentificacion.value = it },
					label = {
						Text(
							text = "N°Identificación",
							fontSize = 14.sp,
							fontWeight = FontWeight.Bold,
							color = Turquoise
						) },
					modifier = Modifier
						.padding(10.dp)
						.width(300.dp)
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
								.padding(20.dp,10.dp,10.dp,20.dp)
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
@Composable
fun DatosPersonales() {
	//Datos Personales
	var FechaNacimiento = remember { mutableStateOf("") }
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
	var NumIdentificacion = remember { mutableStateOf("") }
	Card(
		backgroundColor = Bright1,
		modifier = Modifier
			.padding(8.dp,8.dp,20.dp,8.dp)
			.fillMaxWidth()
			.height(200.dp)
			.clip(shape = RoundedCornerShape(20.dp))
	) {
		Column {
			Box(modifier = Modifier.fillMaxWidth().height(35.dp).shadow(10.dp).background(Turquoise)){
				Text(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center), text = "Datos personales", style = MaterialTheme.typography.h6,color = White,fontWeight = FontWeight.Bold)
			}
		}
	}
}
@Composable
fun Otros() {
	Card(
		backgroundColor = Bright1,
		modifier = Modifier
			.padding(8.dp,8.dp,20.dp,8.dp)
			.fillMaxWidth()
			.height(200.dp)
			.clip(shape = RoundedCornerShape(20.dp))
	) {
		Column {
			Box(modifier = Modifier.fillMaxWidth().height(35.dp).shadow(10.dp).background(Turquoise)){
				Text(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center), text = "Otros", style = MaterialTheme.typography.h6,color = White,fontWeight = FontWeight.Bold)
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
			.padding(20.dp,10.dp,10.dp,10.dp)
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
						uncheckedColor = Dark2,
						checkmarkColor = White
					)
				)
				Text(
					text = "Acepta L.O.P.D",
					fontSize = 14.sp,
					fontWeight = FontWeight.Bold,
					color = Dark2,
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
						uncheckedColor = Dark2,
						checkmarkColor = White
					)
				)
				Text(
					text = "Acepta Información",
					fontSize = 14.sp,
					fontWeight = FontWeight.Bold,
					color = Dark2,
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
						uncheckedColor = Dark2,
						checkmarkColor = White
					)
				)
				Text(
					text = "Incluir en Mailing",
					fontSize = 14.sp,
					fontWeight = FontWeight.Bold,
					color = Dark2,
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
						uncheckedColor = Dark2,
						checkmarkColor = White
					)
				)
				Text(
					text = "SMS",
					fontSize = 14.sp,
					fontWeight = FontWeight.Bold,
					color = Dark2,
					modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.CenterStart)
				)
			}
		}
		Box(modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.Center).weight(1f)) {
			Row(modifier = Modifier.fillMaxSize().padding(5.dp).wrapContentWidth(align = Alignment.CenterHorizontally)) {
				Checkbox(
					checked = correoElectronico.value,
					modifier = Modifier.padding(5.dp).size(40.dp).wrapContentSize(align = Alignment.Center),
					onCheckedChange = { correoElectronico.value = it },
					colors = CheckboxDefaults.colors(
						checkedColor = Turquoise,
						uncheckedColor = Dark2,
						checkmarkColor = White
					)
				)
				Text(
					text = "Correo Elctrónico",
					fontSize = 14.sp,
					fontWeight = FontWeight.Bold,
					color = Dark2,
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
						uncheckedColor = Dark2,
						checkmarkColor = White
					)
				)
				Text(
					text = "Envío Postal",
					fontSize = 14.sp,
					fontWeight = FontWeight.Bold,
					color = Dark2,
					modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.CenterStart)
				)
			}
		}
	}
}

