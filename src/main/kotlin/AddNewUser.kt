import androidx.compose.foundation.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.awt.Color

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
				DatosPersolales()
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
						Row {
							TextField(
								value = Direccion.value,
								onValueChange = { Direccion.value = it },
								label = {
									Text(
										text = "Dirección",
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
									textColor = Grey,
									unfocusedIndicatorColor = TurquoiseLite
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
									.padding(10.dp)
									.weight(0.20f)
									.fillMaxWidth()
									.shadow(elevation = 20.dp,spotColor = Turquoise)
									.clip(shape = RoundedCornerShape(10.dp))
									.height(50.dp),
								colors = TextFieldDefaults.textFieldColors(
									backgroundColor = White,
									focusedIndicatorColor = Turquoise,
									cursorColor = Grey,
									textColor = Grey,
									unfocusedIndicatorColor = TurquoiseLite
								)
							)
						}
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
									textColor = Grey,
									unfocusedIndicatorColor = TurquoiseLite
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
									textColor = Grey,
									unfocusedIndicatorColor = TurquoiseLite
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
									textColor = Grey,
									unfocusedIndicatorColor = TurquoiseLite
								)
							)
						}
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
									textColor = Grey,
									unfocusedIndicatorColor = TurquoiseLite
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
									textColor = Grey,
									unfocusedIndicatorColor = TurquoiseLite
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
									textColor = Grey,
									unfocusedIndicatorColor = TurquoiseLite
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
									textColor = Grey,
									unfocusedIndicatorColor = TurquoiseLite
								)
							)
						}
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
									textColor = Grey,
									unfocusedIndicatorColor = TurquoiseLite
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
									textColor = Grey,
									unfocusedIndicatorColor = TurquoiseLite
								)
							)
						}
					}
					Column(modifier = Modifier.weight(0.5f).fillMaxSize()) {
						Card(
							backgroundColor = TurquoiseLite,
							modifier = Modifier
								.padding(10.dp)
								.fillMaxSize()
								.height(260.dp)
								.clip(shape = RoundedCornerShape(20.dp))
						){}
					}
				}
			}
			Row {
				Dropdown()
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
						textColor = Grey,
						unfocusedIndicatorColor = TurquoiseLite
					)
				)
			}
		}
	}
}
@Composable
fun DatosPersolales() {
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
fun Dropdown() {
	val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
	val expanded = remember { mutableStateOf(false) }
	val selectedOptionText = remember { mutableStateOf(options[0]) }

	Box(
		contentAlignment = Alignment.CenterStart,
		modifier = Modifier
			.padding(10.dp)
			.width(300.dp)
			.shadow(elevation = 20.dp,spotColor = Turquoise)
			.clip(shape = RoundedCornerShape(10.dp))
			.border(BorderStroke(1.dp, Bright1), RoundedCornerShape(4.dp))
			.height(50.dp)
			.clickable { expanded.value = !expanded.value },
	) {
		Text(
			text = selectedOptionText.value,
			fontSize = 14.sp,
			modifier = Modifier.padding(start = 10.dp)
		)
		Icon(
			Icons.Filled.ArrowDropDown, "contentDescription",
			Modifier.align(Alignment.CenterEnd)
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
					Text(text = selectionOption)
				}
			}
		}
	}
}